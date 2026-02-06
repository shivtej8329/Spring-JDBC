package com.mainapp;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.JdbcTemplate;

public class Crud {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void readSingleObject(int eid) {
		String sql = "select * from employee where eid = ?";
		
		Employee e = jdbcTemplate.queryForObject(sql, new RowMapperImpl(),eid);
		System.out.println(e);
	}

	public void readAllPojoBased(int eid) {

		String sql = "select * from employee";
		List<Employee> employees = jdbcTemplate.query(sql, new RowMapperImpl());

		for (Employee e : employees) {
			System.out.println(e);
		}
	}

	public void readAll() {

		String sql = "select * from employee";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> map : queryForList) {

			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			System.out.println();
		}
	}

	public void delete(int eid) {

		String sql = "delete from employee where eid = ?";
		int row = jdbcTemplate.update(sql, eid);

		if (row == 1) {
			System.out.println("data deleted");
		}
	}

	public void insert(int eid, String ename, String eaddress, int esalary) {

		String sql = "insert into employee(eid,ename,eaddress,esalary) values(?,?,?,?)";
		int row = jdbcTemplate.update(sql, eid, ename, eaddress, esalary);

		if (row == 1) {
			System.out.println("data inserted");
		}
	}

	public void update(int eid, int esalary) {

		String sql = "update employee set esalary = ? where eid = ?";
		int row = jdbcTemplate.update(sql, esalary, eid);

		if (row == 1) {
			System.out.println("data updated");
		}
	}
}
