package it.plague.blog.database;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.vertx.junit5.Timeout;
import io.vertx.junit5.VertxTestContext;
import it.plague.blog.config.Constant;
import it.plague.blog.support.verticle.AbstractVerticleTestSuite;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.TimeUnit;

class ArticleDatabaseVerticleTest extends AbstractVerticleTestSuite {

  @Inject
  @Named(Constant.HTTP_SERVER_HOST)
  String host;

  @Inject
  @Named(Constant.HTTP_SERVER_PORT)
  String port;

  @BeforeEach
  @Timeout(value = 3, timeUnit = TimeUnit.SECONDS)
  void setUp(VertxTestContext context) {
    deployVerticle(ArticleDatabaseVerticle.class.getName(), context.succeeding(id -> context.completeNow()));
  }

}