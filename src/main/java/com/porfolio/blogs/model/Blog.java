package com.porfolio.blogs.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "blog")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Blog {

    @Id
    @JsonProperty("id")
    private Long blogId;

    @NotNull
    @JsonProperty("heading")
    private String blogName;

    @NotNull
    @JsonProperty("content")
    private String blogContent;

    @JsonProperty("created-date")
    private LocalDateTime timestamp;

    @Transient
    public static final String SEQUENCE_NAME = "database_sequences";


}
