package it.redhat.reproducer.saaj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedWebappClassLoader;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        //TomcatEmbeddedWebappClassLoader tomcatEmbeddedWebappClassLoader = new TomcatEmbeddedWebappClassLoader();
        // Set the custom class loader for the current thread
        //Thread.currentThread().setContextClassLoader(tomcatEmbeddedWebappClassLoader);


        // Launch Spring Boot application
        SpringApplication.run(Application.class, args);
    }
    
}
