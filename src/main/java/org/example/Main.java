package org.example;

import org.example.config.AppConfig;
import org.example.presentation.CommandProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
      //  CommandProcessor commandProcessor = new CommandProcessor();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CommandProcessor commandProcessor = context.getBean(CommandProcessor.class);
        commandProcessor.process();
    }
}