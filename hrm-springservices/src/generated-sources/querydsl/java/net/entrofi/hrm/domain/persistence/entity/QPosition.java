package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPosition is a Querydsl query type for Position
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPosition extends EntityPathBase<Position> {

    private static final long serialVersionUID = 254828858;

    public static final QPosition position = new QPosition("position1");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QPosition(String variable) {
        super(Position.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QPosition(Path<? extends Position> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QPosition(PathMetadata<?> metadata) {
        super(Position.class, metadata);
    }

}

