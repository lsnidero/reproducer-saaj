package it.redhat.reproducer.saaj.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.redhat.soap.ws.client.generated.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping(value = "/test")
    public String test(@RequestParam(required = false, defaultValue = "This is a test") String testWord) throws MalformedURLException {
        TextCasing service = new TextCasing();
        String sword = service.getTextCasingSoap().invertStringCase(testWord);
        StringBuilder sb = new StringBuilder();
        classLoaderWalk(sb, this.getClass().getClassLoader());
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
