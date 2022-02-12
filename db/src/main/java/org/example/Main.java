package org.example;

import java.util.HashMap;
import java.util.Map;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.flywaydb.core.Flyway;

public class Main {
    private Config config;
    private Map<String, String> appConfig;

    public static void main(String[] args) {
        Main m = new Main();
        m.loadConfig();
        m.runDBMigrate();
    }

    public Main() {
        this.config = ConfigFactory.load();
        this.appConfig = new HashMap<>();
    }

    public void loadConfig() {
        this.appConfig.put("url", this.config.getString("datasource.url"));
        this.appConfig.put("user", this.config.getString("datasource.user"));
        this.appConfig.put("password", this.config.getString("datasource.password"));
    }

    public Map<String, String> getAppConfig() {
        return appConfig;
    }

    public void runDBMigrate() {
        Flyway flyway = Flyway.configure()
                        .dataSource(this.appConfig.get("url"),
                        this.appConfig.get("user"),
                        this.appConfig.get("password")).load();
        flyway.migrate();
    }
}
