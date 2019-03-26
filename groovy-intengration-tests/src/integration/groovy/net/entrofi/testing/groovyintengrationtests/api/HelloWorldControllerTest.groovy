import net.entrofi.testing.groovyintengrationtests.GroovyIntengrationTestsApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.util.StringUtils

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
    void "given name is hasan 'hello' should return Hello hasan" () {
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
