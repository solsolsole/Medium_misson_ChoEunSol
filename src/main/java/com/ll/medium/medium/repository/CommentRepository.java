package com.ll.medium.medium.repository;

import com.ll.medium.medium.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
