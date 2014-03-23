plugins(model) ::= <<
<pluginManagement>
    <plugins>$project.plugins:{p|

        <plugin>
            <groupId>$p.groupId$</groupId>
            <artifactId>$p.artifactId$</artifactId>
            <version>$p.version$</version>
        </plugin>}$
    </plugins>
</pluginManagement>
<plugins>$project.plugins:{p|

    <plugin>
        <groupId>$p.groupId$</groupId>
        <artifactId>$p.artifactId$</artifactId>
        $configuration(p.configuration)$
        <executions>$p.executions:{ex|
            <execution>
                <goals>$ex.goals:{g|

                    <goal>$g.name$</goal>}$
                </goals>
                $configuration(ex.configuration)$
            </execution>}$
        </executions>
    </plugin>}$
</plugins>
>>