pom(project) ::= <<
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>$project.groupId$</groupId>
    <artifactId>$project.artifactId$</artifactId>
    <version>$project.version$</version>
    <packaging>$project.javaProject.packaging$</packaging>
    
    <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
	
    <dependencies>$project.javaProject.dependencies:{d|
    
        <dependency>
            <groupId>$d.groupId$</groupId>
            <artifactId>$d.artifactId$</artifactId>
            <version>$d.version$</version>
            <scope>$d.scope$</scope>
        </dependency>}$
    </dependencies>
    
    <profiles>$project.profiles:{p|
    
        <profile>
            <id>$p.id$</id>
            <activation>
                <activeByDefault>$p.activeByDefault$</activeByDefault>
            </activation>
            <dependencies>$p.dependencies:{d|
    
                <dependency>
                    <groupId>$d.groupId$</groupId>
                    <artifactId>$d.artifactId$</artifactId>
                    <version>$d.version$</version>
                    <scope>$d.scope$</scope>
                </dependency>}$
            </dependencies>
        </profile>}$
    </profiles>
    
    <build>
      <resources>$project.resources:{r|
      
          <resource>
              <$r.type$>$r.name$</$r.type$>
          </resource>}$
      </resources>
        $plugins(project)$
    </build>
</project>
>>