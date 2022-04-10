package com.ydlclass.mapper;

import com.ydlclass.entity.Dept;
import com.ydlclass.entity.Employee;

import java.util.List;

/**
 * @author 小松
 */
public interface DeptMapper {

    List <Dept> select(Dept dept);

    List<Dept>selectAll(Dept dept);

    List <Dept> select2(Dept dept);

}
