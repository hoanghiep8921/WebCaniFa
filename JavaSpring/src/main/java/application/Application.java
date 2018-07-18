package application;


import application.service.FileStorageService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


@SpringBootApplication
public class Application {

private static final Logger logger= LogManager.getLogger(Application.class);

        @Resource
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
