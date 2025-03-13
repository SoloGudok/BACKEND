package com.example.backend.domain.subscription.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscriptionImg is a Querydsl query type for SubscriptionImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubscriptionImg extends EntityPathBase<SubscriptionImg> {

    private static final long serialVersionUID = 660574253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscriptionImg subscriptionImg = new QSubscriptionImg("subscriptionImg");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSubscription subscription;

    public final StringPath subscriptionImgUrl = createString("subscriptionImgUrl");

    public QSubscriptionImg(String variable) {
        this(SubscriptionImg.class, forVariable(variable), INITS);
    }

    public QSubscriptionImg(Path<? extends SubscriptionImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscriptionImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscriptionImg(PathMetadata metadata, PathInits inits) {
        this(SubscriptionImg.class, metadata, inits);
    }

    public QSubscriptionImg(Class<? extends SubscriptionImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.subscription = inits.isInitialized("subscription") ? new QSubscription(forProperty("subscription"), inits.get("subscription")) : null;
    }

}

