package com.studentmanagement.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentmanagement.studentmanagement.dao.StudentDao;
import com.studentmanagement.studentmanagement.entity.Student;
import com.studentmanagement.studentmanagement.model.StudentVO;

//Student Service Implementation class
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	// Autowired to get the Object of StudentDao Implementation Class
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private Validator validator;

	@Autowired
	private EntityManager entityManager;

	// For Logging StudentServiceImpl Class
	private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	// To get the Students list
	@Override
	public List<StudentVO> getStudentsList() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> studentRoot = criteriaQuery.from(Student.class);
		criteriaQuery.select(studentRoot);
		TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Student> studentList = typedQuery.getResultList();

		List<StudentVO> studentVOList = new ArrayList<>();
		for (Student student : studentList) {
			StudentVO studentVO = new StudentVO();
			studentVO.setRollNo(student.getRollNo());
			studentVO.setAge(student.getAge());
			studentVO.setAddress(student.getAddress());
			studentVO.setName(student.getName());
			studentVO.setEmail(student.getEmail());
			studentVOList.add(studentVO);
		}

		logger.info("===Getting student list from database in StudentServiceImpl class ");
		return studentVOList;
	}

//	private JMapper<SupplierTypeVO, SupplierType> supplierMapper =
//		      new JMapper<SupplierTypeVO, SupplierType>(SupplierTypeVO.class, SupplierType.class);
//	supplierTypeVOList.add(supplierMapper.getDestination(supplierType));

	// To get the Student details
	@Override
	public StudentVO getStudent(long studentId) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> studentRoot = criteriaQuery.from(Student.class);
		criteriaQuery.select(studentRoot);
		criteriaQuery.where(criteriaBuilder.equal(studentRoot.get("rollNo"), studentId));
		TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Student> studentList = typedQuery.getResultList();

		// Optional<Student> studentOptional = studentDao.findById(studentId);

		StudentVO studentVO = null;
		if (studentList.isEmpty()) {

			return studentVO;
		} else {

			Student student = studentList.get(0);
			studentVO = student.toConvertStudentVo();

			logger.info("====Getting student detail from database in StudentServiceImpl class{}", studentId);
			return studentVO;
		}
	}

//	@Override
//	public StudentVO addStudent(StudentVO studentVO) {
//
//		Student student = new Student();
//
//		student.setAge(studentVO.getAge());
//		student.setAddress(studentVO.getAddress());
//		student.setName(studentVO.getName());
//		student.setEmail(studentVO.getEmail());
//
//		// Student studentobject = studentDao.save(student);
//		entityManager.persist(student);
//
//		logger.info("====Getting student  Saved detail from database in StudentServiceImpl class{}", studentVO);
//
////		StudentVO studentVOObject = new StudentVO();
////		studentVOObject.setRollNo(studentobject.getRollNo());
////		studentVOObject.setAge(studentobject.getAge());
////		studentVOObject.setAddress(studentobject.getAddress());
////		studentVOObject.setName(studentobject.getName());
////		studentVOObject.setEmail(studentobject.getEmail());
//
//		return studentVO;
//	}

	@Override
	public long deleteStudent(long studentId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaDelete<Student> criteriaDelete = criteriaBuilder.createCriteriaDelete(Student.class);
		Root<Student> from = criteriaDelete.from(Student.class);

		criteriaDelete.where(criteriaBuilder.equal(from.get("rollNo"), studentId));
		studentId = entityManager.createQuery(criteriaDelete).executeUpdate();
		return studentId;
	}

	@Override
	public StudentVO updateStudent(StudentVO studentVO, long studentId) {
		Student student = new Student();
		student.setRollNo(studentId);
		student.setAge(studentVO.getAge());
		student.setAddress(studentVO.getAddress());
		student.setName(studentVO.getName());
		student.setEmail(studentVO.getEmail());

		// Student studentobject = studentDao.save(student);
		Student studentobject = entityManager.merge(student);
		logger.info("====Getting student  Update detail from database in StudentServiceImpl class{}", studentVO);

		StudentVO studentVOObject = new StudentVO();
		studentVOObject.setRollNo(studentobject.getRollNo());
		studentVOObject.setAge(studentobject.getAge());
		studentVOObject.setAddress(studentobject.getAddress());
		studentVOObject.setName(studentobject.getName());
		studentVOObject.setEmail(studentobject.getEmail());

		return studentVOObject;
	}

//	@Override
//	public Student addStudent(Student student) {
//
//		entityManager.persist(student);
//
//		logger.info("====Getting student  Saved detail from database in StudentServiceImpl class{}", student);
//
//		return student;
//	}

	@Override
	public Student addStudent(StudentVO studentVO) {

		Student student = new Student();
		student.setAddress(studentVO.getAddress());
		student.setAge(studentVO.getAge());
		student.setEmail(studentVO.getEmail());
		student.setName(studentVO.getName());

		Set<ConstraintViolation<Student>> violations = validator.validate(student);

		if (!violations.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<Student> constraintViolation : violations) {
				sb.append(constraintViolation.getMessage()+",");
			}
			//throw new ConstraintViolationException(sb.toString(), violations);
			
			throw new ConstraintViolationException(sb.toString(), violations);
			
		}
		
	
		return student;

	}
}
