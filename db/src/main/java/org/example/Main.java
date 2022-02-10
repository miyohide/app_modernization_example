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
        Config config = ConfigFactory.load();
        String url = config.getString("datasource.url");
        String user = config.getString("datasource.user");
        String password = config.getString("datasource.password");
        Flyway flyway = Flyway.configure()
                        .dataSource(url, user, password).load();
        flyway.migrate();
    }

    public Main() {
        this.config = ConfigFactory.load();
        this.appConfig = new HashMap<>();
    }

    public Map<String, String> getConfig() {
        this.appConfig.put("url", this.config.getString("datasource.url"));
        this.appConfig.put("user", this.config.getString("datasource.user"));
        this.appConfig.put("password", this.config.getString("datasource.password"));

        return this.appConfig;
    }    
}
