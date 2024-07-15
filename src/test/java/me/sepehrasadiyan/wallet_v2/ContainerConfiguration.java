package me.sepehrasadiyan.wallet_v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@TestConfiguration
@Profile("test")
@Testcontainers
@SpringBootTest
public class ContainerConfiguration {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    Environment environment;

    static {
        MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withReuse(true);

        mysql.start();

        System.setProperty("APP_DATABASE_URL", mysql.getJdbcUrl());
        System.setProperty("APP_DATABASE_USERNAME", mysql.getUsername());
        System.setProperty("APP_DATABASE_PASSWORD", mysql.getPassword());
        System.setProperty("testcontainers.mysql.port", mysql.getMappedPort(3306).toString());
    }

}
