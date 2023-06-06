package net.springmicroservices.departmentservice.repository;

import net.springmicroservices.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT * FROM departments WHERE department_code=?", nativeQuery = true)
    Department findByDepartmentCode(String department_code);
}