package com.itrifid.reeldekho.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String content;

    @ElementCollection
    private List<String> multimediaUrls; // URLs of uploaded images/videos in S3

    private UUID userId;
    private String category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EngagementMetrics engagementMetrics;
}