package com.porfolio.blogs.Repository;

import com.porfolio.blogs.Model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, Long> {



}
