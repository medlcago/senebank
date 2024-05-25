package org.backend.senebank.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentConfig {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();

        String dbURL = dotenv.get("DB_URL");
        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");
        String jwtSecret = dotenv.get("JWT_SECRET");


        System.setProperty("DB_URL", dbURL);
        System.setProperty("DB_USERNAME", dbUsername);
        System.setProperty("DB_PASSWORD", dbPassword);
        System.setProperty("JWT_SECRET", jwtSecret);
    }
}