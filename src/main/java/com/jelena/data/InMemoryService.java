package com.jelena.data;

import com.jelena.business.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;


public class InMemoryService {
	
	// Map for in-memory storage
	private final Map<Long, Employee> map = new LinkedHashMap<Long, Employee>();
	// ID generation sequence
	private final AtomicLong sequence = new AtomicLong(930);
	
	
	public Employee findOne(Long id) {
		return map.get(id);
	}
	
	public List<Employee> findAll() {
		return new ArrayList<Employee>(map.values());
	}
	
	public Employee save(Employee emp) {
		if (emp.getId() == null) {
			emp.setId(sequence.getAndIncrement());
		}
		map.put(emp.getId(), emp);
		return emp;
	}
	
}
