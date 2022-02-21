package com.studentmanagement.studentmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.studentmanagement.entity.Student;
import com.studentmanagement.studentmanagement.exception.DataNotFoundException;
import com.studentmanagement.studentmanagement.model.StudentVO;
import com.studentmanagement.studentmanagement.service.StudentService;

//Student Controller Class
@RestController
@RequestMapping("/rest")
@CrossOrigin
public class StudentController {

	// Autowired to get the Object of StudentService Implementation Class
	@Autowired
	private StudentService studentService;

	// For Logging StudentController Class
	private Logger logger = LoggerFactory.getLogger(StudentController.class);

	// get the Students list
	@GetMapping("/students")
	public List<StudentVO> getStudentsList() {
		logger.info("===Getting student list from database in StudentController Class ");
		return studentService.getStudentsList();
	}

//	// get the Student details
//	@GetMapping("/students/{studentId}")
//	public ResponseEntity<Object> getStudent(@PathVariable Long studentId) {
//		try {
//
//			StudentVO studentVO=studentService.getStudent(studentId);
//			
//			if (studentVO==null) {
//				throw new DataNotFoundException("Data Not Found");
//			}
//			return ResponseEntity.ok().body(studentVO);
//		}
//
//		catch (DataNotFoundException e) {
//			logger.error("===Error While fetching in  StudentController {}", e);
//
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body("Student  With Roll No " + studentId + " not Found");
//		}
//		catch (Exception e) {
//
//			logger.error("===Error While fetching in  StudentController {}", e);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body("Student  With Roll No " + studentId + " not Found");
//		}
//	}
//	
//	

	// get the Student details
	@GetMapping("/students/{studentId}")
	public StudentVO getStudent(@PathVariable("studentId") long studentId) {

		StudentVO studentVO = studentService.getStudent(studentId);

		if (studentVO == null) {
			throw new DataNotFoundException(String.valueOf(studentId));
		}
		return studentVO;

	}

	
	
	
	
	
	
	
	
	
	

	@PostMapping("/students")
	public ResponseEntity<StudentVO> createUser(@RequestBody StudentVO studentVO) {

		Student student1 = studentService.addStudent(studentVO);
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}

	// Updating Student Data
	@PutMapping("/students/{studentId}")
	public StudentVO updateStudent(@RequestBody StudentVO studentVO, @PathVariable("studentId") long studentId) {
		return studentService.updateStudent(studentVO, studentId);

	}

	// Delete Student Data
	@DeleteMapping("/students/{studentId}")
	public StudentVO deleteStudent(@PathVariable("studentId") long studentId) {
		long deleteRow = studentService.deleteStudent(studentId);

		if (deleteRow < 1) {
			throw new DataNotFoundException(String.valueOf(studentId));
		}
		return null;
	}

	
	
	
	
}
