# Auto Generating Rest API Documentation via Rest Assured Tests and AsciiDoctor in a Spring Boot Based Web Application

A clear and concise REST API documentation is mandatory for a rest api to be easily used. However, it's error prone and is also hard to maintain an api documentation if we attempt to write it manually.  This sample project demonstrates how we can use rest assured tests to generate api documentation with a small amount of manual work. 

## Project Setup

This sample uses Spring Boot 2.x as its core framework. In addition, the following dependencies and build plugins are added for demonstration purposes: 

### Dependencies

The dependencies needed for this demostration are as follows: 

#### Application Specific Dependencies
1. **spring-boot-starter-web**
2. **spring-boot-starter-data-jpa**
3. **spring-boot-starter-data-rest**
4. **com.h2database:h2**

#### Test Specific Dependencies
1. **spring-boot-starter-test**
2. **rest-assured**: For testing rest endpoints
3. **spring-restdocs-restassured**: For helping spring integration tests to generate documentation while running the tests.
4. **hamcrest-all**: For defining declarative test matchers. 

### Build Plugins & Configurations

1. **Build Helper Maven Plugin**: This plugin is used for introducing an additional test source folder to the project. 
    ```xml
    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>build-helper-maven-plugin</artifactId>
        <version>${build-helper-maven-plugin.version}</version>
        <executions>
            <execution>
                <id>add-test-source</id>
                <phase>generate-test-sources</phase>
                <goals>
                    <goal>add-test-source</goal>
                </goals>
                <configuration>
                    <sources>
                        <source>src/integration/java</source>
                    </sources>
                </configuration>
            </execution>
        </executions>
    </plugin>
    ```
2. **Spring Boot Maven Plugin**: The spring boot plugin as usual. We are not going to introduce any custom configuration here. 
3. **Maven Surefire Plugin**: In simple cases, we do not need to configure this plugin. However, in this case the plugin is configured to exclude integration tests from unit test perspective. It's done by adding exclusion patterns to the plugin configuration. 
    ```xml
    <plugin>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
            <excludes>
                <exclude>**/*IntTest.java</exclude>
            </excludes>
        </configuration>
    </plugin>
    ```
4. **Maven Failsafe Plugin**: This is the plugin that will be used for running integration tests. Similar to Surefire Plugin we need additional configuration here; however, this time we will add inclusions instead of exclusions. 
    ```xml
    <plugin>
        <artifactId>maven-failsafe-plugin</artifactId>
        <executions>
            <execution>
                <id>integration-test</id>
                <goals>
                    <goal>integration-test</goal>
                </goals>
                <configuration>
                    <includes>
                        <include>**/*IntTest.class</include>
                    </includes>
                </configuration>
            </execution>
        </executions>
    </plugin>
    ```
5. **Asciidoctor Maven Plugin**: Ascii doctor maven plugin is the plugin that we will use to  automatically generate API documentation using the integration tests that we have implemented. The most important configuration that will help us to use this plugin with spring boot is the spring-restdocs-asciidoctor plugin dependency. You can also make sure that the documents are processed as soon as integration tests are run by adding an explicit phase decleration to the plugin: 
    ```xml
    <plugin>
        <groupId>org.asciidoctor</groupId>
        <artifactId>asciidoctor-maven-plugin</artifactId>
        <version>${asciidoctor-maven-plugin.version}</version>
        <executions>
            <execution>
                <id>generate-docs</id>
                <phase>post-integration-test</phase>
                <goals>
                    <goal>process-asciidoc</goal>
                </goals>
                <configuration>
                    <backend>html</backend>
                    <doctype>book</doctype>
                    <attributes>
                        <snippets>${snippetsDirectory}</snippets>
                    </attributes>
                    <sourceDirectory>src/docs/asciidocs</sourceDirectory>
                    <outputDirectory>target/generated-docs</outputDirectory>
                </configuration>
            </execution>
        </executions>
        <dependencies>
            <dependency>
                <groupId>org.springframework.restdocs</groupId>
                <artifactId>spring-restdocs-asciidoctor</artifactId>
                <version>${spring-restdocs-asciidoctor.version}</version>
            </dependency>
        </dependencies>
    </plugin>
    ```

