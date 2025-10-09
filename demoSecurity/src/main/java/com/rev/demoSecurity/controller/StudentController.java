package com.rev.demoSecurity.controller;

import com.rev.demoSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    List<Student> x=new ArrayList<>(List.of(new Student(1,"Revan",99), new Student(2,"Revan",100)));
    @GetMapping("/students")
    public List<Student> getStudents() {
        return x;
    }
    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        x.add(student);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken( HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
