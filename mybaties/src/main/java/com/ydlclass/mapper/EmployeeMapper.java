package com.ydlclass.mapper;

import com.ydlclass.entity.Employee;

import java.util.List;

/**
 * @author 小松
 */
public interface EmployeeMapper {

    List <Employee> select(Employee employee);

    List <Employee> select2(Employee employee);


    List <Employee> selectByDid(int id);


}
