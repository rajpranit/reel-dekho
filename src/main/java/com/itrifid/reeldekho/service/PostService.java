package com.itrifid.reeldekho.service;

import com.itrifid.reeldekho.entity.Comment;
import com.itrifid.reeldekho.entity.EngagementMetrics;
import com.itrifid.reeldekho.entity.Post;
import com.itrifid.reeldekho.repository.CommentRepository;
import com.itrifid.reeldekho.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Post createPost(Post post) {
        post.setEngagementMetrics(new EngagementMetrics(0,0,0)); // Initialize metrics
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(UUID id) {
        return postRepository.findById(id);
    }

    public Post updatePost(UUID id, Post updatedPost) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setCategory(updatedPost.getCategory());
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }

    public Comment addComment(UUID postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public void likePost(UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.getEngagementMetrics().setLikes(post.getEngagementMetrics().getLikes() + 1);
        postRepository.save(post);
    }
}
