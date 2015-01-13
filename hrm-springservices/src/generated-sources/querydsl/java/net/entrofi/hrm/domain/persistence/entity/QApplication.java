package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QApplication is a Querydsl query type for Application
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QApplication extends EntityPathBase<Application> {

    private static final long serialVersionUID = -203884641;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QApplication application = new QApplication("application");

    public final DateTimePath<java.util.Date> applicationDate = createDateTime("applicationDate", java.util.Date.class);

    public final QCandidate candidate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInterview interview;

    public final QVacancy vacancy;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QApplication(String variable) {
        this(Application.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QApplication(Path<? extends Application> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApplication(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QApplication(PathMetadata<?> metadata, PathInits inits) {
        this(Application.class, metadata, inits);
    }

    public QApplication(Class<? extends Application> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.candidate = inits.isInitialized("candidate") ? new QCandidate(forProperty("candidate")) : null;
        this.interview = inits.isInitialized("interview") ? new QInterview(forProperty("interview"), inits.get("interview")) : null;
        this.vacancy = inits.isInitialized("vacancy") ? new QVacancy(forProperty("vacancy"), inits.get("vacancy")) : null;
    }

}

