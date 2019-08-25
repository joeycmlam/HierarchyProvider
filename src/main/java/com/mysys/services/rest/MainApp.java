package com.mysys.services.rest;

import com.mysys.services.clsn.AppConfig;
import com.mysys.services.clsn.HoldingsTree;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class MainApp {

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args){
        System.out.println("Testing...");

        SpringApplication.run(MainApp.class, args);

    }

    @RequestMapping("/")
    public String version() {

        return this.appName + "-0.0.1";
    }

    @RequestMapping("/tree")
    public String tree() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HoldingsTree h = context.getBean("HoldingsTreeProvider", HoldingsTree.class);
        return h.buildTree();
    }

}
