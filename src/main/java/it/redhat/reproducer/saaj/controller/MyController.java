package it.redhat.reproducer.saaj.controller;

import com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl;
import it.redhat.soap.ws.client.generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.SAAJMetaFactory;
import java.net.MalformedURLException;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class MyController {

    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(@RequestParam(required = false, defaultValue = "This is a test", name = "word") String testWord) throws MalformedURLException {

        SAAJMetaFactory saajMetaFactory = new SAAJMetaFactoryImpl();

        log.info("About to invoke service after creating a SaajMetaFactory with class {}", saajMetaFactory.getClass());
        TextCasing service = new TextCasing();
        String sword = service.getTextCasingSoap().invertStringCase(testWord);
        StringBuilder sb = new StringBuilder();
        classLoaderWalk(sb, this.getClass().getClassLoader());
        log.info("Service invoked");

        return String.format("{\"invertedCase\":\"%s\", \"classloader\" : [%s]}", sword, sb);
    }


    private void classLoaderWalk(StringBuilder result, ClassLoader classLoader) {
        String name = Optional.ofNullable(classLoader.getName()).orElse("unnamed");
        result.append(String.format("{\"name\":\"%s\", \"classLoaderClassName\":\"%s\"}", name, classLoader.getClass().getName()));
        if (classLoader.getParent() != null) {
            result.append(",");
            classLoaderWalk(result, classLoader.getParent());
        }

    }
}
