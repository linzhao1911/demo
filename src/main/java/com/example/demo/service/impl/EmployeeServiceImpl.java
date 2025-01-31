package com.example.demo.service.impl;
import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.entity.EmployeeEntity;
import com.example.demo.model.vo.EmployeeVO;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.service.RedisService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public List<EmployeeVO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeMapper.getAllEmployees();
        List<EmployeeVO> employeeVOS = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeVO employeeVO = new EmployeeVO(employeeEntity);
            employeeVOS.add(employeeVO);
        }
        return employeeVOS;
    }

    @Override
    public EmployeeVO getEmployeeById(Integer id) {
        String cacheKey = "employee:detail:" + id;
        EmployeeVO employeeVO = (EmployeeVO)redisService.getValue(cacheKey);
        
        if (employeeVO == null) {
            EmployeeEntity employeeEntity = employeeMapper.getEmployeeById(id);
            if (employeeEntity == null) {
                throw new BusinessException("员工不存在");
            }
            employeeVO = new EmployeeVO(employeeEntity);
            redisService.setValue(cacheKey, employeeVO);
        }
        return employeeVO;
    }

    @Override
    public int insertEmployee(EmployeeDTO employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setDepartmentId(employee.getDepartmentId());
        employeeEntity.setEmployeeName(employee.getEmployeeName());
        employeeEntity.setSalary(employee.getSalary().intValue());
        employeeEntity.setAge(employee.getAge());
        int a = employeeMapper.insertEmployee(employeeEntity);
        String str = JSON.toJSONString(employeeEntity);
        kafkaProducerService.send("test-topic", str);
        return a;
    }

    @Override
    @Transactional
    public int updateEmployee(EmployeeDTO employee) {
        try {
            // 先检查员工是否存在
            EmployeeEntity existingEmployee = employeeMapper.getEmployeeById(employee.getId());
            if (existingEmployee == null) {
                throw new BusinessException("员工不存在");
            }

            EmployeeEntity employeeEntity = employee.toEntity();
            int result = employeeMapper.updateEmployee(employeeEntity);
            if (result > 0) {
                redisService.delete("employee:detail:" + employeeEntity.getId());
            }
            return result;
        } catch (Exception e) {
            log.error("更新员工信息失败", e);
            throw new BusinessException("更新员工信息失败");
        }
    }

    @Override
    public int deleteEmployee(Integer id) {
        return employeeMapper.deleteEmployee(id);
    }

    @Override
    @Cacheable(value = "EmployeeCache",key = "#id")
    public List<EmployeeVO> getEmployeesByCondition(String name, Integer age, Integer salary, Integer id) {
        List<EmployeeEntity> employeeEntities = employeeMapper.getEmployeesByCondition(name, age, salary, id);
        List<EmployeeVO> employeeVOS = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeVO employeeVO = new EmployeeVO(employeeEntity);
            employeeVOS.add(employeeVO);
        }

        return employeeVOS;
    }
}