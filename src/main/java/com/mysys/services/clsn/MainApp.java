package com.mysys.services.clsn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class MainApp {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        HoldingsTree h = context.getBean("HoldingsTreeProvider", HoldingsTree.class);
        h.printTree();
        context.close();
    }
}
