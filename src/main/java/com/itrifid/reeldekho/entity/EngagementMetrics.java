package com.itrifid.reeldekho.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EngagementMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int likes;
    private int shares;
    private int views;

    public EngagementMetrics(int likes, int shares, int views) {
        this.likes = likes;
        this.shares = shares;
        this.views = views;
    }

}
