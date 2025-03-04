package com.example.backend.subscription.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Service_img")
public class ServiceImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long service_img_id;
    private String service_img_name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="service_id")
    private ServiceEntity service;
}
