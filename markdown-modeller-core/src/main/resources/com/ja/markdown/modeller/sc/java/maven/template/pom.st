pom(project) ::= <<
\<?xml version="1.0" encoding="UTF-8"?\>
\<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"\>
    \<modelVersion\>4.0.0\</modelVersion\>
    \<groupId\><project.groupId>\</groupId\>
    \<artifactId\><project.artifactId>\</artifactId\>
    \<version\><project.version>\</version\>
    
    \<properties\>
      \<project.build.sourceEncoding\>UTF-8\</project.build.sourceEncoding\>
    \</properties\>
	
    \<dependencies\><project.javaProject.dependencies:{d|
    
        \<dependency\>
            \<groupId\><d.groupId>\</groupId\>
            \<artifactId\><d.artifactId>\</artifactId\>
            \<version\><d.version>\</version\>
            \<scope\><d.scope>\</scope\>
        \</dependency\>}>
    \</dependencies\>
    
    \<profiles\><project.profiles:{p|
    
        \<profile\>
            \<id\><p.id>\</id\>
            \<activation\>
                \<activeByDefault\><p.activeByDefault>\</activeByDefault\>
            \</activation\>
            \<dependencies\><p.dependencies:{d|
    
                \<dependency\>
                    \<groupId\><d.groupId>\</groupId\>
                    \<artifactId\><d.artifactId>\</artifactId\>
                    \<version\><d.version>\</version\>
                    \<scope\><d.scope>\</scope\>
                \</dependency\>}>
            \</dependencies\>
        \</profile\>}>
    \</profiles\>
    
    \<build\>
      \<pluginManagement\>
        \<plugins\>
          \<plugin\>
            \<groupId\>org.apache.maven.plugins\</groupId\>
            \<artifactId\>maven-compiler-plugin\</artifactId\>
            \<version\>3.1\</version\>
          \</plugin\>
        \</plugins\>
      \</pluginManagement\>
      \<plugins\>
        \<plugin\>
          \<groupId\>org.apache.maven.plugins\</groupId\>
          \<artifactId\>maven-compiler-plugin\</artifactId\>
          \<configuration\>
            \<source\>1.7\</source\>
            \<target\>1.7\</target\>
          \</configuration\>
        \</plugin\>
      \</plugins\>
    \</build\>
\</project\>
>>