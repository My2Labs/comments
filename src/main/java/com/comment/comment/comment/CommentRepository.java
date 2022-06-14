package com.comment.comment.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    javax.xml.stream.events.Comment save(javax.xml.stream.events.Comment comment);
}
