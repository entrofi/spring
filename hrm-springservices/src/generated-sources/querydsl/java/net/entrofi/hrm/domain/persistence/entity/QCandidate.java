package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCandidate is a Querydsl query type for Candidate
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCandidate extends EntityPathBase<Candidate> {

    private static final long serialVersionUID = -1888694382;

    public static final QCandidate candidate = new QCandidate("candidate");

    public final QUser _super = new QUser(this);

    public final ListPath<Application, QApplication> applications = this.<Application, QApplication>createList("applications", Application.class, QApplication.class, PathInits.DIRECT);

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastName = _super.lastName;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final StringPath salt = _super.salt;

    //inherited
    public final StringPath username = _super.username;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QCandidate(String variable) {
        super(Candidate.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QCandidate(Path<? extends Candidate> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QCandidate(PathMetadata<?> metadata) {
        super(Candidate.class, metadata);
    }

}

