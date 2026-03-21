package com.subham.studentRecord.repository;

import com.subham.studentRecord.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query(value = """
//            SELECT *
//            FROM student
//            WHERE email = :email
//            LIMIT 1
//            """, nativeQuery = true)
//    Optional<Student> findIfEmailExist(
//            @Param("email") String email);

    Optional<Student> findByEmail(String email);


//    @Query(value = """
//                        SELECT *
//                        FROM student
//                        WHERE department = :department
//            """, nativeQuery = true)
//    List<Student> findStudentsByDepartment(
//            @Param("department") String department);

    List<Student> findByDepartment(String department);

}
