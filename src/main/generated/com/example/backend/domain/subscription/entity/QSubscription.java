package com.example.backend.domain.subscription.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscription is a Querydsl query type for Subscription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubscription extends EntityPathBase<Subscription> {

    private static final long serialVersionUID = -493327434L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscription subscription = new QSubscription("subscription");

    public final com.example.backend.global.QBaseTimeEntity _super = new com.example.backend.global.QBaseTimeEntity(this);

    public final QCategory category;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath homepage = createString("homepage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<SubscriptionImg, QSubscriptionImg> images = this.<SubscriptionImg, QSubscriptionImg>createList("images", SubscriptionImg.class, QSubscriptionImg.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final BooleanPath selected = createBoolean("selected");

    public QSubscription(String variable) {
        this(Subscription.class, forVariable(variable), INITS);
    }

    public QSubscription(Path<? extends Subscription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscription(PathMetadata metadata, PathInits inits) {
        this(Subscription.class, metadata, inits);
    }

    public QSubscription(Class<? extends Subscription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

