package com.porfolio.blogs.repository;

import com.porfolio.blogs.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, Long> {



}
