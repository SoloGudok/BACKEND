package com.example.backend.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAvgExpenditure is a Querydsl query type for AvgExpenditure
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAvgExpenditure extends EntityPathBase<AvgExpenditure> {

    private static final long serialVersionUID = -1230243116L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAvgExpenditure avgExpenditure = new QAvgExpenditure("avgExpenditure");

    public final com.example.backend.global.QBaseTimeEntity _super = new com.example.backend.global.QBaseTimeEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final com.example.backend.domain.subscription.entity.QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> nonSubAvgExpenditure = createNumber("nonSubAvgExpenditure", Integer.class);

    public final NumberPath<Integer> subAvgExpenditure = createNumber("subAvgExpenditure", Integer.class);

    public QAvgExpenditure(String variable) {
        this(AvgExpenditure.class, forVariable(variable), INITS);
    }

    public QAvgExpenditure(Path<? extends AvgExpenditure> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAvgExpenditure(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAvgExpenditure(PathMetadata metadata, PathInits inits) {
        this(AvgExpenditure.class, metadata, inits);
    }

    public QAvgExpenditure(Class<? extends AvgExpenditure> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.example.backend.domain.subscription.entity.QCategory(forProperty("category")) : null;
    }

}

