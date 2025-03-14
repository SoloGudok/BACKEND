package com.example.backend.domain.subscription.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMembershipDetail is a Querydsl query type for MembershipDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMembershipDetail extends EntityPathBase<MembershipDetail> {

    private static final long serialVersionUID = 1698034144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembershipDetail membershipDetail = new QMembershipDetail("membershipDetail");

    public final com.example.backend.global.QBaseTimeEntity _super = new com.example.backend.global.QBaseTimeEntity(this);

    public final BooleanPath combination = createBoolean("combination");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMembership membership;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QSubscription subscription;

    public QMembershipDetail(String variable) {
        this(MembershipDetail.class, forVariable(variable), INITS);
    }

    public QMembershipDetail(Path<? extends MembershipDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMembershipDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMembershipDetail(PathMetadata metadata, PathInits inits) {
        this(MembershipDetail.class, metadata, inits);
    }

    public QMembershipDetail(Class<? extends MembershipDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.membership = inits.isInitialized("membership") ? new QMembership(forProperty("membership"), inits.get("membership")) : null;
        this.subscription = inits.isInitialized("subscription") ? new QSubscription(forProperty("subscription"), inits.get("subscription")) : null;
    }

}

