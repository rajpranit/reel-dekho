package com.itrifid.reeldekho.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String content;
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}