package net.entrofi.spring.restassuredasciidoctor.data.repository;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.entrofi.spring.restassuredasciidoctor.RestAssuredAsciiDoctorApplication;
import net.entrofi.spring.restassuredasciidoctor.data.entity.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

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


    @Test
    public void saveNote_given_a_valid_note_request_should_return_created() {
        Note note = new Note("Documented call", "Documented call details");
        given(documentationSpec).filter(document("save-note-created"))
                .body(note).contentType(ContentType.JSON)
                .when().post(host + "/" + "notes")
                .then().statusCode(HttpStatus.CREATED.value());
    }

}

