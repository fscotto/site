/*
 * Copyright (C) 2019 Fabio Scotto di Santolo <fabio.scottodisantolo@gmail.com>
 *
 * This file is part of plague-blog.
 *
 * plague-blog is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * plague-blog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with plague-blog.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.fscotto.blog.config.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.eventbus.EventBus;

public class VertxModule extends AbstractModule {

  private final Vertx vertx;

  public VertxModule(Vertx vertx) {
    this.vertx = vertx;
  }

  @Override
  protected void configure() {
    bind(Vertx.class).toInstance(vertx);
  }

  @Provides
  @Singleton
  public EventBus getEventBus() {
    return this.vertx.eventBus();
  }
}
