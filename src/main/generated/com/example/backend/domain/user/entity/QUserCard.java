package com.example.backend.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCard is a Querydsl query type for UserCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCard extends EntityPathBase<User> {

    private static final long serialVersionUID = -1344124350L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCard userCard = new QUserCard("userCard");

    public final com.example.backend.domain.card.entity.QCard card;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QUserCard(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUserCard(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCard(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUserCard(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.card = inits.isInitialized("card") ? new com.example.backend.domain.card.entity.QCard(forProperty("card"), inits.get("card")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

