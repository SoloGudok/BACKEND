package com.example.backend.domain.subscription.entity;

import com.example.backend.domain.user.entity.User;
import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;


@Getter
@Entity
@SQLDelete(sql = "UPDATE membership SET deleted_at = NOW() where id = ?")
@Table(name = "Membership")
public class Membership extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(nullable = false)
    private int status;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


}
