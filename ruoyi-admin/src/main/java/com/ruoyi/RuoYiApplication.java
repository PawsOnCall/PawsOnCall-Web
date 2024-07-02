package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("  _____                    ____         _____      _ _ \n" +
                " |  __ \\                  / __ \\       / ____|    | | |\n" +
                " | |__) |_ ___      _____| |  | |_ __ | |     __ _| | |\n" +
                " |  ___/ _` \\ \\ /\\ / / __| |  | | '_ \\| |    / _` | | |\n" +
                " | |  | (_| |\\ V  V /\\__ \\ |__| | | | | |___| (_| | | |\n" +
                " |_|   \\__,_| \\_/\\_/ |___/\\____/|_| |_|\\_____\\__,_|_|_|\n" +
                "                                                       \n" +
                "                                                       ");
    }
}
