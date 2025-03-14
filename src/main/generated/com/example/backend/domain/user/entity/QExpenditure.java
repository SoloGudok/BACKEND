package com.example.backend.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpenditure is a Querydsl query type for Expenditure
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExpenditure extends EntityPathBase<Expenditure> {

    private static final long serialVersionUID = 1167700824L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExpenditure expenditure = new QExpenditure("expenditure");

    public final com.example.backend.global.QBaseTimeEntity _super = new com.example.backend.global.QBaseTimeEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final com.example.backend.domain.subscription.entity.QCategory category;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.example.backend.domain.subscription.entity.QSubscription subscription;

    public final QUser user;

    public QExpenditure(String variable) {
        this(Expenditure.class, forVariable(variable), INITS);
    }

    public QExpenditure(Path<? extends Expenditure> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExpenditure(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExpenditure(PathMetadata metadata, PathInits inits) {
        this(Expenditure.class, metadata, inits);
    }

    public QExpenditure(Class<? extends Expenditure> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.example.backend.domain.subscription.entity.QCategory(forProperty("category")) : null;
        this.subscription = inits.isInitialized("subscription") ? new com.example.backend.domain.subscription.entity.QSubscription(forProperty("subscription"), inits.get("subscription")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

