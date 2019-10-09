package com.porfolio.blogs;

import com.porfolio.blogs.Model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class BlogModelListener extends AbstractMongoEventListener<Blog> {

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Blog> event) {
        if (event.getSource().getBlogId() < 1) {
            event.getSource().setBlogId(sequenceGeneratorService.generateSequence(Blog.SEQUENCE_NAME));
        }
    }
}
