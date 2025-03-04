package com.example.backend.subscription.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Description")
public class DescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long description_id;

    @CreationTimestamp
    private Timestamp created_at;

    private String account_email;
    private String account_password;
    private boolean approval;
}
