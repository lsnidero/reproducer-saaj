package it.redhat.reproducer.saaj.controller;

import it.redhat.soap.ws.client.generated.Student;
import it.redhat.soap.ws.client.generated.StudentDetailsPortService;
import it.redhat.soap.ws.client.generated.StudentDetailsRequest;
import it.redhat.soap.ws.client.generated.StudentDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/test")
    public  String hello(@RequestParam(required = false) String pName){
        String name = Optional.ofNullable(pName).orElse("Sajal");

        StudentDetailsPortService service = new StudentDetailsPortService();
        StudentDetailsRequest request = new StudentDetailsRequest();
        request.setName(name);
        StudentDetailsResponse studentDetailsResponse = service.getStudentDetailsPortSoap11().studentDetails(request);
        Student student = studentDetailsResponse.getStudent();


        return String.format("%s, %s. This is loaded with the %s classloader",student.getName(),student.getAddress(), this.getClass().getClassLoader().getClass().getName());
    }
}
