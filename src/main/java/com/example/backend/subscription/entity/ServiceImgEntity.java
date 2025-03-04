package com.example.backend.subscription.entity;

import com.example.backend.user.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Service_img")
public class ServiceImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long service_img_id;
    private String service_img_url;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="service_id")
    private ServiceEntity service;
}
