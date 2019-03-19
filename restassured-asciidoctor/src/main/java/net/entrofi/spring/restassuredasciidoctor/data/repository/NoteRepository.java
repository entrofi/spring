package net.entrofi.spring.restassuredasciidoctor.data.repository;

import net.entrofi.spring.restassuredasciidoctor.data.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NoteRepository extends JpaRepository<Note, Long> {
}
