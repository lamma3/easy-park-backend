package com.oocl.easyparkbackend.db;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("heroku")
public class HerokuPostgresConfig {
    
    @Bean
    public DataSource dataSource() throws URISyntaxException {
        //URI dbUri = new URI(System.getenv("postgres://ipiwgawjdcgplv:8960f31ff028963846d58cc55ef7bc18732cf75635dc5b4f0e150f3fdd4f81@ec2-34-225-82-212.compute-1.amazonaws.com:5432/d6dmm9j60cskhf"));
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();
    }
}
