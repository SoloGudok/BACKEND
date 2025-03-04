package com.example.backend.domain.user.entity;

import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.sql.Timestamp;

@Getter
@Entity
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() where id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint")
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String password;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(nullable = true, columnDefinition = "varchar(50)")
    private int gender;

    @Column(nullable = true, columnDefinition = "TIMESTAMP")
    private Timestamp birth;

    @Column(nullable = false, columnDefinition = "varchar(255)", unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String phone;



}
