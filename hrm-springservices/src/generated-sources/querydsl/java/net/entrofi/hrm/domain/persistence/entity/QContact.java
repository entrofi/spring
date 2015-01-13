package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QContact is a Querydsl query type for Contact
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContact extends EntityPathBase<Contact> {

    private static final long serialVersionUID = -1004038673;

    public static final QContact contact = new QContact("contact");

    public final DateTimePath<java.util.Date> birthDate = createDateTime("birthDate", java.util.Date.class);

    public final SetPath<ContactPhoneDetail, QContactPhoneDetail> contactDetails = this.<ContactPhoneDetail, QContactPhoneDetail>createSet("contactDetails", ContactPhoneDetail.class, QContactPhoneDetail.class, PathInits.DIRECT);

    public final StringPath description = createString("description");

    public final StringPath firstName = createString("firstName");

    public final SetPath<Hobby, QHobby> hobbies = this.<Hobby, QHobby>createSet("hobbies", Hobby.class, QHobby.class, PathInits.DIRECT);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final ArrayPath<Byte> photo = createArray("photo", Byte[].class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QContact(String variable) {
        super(Contact.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QContact(Path<? extends Contact> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QContact(PathMetadata<?> metadata) {
        super(Contact.class, metadata);
    }

}

