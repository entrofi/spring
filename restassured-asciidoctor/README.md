# Auto Generating Rest API Documentation via Rest Assured Tests and AsciiDoctor in a Spring Boot Based Web Application

A clear and concise REST API documentation is mandatory for a rest api to be easily used. However, it's error prone and is also hard to maintain an api documentation if we attempt to write it manually.  This sample project demonstrates how we can use rest assured tests to generate api documentation with a small amount of manual work. 

- [Project Setup](#project-setup)
  * [Dependencies](#dependencies)
    + [Application Specific Dependencies](#application-specific-dependencies)
    + [Test Specific Dependencies](#test-specific-dependencies)
  * [Build Plugins & Configurations](#build-plugins---configurations)

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

## Generating Documentation for the API

Now that, we setup our project. It's now time to do some actual demonstration. This sample will use spring boot's auto generated controller layer. 

### Exposing an API
As the first step let's create a simple entity and then bind a repository implementation to that entity. 
```java
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String details;

    public Note() {
    }

    public Note(String title, String details) {
        this.title = title;
        this.details = details;
    }
    //...getters and setters
}
```

Let's create a repository interface which extends JpaRepository (or any other repository interface provided by spring data). We need to mark this repository as a *repository rest resource* `@RepositoryRestResource`, in order to be able to expose its methods via auto generated controller layer: 

```java
@RepositoryRestResource
public interface NoteRepository extends JpaRepository<Note, Long> {
    
}
```

We now have an auto-generated controller layer and detailed information about the methods exposed in this layer can be seen using the url ``http://host:port/profile``

```json
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/profile"
        },
        "notes": {
            "href": "http://localhost:8080/profile/notes"
        }
    }
}
```

In order to access api endpoint descriptions, which are related with our ``Note`` entity, you can visit ``.../profile/notes``. We will not show the results here to keep the demonstration simple. In summary, we now have endpoints like ``(method: GET) http://localhost:8080/notes``, ``(method: POST) http://localhost:8080/notes``, ..., which provides basic CRUD functionality for our entity. 

We can now move on to documentation generation step. 

### API Documentation Generation

We have a few steps left to achieve our goal. Steps are as follows in summary: 
1. Configure integration tests so that they are ready to be used for API documentation generation.
2. Implement test cases with documentation support. 
3. Create an asciidoctor container document in order to collect auto-generated documents and to add some manual explanations to them. 
4. Run the tests and copy generated html(s) to serve our clients. 

Let's begin with the first step

#### Configuring the integration tests for auto-documentation generation

The basic configuration for our integration tests is shown below. Let's explain them step by step:

If we take a look at the class level configuration annotations, we can see that in the first line junit is being informed to use ``SpringRunner`` class instead of using its built-in runner. In the second line we're marking this test class as a Spring Boot test using `@SpringBootTest` annotation. This annotation searches for `SpringBootConfiguration` when we do not provide nested `@Configuration`s and the classes parameter informs spring boot to look specifically for the specified classes while the application context is being loaded. Moving to the last one of the annotations , `@AutoConfigureRestDocs`, as you can guess, this one automatically configures Rest Api Documentation Support provided by Spring Rest Docs. 

Moving to injected fields, which we have in this test class: the `port` and `host` attributes are obviously the web container parameters configured within Spring Boot test context (either automatically or manually). If you take a look at the class level `@SpringBootTest` annotation again, you can see there that we informed spring boot test context to use a random port each time the tests are run.  The last attribute is a bean from rest-assured which we will use to document our API calls. 


```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestAssuredAsciiDoctorApplication.class}, webEnvironment = RANDOM_PORT)
@AutoConfigureRestDocs
public class NoteRepositoryIntTest {
    @Value("${local.server.port}")
    private int port;

    @Value("${deployment.environment.host:http://localhost}")
    private String host;

    @Autowired
    private RequestSpecification documentationSpec;

}
```

#### Implement test cases which document API calls

Below is an integration test implementation which sends a post request a server while documenting necessary information from the request/response lifecycle.  Let's investigate, how this is achieved.
```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestAssuredAsciiDoctorApplication.class}, webEnvironment = RANDOM_PORT)
@AutoConfigureRestDocs
public class NoteRepositoryIntTest {
//    ...Beginning part of the file
    
    @Autowired
    private RequestSpecification documentationSpec;
    
    @Test
    public void saveNote_given_a_valid_note_request_should_return_created() {
        Note note = new Note("Documented call", "Documented call details");
        given(documentationSpec).filter(document("save-note-created"))
                .body(note).contentType(ContentType.JSON)
                .when().post(host + "/" + "notes")
                .then().statusCode(HttpStatus.CREATED.value());
    }
    
//    ...Rest of the file
}
```

Take a look at the line starting with `given(documentationSpec)...`, this call to the `given()` method of the rest-assured library informs the library that we are interested in a predefined request specification which, in our case, informs the library about the intention that we would like to document the call. 

The next call, which follows the `given()` method, is the `filter` method provided by the library. Using this method we're adding a `org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document` filter which is an implementation of `io.restassured.filter.Filter` interface.  The argument to `document("save-note-create")` identifies the snippet folder in which we want to collect the snippets that will be created by this API test call. 

After running this test method, you can see that a bunch of files with extension `.adoc` are created under `target/generated-snippets/save-note-created` folder. These files are various different representations of our request/response cycle. In the next section we will use these snippets to create our actual API documentation. 


#### Create an Asciidoctor container document to generate human readable documents
We are about to finish with documenting our API. In this step we are going to create a container document `index.adoc` under `src/docs/asciidocs` folder. After creating this file, we will add some manual descriptions, which we may want to represent in our documentation, into this document and also include auto-generated snippets that we wish the document to show to our clients. 

```asciidoc
= Note API Documentation
:doctype: book
:icons: font
:source-highlighter: highlightjs
:toc: left
:toclevels: 4
:sectlinks:

[[overview]]
== Summary and Assumptions

[[overview-http-verbs]]
== HTTP verbs

This sample implementation adheres to following HTTP/REST conventions while using HTTP verbs.

|===
| Verb | Usage

| `GET`
| Used to retrieve a resource

| `POST`
| Used to create a new resource

| `PUT`
| Used to update an existing resource.
|===

[[resources]]
= Resources


[[save-note-created]]
== Create Note

Creates a note. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed at commodo mauris. Curabitur pharetra neque eu tincidunt molestie. Pellentesque vehicula finibus lacus, sit amet tincidunt dui bibendum eu. Etiam et aliquet massa, ut auctor magna. Quisque lacinia lacinia ex in consequat.

.request
include::{snippets}/save-note-created/http-request.adoc[]

.response
include::{snippets}/save-note-created/http-response.adoc[]

```

#### Run the tests and copy generated document(s) to serve our consumers
As soon as we're finished with writing the container file, we can run our tests via `mvn verify` command and generate the human readable form of our documentation. The generated file(s) can be found under `target/generated-docs/`. You can now copy/deploy generated html file(s) to the place that you want to serve your documentation. 










 
