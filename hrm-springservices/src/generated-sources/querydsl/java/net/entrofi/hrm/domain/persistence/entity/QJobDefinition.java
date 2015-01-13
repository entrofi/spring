package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJobDefinition is a Querydsl query type for JobDefinition
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJobDefinition extends EntityPathBase<JobDefinition> {

    private static final long serialVersionUID = -269451393;

    public static final QJobDefinition jobDefinition = new QJobDefinition("jobDefinition");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<Vacancy, QVacancy> vacancies = this.<Vacancy, QVacancy>createSet("vacancies", Vacancy.class, QVacancy.class, PathInits.DIRECT);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QJobDefinition(String variable) {
        super(JobDefinition.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QJobDefinition(Path<? extends JobDefinition> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QJobDefinition(PathMetadata<?> metadata) {
        super(JobDefinition.class, metadata);
    }

}

