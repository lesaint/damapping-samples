DAMapping Sample project : Maven single module
=============================================

This minimal single module Maven project demos the use of DAMapping.

## With Java 7+

To use DAMapping annotation processing, declaring DAMapping annotation-processor artifact as a direct dependency is enough.
Since Java 7, javac compiler is capable of discovering annotation processor in the class path automatically (aka. here as autodiscovery).

```xml
<dependency>
    <groupId>fr.javatronic.damapping</groupId>
    <artifactId>annotation-processor</artifactId>
    <version>0.2.0</version>
    <!-- note that the compile scope is the default scope so following tag is optionnal -->
    <scope>compile</scope>
</dependency>
```

## With Java 6 or when autodiscovery is disabled

For the following reasons, it might be necessary to explicitly declare DAMApping annotation processor:
* module is compiled with Java 6
* another annotation processor is already declared explicitly in the maven-compiler-plugin
    - declaring explictly annotation processor disables auto discovery
    - so, DAMApping annotation processor has to be declared explicitly as well

```xml
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <configuration>
            <source>1.6</source>
            <target>1.6</target>
            <encoding>UTF-8</encoding>
            <annotationProcessors>
              <annotationProcessor>fr.javatronic.damapping.processor.DAAnnotationProcessor</annotationProcessor>
            </annotationProcessors>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```

## DAMapping annotations

DAMapping annotations are isolated in a specific artifact. But this artifact is a dependency of the annotation-processor artifact.
So there is no extra dependency to add to project to use DAMapping annotations, but for information, here it is anyway:

```xml
<dependency>
    <groupId>fr.javatronic.damapping</groupId>
    <artifactId>annotations</artifactId>
    <version>0.2.0</version>
</dependency>
```
