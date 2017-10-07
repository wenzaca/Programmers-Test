package com.programmers.apply.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.programmers.apply.entities.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    @Test
    public void findByNameTest() {
        // given
        Employee employee = new Employee("Wendler Zacariotto","wenzaca@gmail.com");
        entityManager.persist(employee);
        Employee employee1 = new Employee("Maria Silva","msilva@gmail.com");
        entityManager.persist(employee1);
        entityManager.flush();
     
        // when
        Employee found = employeeRepository.findByName(employee.getName());
     
        // then
        assertThat(found.getName())
          .isEqualTo(employee.getName());
    }
    
 
}