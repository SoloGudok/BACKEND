package com.example.backend.domain.subscription.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUnsubscription is a Querydsl query type for Unsubscription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnsubscription extends EntityPathBase<Unsubscription> {

    private static final long serialVersionUID = 285743759L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUnsubscription unsubscription = new QUnsubscription("unsubscription");

    public final com.example.backend.global.QBaseTimeEntity _super = new com.example.backend.global.QBaseTimeEntity(this);

    public final StringPath accountEmail = createString("accountEmail");

    public final StringPath accountPassword = createString("accountPassword");

    public final BooleanPath approval = createBoolean("approval");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QSubscription subscription;

    public final com.example.backend.domain.user.entity.QUser user;

    public QUnsubscription(String variable) {
        this(Unsubscription.class, forVariable(variable), INITS);
    }

    public QUnsubscription(Path<? extends Unsubscription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUnsubscription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUnsubscription(PathMetadata metadata, PathInits inits) {
        this(Unsubscription.class, metadata, inits);
    }

    public QUnsubscription(Class<? extends Unsubscription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.subscription = inits.isInitialized("subscription") ? new QSubscription(forProperty("subscription"), inits.get("subscription")) : null;
        this.user = inits.isInitialized("user") ? new com.example.backend.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

