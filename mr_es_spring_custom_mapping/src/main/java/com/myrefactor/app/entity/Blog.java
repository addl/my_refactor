package com.myrefactor.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;

@Data
@Document(indexName = "blog_index", createIndex = false)
public class Blog {

  @Id
  private String id;
  private String title;
  private String description;
  private String url;

}
