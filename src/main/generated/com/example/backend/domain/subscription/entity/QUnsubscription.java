package com.example.backend.domain.subscription.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUnsubscription is a Querydsl query type for Unsubscription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnsubscription extends EntityPathBase<Unsubscription> {

    private static final long serialVersionUID = 285743759L;

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

    public QUnsubscription(String variable) {
        super(Unsubscription.class, forVariable(variable));
    }

    public QUnsubscription(Path<? extends Unsubscription> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUnsubscription(PathMetadata metadata) {
        super(Unsubscription.class, metadata);
    }

}

