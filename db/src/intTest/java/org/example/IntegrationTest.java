package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class IntegrationTest {
  @Test
  void withEnvironmentVariable() {
    Main m = new Main();
    m.loadConfig();
    Map<String, String> appConfig = m.getAppConfig();
    assertEquals(appConfig.get("url"), "env_db_url");
    assertEquals(appConfig.get("user"), "env_db_user");
    assertEquals(appConfig.get("password"), "env_db_password");
  }
}
