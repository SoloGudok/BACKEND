package com.example.backend.domain.user.entity;

import com.example.backend.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    private String gender;

    @Column(nullable = true, columnDefinition = "TIMESTAMP")
    private Timestamp birth;

    @Column(nullable = false, columnDefinition = "varchar(255)", unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String phone;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserCard> userCards = new ArrayList<>();
    public int calculateAge() {
        if (birth == null) {
            return 0;  // birth가 null인 경우, 예외 처리 필요
        }
        LocalDate birthDate = birth.toLocalDateTime().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }

}
