package com.myrefactor.app.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.myrefactor.app.entity.Blog;

public interface BlogRepository extends ElasticsearchRepository<Blog, String> {

}
