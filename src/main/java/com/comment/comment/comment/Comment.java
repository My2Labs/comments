package com.comment.comment.comment;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Comment {
    @Autowired
    private CommentRepository commentRepository;

    public Iterable<Comment> list() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> update(Comment comment) {
        Optional<Comment> foundComment = commentRepository.findById(comment.getId());
        if (foundComment.isPresent()) {
            Comment updatedComment = foundComment.get();
            updatedComment.setSomeProperty(comment.getSomeProperty());
            commentRepository.save(updatedComment);
            return Optional.of(updatedComment);
        } else {
            return Optional.empty();
        }
    }

    private void setSomeProperty(Object someProperty) {
    }

    private Object getSomeProperty() {
        return null;
    }

    private Long getId() {
        return null;
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
