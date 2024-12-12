package com.itrifid.reeldekho.controller;

import com.itrifid.reeldekho.entity.Comment;
import com.itrifid.reeldekho.entity.Post;
import com.itrifid.reeldekho.service.PostService;
import com.itrifid.reeldekho.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;

    public PostController(PostService postService, S3Service s3Service) {
        this.postService = postService;
        this.s3Service = s3Service;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam String title,
                                           @RequestParam String content,
                                           @RequestParam int userId,
                                           @RequestParam String category,
                                           @RequestParam List<MultipartFile> multimedia) throws IOException {
        List<String> urls = multimedia.stream()
                .map(file -> {
                    try {
                        return s3Service.uploadFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException("Error uploading file", e);
                    }
                }).toList();

        Post post = Post.builder()
                .title(title)
                .content(content)
                .userId(UUID.randomUUID())
                .category(category)
                .multimediaUrls(urls)
                .build();

        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable UUID id, @RequestBody Comment comment) {
        return ResponseEntity.ok(postService.addComment(id, comment));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<String> likePost(@PathVariable UUID id) {
        postService.likePost(id);
        return ResponseEntity.ok("Post liked successfully");
    }
}
