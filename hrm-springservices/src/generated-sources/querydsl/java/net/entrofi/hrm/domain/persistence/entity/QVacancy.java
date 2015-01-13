package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVacancy is a Querydsl query type for Vacancy
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVacancy extends EntityPathBase<Vacancy> {

    private static final long serialVersionUID = -1732858294;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QVacancy vacancy = new QVacancy("vacancy");

    public final SetPath<Application, QApplication> applications = this.<Application, QApplication>createSet("applications", Application.class, QApplication.class, PathInits.DIRECT);

    public final StringPath definition = createString("definition");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QJobDefinition jobDefinition;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QVacancy(String variable) {
        this(Vacancy.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QVacancy(Path<? extends Vacancy> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVacancy(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVacancy(PathMetadata<?> metadata, PathInits inits) {
        this(Vacancy.class, metadata, inits);
    }

    public QVacancy(Class<? extends Vacancy> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jobDefinition = inits.isInitialized("jobDefinition") ? new QJobDefinition(forProperty("jobDefinition")) : null;
    }

}

