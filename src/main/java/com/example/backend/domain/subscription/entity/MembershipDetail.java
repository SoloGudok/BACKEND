package com.example.backend.domain.subscription.entity;

import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Getter
@SQLDelete(sql = "UPDATE membership_detail SET deleted_at = NOW() where id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "membership_detail")
public class MembershipDetail extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(name = "combination", nullable = false)
    private boolean combination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id", nullable = false)
    private Membership membership;

    // ✅ 필요한 생성자 추가!
    public MembershipDetail(Membership membership, Subscription subscription, boolean combination) {
        this.membership = membership;
        this.subscription = subscription;
        this.combination = combination;
    }

}
