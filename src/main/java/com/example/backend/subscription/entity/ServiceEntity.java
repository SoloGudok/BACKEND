package com.example.backend.subscription.entity;

import com.example.backend.user.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Service")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long service_id;
    private String name;
    @CreationTimestamp
    private Timestamp created_at;
    private int price;
    private String content;
    private String homepage;

    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToOne
    @JoinColumn(name = "description_id")
    private DescriptionEntity description;

}
