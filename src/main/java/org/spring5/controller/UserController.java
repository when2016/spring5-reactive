package org.spring5.controller;

import org.spring5.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * http://www.lupaworld.com/article-263775-1.html Created by wanghongen on 2018/3/4
 */
//@RestController("/api/v2")
public class UserController {

  @GetMapping("/user")
  public Mono<ServerResponse> handlerGetUsers() {
    return WebClient.create("http://localhost:9000").get().uri("/api/user")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .flatMap(resp -> ServerResponse.ok().body(resp.bodyToFlux(User.class), User.class));
  }

  @GetMapping("/user/{id}")
  public Mono<ServerResponse> handlerGetUserById(@PathVariable String id) {
    return WebClient.create("http://localhost:9000").get().uri("/api/user/" + id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .flatMap(resp -> ServerResponse.ok().body(resp.bodyToMono(User.class), User.class));
  }
}
