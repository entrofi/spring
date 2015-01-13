package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QInterview is a Querydsl query type for Interview
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInterview extends EntityPathBase<Interview> {

    private static final long serialVersionUID = -1894249584;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QInterview interview = new QInterview("interview");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInterviewState interviewState;

    public final StringPath location = createString("location");

    public final DateTimePath<java.util.Date> plannedDate = createDateTime("plannedDate", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QInterview(String variable) {
        this(Interview.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QInterview(Path<? extends Interview> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterview(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterview(PathMetadata<?> metadata, PathInits inits) {
        this(Interview.class, metadata, inits);
    }

    public QInterview(Class<? extends Interview> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interviewState = inits.isInitialized("interviewState") ? new QInterviewState(forProperty("interviewState")) : null;
    }

}

