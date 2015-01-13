package net.entrofi.hrm.domain.persistence.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDepartment is a Querydsl query type for Department
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDepartment extends EntityPathBase<Department> {

    private static final long serialVersionUID = -455455965;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QDepartment department = new QDepartment("department");

    public final SetPath<Department, QDepartment> departments = this.<Department, QDepartment>createSet("departments", Department.class, QDepartment.class, PathInits.DIRECT);

    public final StringPath description = createString("description");

    public final SetPath<Employee, QEmployee> employees = this.<Employee, QEmployee>createSet("employees", Employee.class, QEmployee.class, PathInits.DIRECT);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QDepartment parent;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QDepartment(String variable) {
        this(Department.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QDepartment(Path<? extends Department> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDepartment(PathMetadata<?> metadata, PathInits inits) {
        this(Department.class, metadata, inits);
    }

    public QDepartment(Class<? extends Department> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QDepartment(forProperty("parent"), inits.get("parent")) : null;
    }

}

