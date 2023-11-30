package hello.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import  hello.constants.CommonConstants;
import hello.model.Employee;

@Component
public class BigQueryUtils {

	public Map<String, Object> getRow(Employee employee){
		HashMap<String, Object> rowMap= new HashMap<>();
		rowMap.put(CommonConstants.EMPLOYEE_ID, employee.getEmployeeId());
		rowMap.put(CommonConstants.FIRST_NAME, employee.getFirstName());
		rowMap.put(CommonConstants.LAST_NAME, employee.getLastName());
		rowMap.put(CommonConstants.EMAIL, employee.getEmail());
		return rowMap;
	}
}
