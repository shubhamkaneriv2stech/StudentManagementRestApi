package com.studentmanagement.studentmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.studentmanagement.entity.Student;

//Repository for getting data from database
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

}
