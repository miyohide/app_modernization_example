package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  void noEnvironmentVariable() {
    Main m = new Main();
    m.loadConfig();
    Map<String, String> appConfig = m.getAppConfig();
    assertEquals(appConfig.get("url"), "jdbc:postgresql://localhost:5432/azureuser");
    assertEquals(appConfig.get("user"), "azureuser");
    assertEquals(appConfig.get("password"), "superverystrongpassword");
  }
}
