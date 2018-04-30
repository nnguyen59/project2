/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author celos
 */
@Component
public class employeeDAO {
     private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <T> void insert(T data) {
        employee demographic = (employee) data;
        this.jdbcTemplate.update(
                "insert into employee (id, firtname,lastname,ssn,salary) values (?,?,?,?,?)",
                demographic.getId(), demographic.getFirstname(),demographic.getLastname(),demographic.getSsn(),demographic.getSalary());
    }
    
     public void update(int a,double salary) {
     
        this.jdbcTemplate.update(
                "update employee set salary=? where id = ?",
                salary,a);
    }
     
     public employee findByCustomerId(int custId){
		 
	String sql = "SELECT * FROM employee WHERE id = ?";
 
	employee customer = (employee)getJdbcTemplate().queryForObject(
			sql, new Object[] { custId }, new BeanPropertyRowMapper(employee.class));
		
	return customer;
}
     
       public void delete(int a) {
        String sql = "DELETE FROM employee WHERE id = "+a;
        this.jdbcTemplate.execute(sql);
    }
    
 public List<employee> findAll(){	
	String sql = "SELECT * FROM employee";	 
	List<employee> customers = new ArrayList<employee>();
	
	List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
	for (Map row : rows) {
		employee customer = new employee();
		customer.setId((Integer)(row.get("id")));
		customer.setFirstname((String)row.get("firstname"));
		customer.setLastname((String)row.get("lastname"));
                customer.setSsn((String)row.get("ssn"));
                customer.setSalary((Double)row.get("salary"));
		customers.add(customer);
	}
		
	return customers;
        
}
    
}
