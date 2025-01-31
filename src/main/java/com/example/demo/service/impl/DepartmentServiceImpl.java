package com.example.demo.service.impl;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.model.dto.DepartmentDTO;
import com.example.demo.model.entity.DepartmentEntity;
import com.example.demo.model.vo.DepartmentVO;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

//    @Override
//    public List<DepartmentVO> getAllDepartments() {
//        List<DepartmentEntity> departmentEntity = departmentMapper.getAllDepartments();
//        List<DepartmentVO> departmentVOS = new ArrayList<>();
//        departmentEntity.forEach(d -> departmentVOS.add(new DepartmentEntity.toVO(departmentEntity)));
//        return departmentVOS;
//    }

    @Override
    public List<DepartmentVO> getAllDepartments() {
        List<DepartmentEntity> departmentEntity = departmentMapper.getAllDepartments();
        List<DepartmentVO> departmentVOS = new ArrayList<>();
        for (DepartmentEntity departmentEntity1 : departmentEntity) {
            departmentVOS.add(new DepartmentVO(departmentEntity1));
        }
          return departmentVOS;
    }

    @Override
    public DepartmentVO getDepartmentById(Integer id) {
        DepartmentEntity departmentEntity = departmentMapper.getDepartmentById(id);
        return departmentEntity.toVO(departmentEntity);
    }

    @Override
    public int insertDepartment(DepartmentDTO department) {
        return departmentMapper.insertDepartment(department.toEntity());
    }

    @Override
    public int updateDepartment(DepartmentDTO department) {
        return departmentMapper.updateDepartment(department.toEntity());
    }

    @Override
    public int deleteDepartment(Integer id) {
        return departmentMapper.deleteDepartment(id);
    }
}
