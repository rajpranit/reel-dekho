package com.itrifid.reeldekho.repository;

import com.itrifid.reeldekho.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
