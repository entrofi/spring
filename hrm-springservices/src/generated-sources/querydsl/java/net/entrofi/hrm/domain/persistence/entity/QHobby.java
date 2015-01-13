package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QHobby is a Querydsl query type for Hobby
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QHobby extends EntityPathBase<Hobby> {

    private static final long serialVersionUID = 454956897;

    public static final QHobby hobby = new QHobby("hobby");

    public final SetPath<Contact, QContact> contacts = this.<Contact, QContact>createSet("contacts", Contact.class, QContact.class, PathInits.DIRECT);

    public final StringPath description = createString("description");

    public final StringPath hobbyId = createString("hobbyId");

    public final StringPath name = createString("name");

    public QHobby(String variable) {
        super(Hobby.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QHobby(Path<? extends Hobby> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QHobby(PathMetadata<?> metadata) {
        super(Hobby.class, metadata);
    }

}

