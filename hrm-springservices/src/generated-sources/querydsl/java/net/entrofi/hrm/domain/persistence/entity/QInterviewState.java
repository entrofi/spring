package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QInterviewState is a Querydsl query type for InterviewState
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QInterviewState extends EntityPathBase<InterviewState> {

    private static final long serialVersionUID = -541555999;

    public static final QInterviewState interviewState = new QInterviewState("interviewState");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath shortName = createString("shortName");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QInterviewState(String variable) {
        super(InterviewState.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QInterviewState(Path<? extends InterviewState> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QInterviewState(PathMetadata<?> metadata) {
        super(InterviewState.class, metadata);
    }

}

