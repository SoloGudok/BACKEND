package com.example.backend.domain.card.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCardImg is a Querydsl query type for CardImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardImg extends EntityPathBase<CardImg> {

    private static final long serialVersionUID = 1880155911L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardImg cardImg = new QCardImg("cardImg");

    public final QCard card;

    public final StringPath cardImgUrl = createString("cardImgUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCardImg(String variable) {
        this(CardImg.class, forVariable(variable), INITS);
    }

    public QCardImg(Path<? extends CardImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCardImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCardImg(PathMetadata metadata, PathInits inits) {
        this(CardImg.class, metadata, inits);
    }

    public QCardImg(Class<? extends CardImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.card = inits.isInitialized("card") ? new QCard(forProperty("card"), inits.get("card")) : null;
    }

}

