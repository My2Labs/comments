package com.comment.comment.comment;

import java.util.Optional;
import java.util.Map;
import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    public Map<String, Iterable<com.comment.comment.comment.Comment>> list;

    public Iterable<com.comment.comment.comment.Comment> list() {
        return commentRepository.findAll();
    }

    public Optional<com.comment.comment.comment.Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> update(Comment comment) {
        Optional<Comment> foundResource = commentRepository.findById(comment.getId());

        if (foundResource.isPresent()) {
            Comment updatedResource = foundResource.get();
            updatedResource.setName(comment.getName());
            updatedResource.setEmail(comment.getEmail());
            updatedResource.setComment(comment.getComment());
            commentRepository.save(updatedResource);
            return Optional.of(updatedResource);
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
