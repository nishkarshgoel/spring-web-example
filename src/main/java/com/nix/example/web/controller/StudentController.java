package com.nix.example.web.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nix.example.web.model.Student;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

	@GetMapping
	public List<Student> getStudent(){
		return List.of(new Student(1L, "nishkarsh goel", "nishkarshgoel@abc.com", LocalDate.of(1990, Month.JANUARY, 1), 31));
	}
}
