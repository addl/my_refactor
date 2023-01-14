package com.myrefactor.app;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.myrefactor.app.entity.Blog;
import com.myrefactor.app.service.BlogService;

/**
 *
@startuml
class MrEsSpringCustomMappingApplication [[java:com.myrefactor.app.MrEsSpringCustomMappingApplication]] {
    +{static} main(args: String[]): void
    +run(args: String[]): void
}
class BlogService {
}
MrEsSpringCustomMappingApplication --> "1" BlogService : blogService
interface CommandLineRunner {
}
CommandLineRunner <|.. MrEsSpringCustomMappingApplication
class Blog [[java:com.myrefactor.app.entity.Blog]] {
    -id: String
    -title: String
    -description: String
    -url: String
    -createdDate: Date
    +Blog()
}
interface BlogRepository [[java:com.myrefactor.app.repository.BlogRepository]] {
}
interface "ElasticsearchRepository<Blog,String>" as ElasticsearchRepository_Blog_String_ {
}
ElasticsearchRepository_Blog_String_ <|-- BlogRepository
class BlogService [[java:com.myrefactor.app.service.BlogService]] {
    -{static} log: org.slf4j.Logger
    +addBlog(entry: Blog): Blog
}
class BlogRepository {
}
BlogService --> "1" BlogRepository : jobRepository
@enduml
 *
 */
@SpringBootApplication
public class MrEsSpringCustomMappingApplication implements CommandLineRunner {

  @Autowired
  private BlogService blogService;

  public static void main(String[] args) {
    SpringApplication.run(MrEsSpringCustomMappingApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // create two Blog entities
    Blog entry_one = new Blog();
    entry_one.setTitle("Getting started with Spring Boot and Elasticsearch");
    entry_one.setDescription("A place where we talk about clean code! How to improve our code skills by applying best practices and most common design patterns in order to get a high scalable code.");
    entry_one.setUrl("http://myrefactor.com/es/posts/primeros-pasos-con-spring-boot-y-elasticsearch");
    
    Blog entry_two = new Blog();
    entry_two.setTitle("Learning Custom Mapping in Elastic Search");
    entry_two.setDescription("Define custom mappings instead of using the default mapping generation");
    entry_two.setUrl(
        "https://www.myrefactor.com/search?query=large+search+term&location=New+York&from_date=2022-01-01&to_date=2022-12-31&education=bachelor+university&category=information_and_technologies&experience=5+years&salary_min=75000&salary_max=100000&sort=relevance&page=1&size=20");
    
    // Indexing the blogs
    blogService.addBlog(entry_one);
    blogService.addBlog(entry_two);
  }

}
