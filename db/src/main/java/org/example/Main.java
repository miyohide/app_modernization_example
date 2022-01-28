package org.example;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Config config = ConfigFactory.load();
        String url = config.getString("datasource.url");
        String user = config.getString("datasource.user");
        String password = config.getString("datasource.password");
        Flyway flyway = Flyway.configure()
                        .dataSource(url, user, password).load();
        flyway.migrate();
    }
}
