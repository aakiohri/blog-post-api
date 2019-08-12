package com.porfolio.blogs.com.porfolio.blogs.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


public class Blog {

    @Id
    @NotNull(message = "Id cannot be null")
    @Pattern(regexp = "[A-Z]+-\\d+")
    @JsonProperty("id")
    private String blogId;

    @NotNull
    @JsonProperty("heading")
    private String blogName;

    @NotNull
    @JsonProperty("content")
    private String blogContent;

    @JsonProperty("created-date")
    private LocalDateTime timestamp;

    public Blog() {
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogName='" + blogName + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
