package com.example.demo.infrastructure.persistence;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentsByPost(Posts post);

    Optional<Comment> findByPostsIdAndId(Long postsId, Long id);
}
