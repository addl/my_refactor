package com.myrefactor.app.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import com.myrefactor.app.entity.Blog;
import com.myrefactor.app.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BlogService {

  @Value("classpath:blog_mappings.json")
  private Resource resourceMapping;

  @Autowired
  private RestHighLevelClient esClient;

  @Autowired
  private BlogRepository jobRepository;

  @PostConstruct
  public void initializeIndex() {
    this.createIndexIfNotPresent("blog_index");
  }

  public Blog addBlog(Blog entry) {
    log.info("Adding entry with title: {}", entry.getTitle());
    return this.jobRepository.save(entry);
  }


  private void createIndexIfNotPresent(String indexName) {
    try {
      GetIndexRequest indexRequest = new GetIndexRequest();
      indexRequest.indices("blog_index");
      boolean exists = esClient.indices().exists(indexRequest, RequestOptions.DEFAULT);
      if (!exists) {
        createIndex(indexName);
      }
    } catch (InterruptedException | ExecutionException | IOException e) {
      log.error("Can not create index {}", indexName);
    }
  }

  private void createIndex(String indexName)
      throws IOException, InterruptedException, ExecutionException {
    String mapping = getResouceasString(resourceMapping);
    CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
    indexRequest.mapping("doc", mapping, XContentType.JSON);
    CreateIndexResponse createIndexResponse =
        esClient.indices().create(indexRequest, RequestOptions.DEFAULT);
    if (createIndexResponse != null && !createIndexResponse.isAcknowledged()) {
      log.error("Can not create index {}", indexName);
    }
  }

  private String getResouceasString(Resource resourceMapping) {
    try (Reader reader = new InputStreamReader(resourceMapping.getInputStream(), "utf-8")) {
      return FileCopyUtils.copyToString(reader);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}
