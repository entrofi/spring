# Running Tests with Groovy

This example demonstrates how we can implement our tests using groovy in a java based project. You might be asking why. Well, implementing tests with groovy has some advantages. However, this article does not try to convince you groovy is a better way to implement your tests. It's just another alternative to implement your tests. If you want to investigate testing java projects with groovy, the article will, then,  provide you a starting point for your investigation.   

## A few advantages that we can have when we write our tests with groovy

Even though, I am not going to argue about the advantages, you can find a list of a few advantages, which I can think, below : 
1. Creation of mocks and stubs are easier in groovy. Using language features like closures and simple maps you can avoid implementation of inner classes and express your mock objects using closures.  Further information regarding this topic can be found in the [official documentation](http://docs.groovy-lang.org/next/html/documentation/core-testing-guide.html#_mocking_and_stubbing). 
2. Expressive, human readable test method name declarations, 
3. String interpolation,
4. Easier to adapt with BDD frameworks in an expressive manner,
5. Groovy provides many syntactic sugars for data creation, 
6. Test outputs can be more expressive, especially by using frameworks like spock. 


Let's create our example setup now!

## Setting up the project - Adding groovy support to unit tests

Although, this example is not necessarily related with spring boot, we are going to use spring boot here to get up and running quickly. Go to [spring initialzr](https://start.spring.io/) page and create a gradle backed spring boot ***java*** project with the following dependency `spring-boot-starter-web`. 

Our initial `build.gradle` script will be similar to the following snippet: 

```groovy
plugins {
	id 'org.springframework.boot' version '2.1.3.RELEASE'
	id 'java'
}

apply plugin: 'io.spring.dependency-management'

group = 'net.entrofi.testing'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

```

Simple isn't it? Our first job is to add groovy support to our unit tests and then as the following task, we are going to extend this support to our spring boot integration tests. Steps for adding groovy support to unit tests are as follows: 

1. Add groovy-all as a dependency with `testImplementation` scope to our build script. 
    ```groovy
    dependencies {
        ....
        testImplementation 'org.codehaus.groovy:groovy-all:2.4.15'
    }
    ```
2. Inform gradle that we are going to put our groovy based test implementations under the source folder `src/test/groovy`.

    ```groovy
    sourceSets {
        test {
            groovy {
                srcDir file('src/test/groovy')
            }
        }
    }
    ```
3. Add a line to apply groovy plugin so that gradle is able to process groovy files. 
    ```groovy
    apply plugin: 'groovy'
    ```
    
As soon as these steps are applied, our build script will be like the following snippet: 

```groovy
plugins {
	id 'org.springframework.boot' version '2.1.3.RELEASE'
	id 'java'
}

apply plugin: 'io.spring.dependency-management'
apply plugin: 'groovy'

group = 'net.entrofi.testing'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
	mavenCentral()
}

sourceSets {
	test {
		groovy {
			srcDir file('src/test/groovy')
		}
	}
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.codehaus.groovy:groovy-all:2.4.15'
}
```


We can now add some trivial utility class and create a unit test for it using groovy. Create a class called `MathUtil` under the package `net.entrofi.testing.groovyintengrationtests.util`, and implement a method which sums it's arguments and returns the result. 

```java
public final class MathUtil {

    public static int sum(int a, int b) {
        return a + b;
    }
}
```

Now create a groovy unit test file (`MathUtilTest.groovy`) for this trivial utility class under `src/test/groovy` folder. 

```groovy
package net.entrofi.testing.groovyintengrationtests.util

import org.junit.Test

import static org.junit.Assert.assertEquals

class MathUtilTest {


    @Test
    void "sum of 2 and 3 should return 5"() {
        int result = 5;
        assertEquals(5, MathUtil.sum(2, 3))
    }
}
```

Run the test, and if everything goes fine, you can move on to integration test configuration.

## Setting up groovy backed spring boot integration tests

This section is also similar to the previous one, with only slight differences. We will inform gradle that our groovy based integration tests reside under `src/integration/groovy` folder and extend our previous test configuration in order to reuse similar libraries that we will need for integration tests also.  In addition, we are going to configure the flow using which our integration tests will be run:

Add the following lines to `sourceSets` section of the `build.gradle` file: 

```groovy
sourceSets {
	test {
		groovy {
			srcDir file('src/test/groovy')
		}
	}

	integration {
		groovy {
			compileClasspath += main.output + test.output
			runtimeClasspath += main.output + test.output
			srcDir file('src/integration/groovy')
		}
		java.srcDir project.file('src/integration/java')
		resources.srcDir project.file('src/integration/resources')
	}
}
```
`sourceSets` in gradle are instances of [NamedDomainObjectContainer](https://docs.gradle.org/2.4/javadoc/org/gradle/api/NamedDomainObjectContainer.html) which means named extensions of dependency [configurations](https://docs.gradle.org/current/userguide/managing_dependency_configurations.html#managing_dependency_configurations) will be supported by the relevant plugins. For instance, gradle defines `implementation` (previously compile), `runtime`, `testImplementation`, etc., as [predefined dependency scopes (configurations)](https://docs.gradle.org/current/userguide/managing_dependency_configurations.html#managing_dependency_configurations) by default, and these configurations are further extended (or new ones are provided) by plugins like java plugin. That is to say, we are now going to have independent dependency scopes for our new named configuration `integration`, like `integrationImplementation`, `integrationRuntime`, and so on. 

We can now configure our `integration` related scopes as extensions to `test` configurations, so that we can use similar dependencies from the test scope. 

```groovy
configurations {
	integrationRuntime.extendsFrom testRuntime
	integrationImplementation.extendsFrom testImplementation
}
```

The next item in our build script configuration is defining the task that runs our integration tests and introducing this task to our build lifecycle. The task descriptions is as follows: 

```groovy
task('integrationTest', type: Test, description: 'Runs the integration tests.', group: 'Verification') {
	testClassesDirs = project.sourceSets.integration.output.classesDirs
	classpath = project.sourceSets.integration.runtimeClasspath
}
``` 
In this task implementation, we named our task as `integrationTest`, and marked it as a test task using `type: Test` parameter declaration. After defining the name and the type, we have informed gradle that our integration test classes reside in the output directory of our `integration` source set and also added the classpath from that source set to our task.  

It's now time to introduce this task to our build lifecycle. We would like to introduce this task in check stage where we also run unit tests. As you know integration tests are long running tests, and we prefer shortenning our feedback lifecycle. If there is a failing test within our unit test scope, before even running integration tests. Therefore, we are going to make sure that our integration tests will run after unit tests:

```groovy
check.dependsOn integrationTest
integrationTest.mustRunAfter test
```

Now, we're done with the build script configuration. Let's implement a trivial integration test in our project. In order to do this we will add a `HelloWorldController` class to our project: 

```java
@RestController
public class HelloWorldController {


    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name") String name) {
        return "Hello " + name;
    }
}
```


The next step is to add the corresponding spring boot integration test configuration, and implementation under `src/integration/groovy`: 

```groovy

@RunWith(SpringRunner.class)
@SpringBootTest(classes = [ GroovyIntengrationTestsApplication.class], webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class HelloWorldControllerTest {

    @Value('${local.server.port}')
    private int port;

    @Value('${deployment.environment.host:http://localhost}')
    private String host;

    @Autowired
    private MockMvc mvc

    @Test
    void "given name is hasan hello should return 'hello hasan'" () {
        mvc.perform(
                get(getUrl("/hello"))
                        .param('name', 'hasan')
        ).andExpect(status().isOk())
        .andExpect(content().string('Hello hasan'));
    }


    private String getUrl(String uri) {
        String url =  host + ":" + port;
        url = StringUtils.isEmpty(uri) ? url : url + uri;
        return url;
    }
}
```
After implementing our test with groovy, we run our integration tests (only) using the following command: 

```bash
$ ./gradlew integrationTest
```

> ### Further Reading
>
> 1. [Gradle User Guide - Chapter 6. Build Script Basics](http://gradle.org/docs/2.4/userguide/tutorial_using_tasks.html)
> 2. [Gradle Reference - sourceSets{ }](https://gradle.org/docs/2.4/dsl/org.gradle.api.Project.html#org.gradle.api.Project:sourceSets%28groovy.lang.Closure%29)
> 3. [Gradle User Guide - Dependency Management for Java Projects](http://gradle.org/docs/2.4/userguide/artifact_dependencies_tutorial.html#configurations)
> 4. [Gradle Reference - configurations{ }](http://gradle.org/docs/2.4/dsl/org.gradle.api.Project.html#org.gradle.api.Project:configurations%28groovy.lang.Closure%29)


