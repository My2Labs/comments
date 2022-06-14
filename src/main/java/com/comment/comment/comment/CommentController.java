package com.comment.comment.comment;

import java.util.Map;
import java.util.HashMap;
import com.comment.comment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Iterable<Comment>> list() {
        Iterable<Comment> comments = commentService.list();
        return createHashPlural(comments);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Comment> read(@PathVariable Long id) {
        Comment comment = commentService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No comment with that ID"));
        return createHashSingular(comment);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Comment> create(@Validated @RequestBody Comment comment) {
        Comment createdComment = commentService.create(comment);
        return createHashSingular(createdComment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Comment> update(@RequestBody Comment comment, @PathVariable Long id) {
        Comment updatedComment = commentService
                .update(comment)
                .orElseThrow(() -> new ResourceNotFoundException("No comment with that ID"));

        return createHashSingular(updatedComment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    private Map<String, Comment> createHashSingular(Comment comment) {
        Map<String, Comment> response = new HashMap<String, Comment>();
        response.put("comment", comment);

        return response;
    }

    private Map<String, Iterable<Comment>> createHashPlural(Iterable<Comment> comments) {
        Map<String, Iterable<Comment>> response = new HashMap<String, Iterable<Comment>>();
        response.put("comments", comments);

        return response;
    }
}
