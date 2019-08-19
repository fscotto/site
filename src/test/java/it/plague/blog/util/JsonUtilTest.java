package it.plague.blog.util;

import io.vertx.core.json.JsonObject;
import it.plague.blog.domain.Article;
import it.plague.blog.domain.Author;
import it.plague.blog.domain.User;
import it.plague.blog.support.annotation.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
class JsonUtilTest {

  private JsonObject json;
  private Article object;

  @BeforeEach
  void setUp() {
    var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    var now = LocalDateTime.now().withNano(0);
    this.json = new JsonObject()
      .put("id", 1)
      .put("createdBy", new JsonObject()
        .put("id", 1)
        .put("name", "J.R.")
        .put("lastName", "Tolkien")
        .put("user", new JsonObject()
          .put("id", 1)
          .put("username", "joe")
          .put("password", "secret")))
      .put("created", formatter.format(now))
      .putNull("modifiedBy")
      .putNull("modified")
      .put("title", "Lord of the rings")
      .put("content", "");

    this.object = Builder.of(Article::new)
      .with(Article::setId, 1L)
      .with(Article::setCreatedBy, new Author(1L, "J.R.", "Tolkien", new User(1L, "joe", "secret")))
      .with(Article::setCreated, now)
      .with(Article::setTitle, "Lord of the rings")
      .with(Article::setContent, "")
      .build();
  }

  @Test
  @DisplayName("Should be convert from JsonObject to Object")
  void shouldBeConvertFromJsonToObject() {
    var actual = JsonUtil.fromJson(json, Article.class).get();
    assertThat(actual).isEqualTo(object);
  }

  @Test
  @DisplayName("Should be convert from Object to JsonObject")
  void shouldBeConvertFromObjectToJson() {
    var actual = JsonUtil.toJson(object);
    assertThat(actual).isEqualTo(json);
  }

}