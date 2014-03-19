DAMapping Sample project : Maven multi module
=============================================

This multi module Maven project demos the use of DAMapping in a multi module context and proposes a way to simplify
the selective use of DAMapping in specific modules.

## Description of the project

The sample project declares a root module and two sub modules (`moduleA` and `moduleB`) only the first one if which will use DAMapping.
Also, modules `moduleA` and `moduleB` declares the root module as their parent POM.

## With Java 7+ and automatic discovery

When project is compiled with Java 1.7 and automatic discovery is possible (see [maven-project](../maven-project) for details), DAMApping annotations will be automatically processed as long as DAMapping annotation processor artifact is a direct dependency.

To avoid unnecessary annotation processing, it is advised to avoid declaring the DAMapping annotation processor as a direct dependency of the parent POM, as any module using this parent POM will then be compiled with the annotation processor enabled.

In parent POM, use dependency management to configure the dependency :

```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>fr.javatronic.damapping</groupId>
        <artifactId>annotation-processor</artifactId>
        <version>${damapping.version}</version>
        <!-- note that the compile scope is the default scope so following tag is optionnal -->
        <scope>compile</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```

## With Java 6 or when autodiscoveny is disabled

To enable DAMapping annotations processing, DAMapping annotation processor must be declared explicitly.
So, maven-compiler-plugin must be configured in each module using DAMapping annotations.
This configuration can be inherited from the parent POM but to avoid enabling DAMapping annotations processing in every module inheriting from the parent POM, do has follown :

* declare DAMapping annotation-processor in dependencyManagement with the `provided` scope
    - using the `provided` scope in dependencyManagement delegates to each module using DAMapping the responsability of specifying explicitly the `compile` scope

```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>fr.javatronic.damapping</groupId>
        <artifactId>annotation-processor</artifactId>
        <version>${damapping.version}</version>
        <!-- using provided scope in dependencyManagement allows to delegate the declaration in compile scope
              to only the module actually using DAMApping -->
        <scope>provided</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```

* declare DAMapping annotation-processor as a dependency of the parent POM which will get the `provided` scope from dependencyManagement

```xml
  <dependencies>
    <dependency>
      <groupId>fr.javatronic.damapping</groupId>
      <artifactId>annotation-processor</artifactId>
    </dependency>
  </dependencies>
```

* configure the maven-compiler-plugin with the explicite declaration of the DAMapping annotation processor

```xml
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>2.5.1</version>
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

