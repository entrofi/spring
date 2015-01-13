package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QContactPhoneDetail is a Querydsl query type for ContactPhoneDetail
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContactPhoneDetail extends EntityPathBase<ContactPhoneDetail> {

    private static final long serialVersionUID = -1947054608;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QContactPhoneDetail contactPhoneDetail = new QContactPhoneDetail("contactPhoneDetail");

    public final QContact contact;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath phoneType = createString("phoneType");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QContactPhoneDetail(String variable) {
        this(ContactPhoneDetail.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QContactPhoneDetail(Path<? extends ContactPhoneDetail> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContactPhoneDetail(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContactPhoneDetail(PathMetadata<?> metadata, PathInits inits) {
        this(ContactPhoneDetail.class, metadata, inits);
    }

    public QContactPhoneDetail(Class<? extends ContactPhoneDetail> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contact = inits.isInitialized("contact") ? new QContact(forProperty("contact")) : null;
    }

}

