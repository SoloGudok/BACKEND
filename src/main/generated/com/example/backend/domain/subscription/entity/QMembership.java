package com.example.backend.domain.subscription.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMembership is a Querydsl query type for Membership
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMembership extends EntityPathBase<Membership> {

    private static final long serialVersionUID = 147156143L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembership membership = new QMembership("membership");

    public final com.example.backend.global.QBaseTimeEntity _super = new com.example.backend.global.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final com.example.backend.domain.user.entity.QUser user;

    public QMembership(String variable) {
        this(Membership.class, forVariable(variable), INITS);
    }

    public QMembership(Path<? extends Membership> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMembership(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMembership(PathMetadata metadata, PathInits inits) {
        this(Membership.class, metadata, inits);
    }

    public QMembership(Class<? extends Membership> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.backend.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

