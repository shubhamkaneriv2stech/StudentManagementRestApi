package com.studentmanagement.studentmanagement.service;

import java.util.List;

import com.studentmanagement.studentmanagement.entity.Student;
import com.studentmanagement.studentmanagement.model.StudentVO;

//StudentService interface
public interface StudentService {

	/**
	 * Method to get all studentlist
	 * @return
	 */
	public List<StudentVO> getStudentsList();

	/**
	 * Method to student details based on student id
	 * @param studentId
	 * @return
	 */
	public StudentVO getStudent(long studentId);

	//public StudentVO addStudent(StudentVO studentVO);
	

	public Student addStudent( StudentVO studentVO);

	public StudentVO updateStudent(StudentVO studentVO, long studentId);

	public long deleteStudent(long studentId);

}
