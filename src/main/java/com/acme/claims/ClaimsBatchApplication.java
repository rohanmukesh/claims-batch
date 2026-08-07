package com.acme.claims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@SpringBootApplication
public class ClaimsBatchApplication {
  public static void main(String[] args) {
    SpringApplication.run(ClaimsBatchApplication.class, args);
  }

  @RestController
  static class HealthController {
    @GetMapping("/")
    Map<String, String> root() {
      return Map.of(
        "service", "claims-batch",
        "description", "Claims batch",
        "status", "ok"
      );
    }
  }
}
