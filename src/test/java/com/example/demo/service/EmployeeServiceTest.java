package com.example.demo.service;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.dto.EmployeeDTO;
import com.example.demo.model.entity.EmployeeEntity;
import com.example.demo.model.vo.EmployeeVO;
import com.example.demo.service.impl.EmployeeServiceImpl;
import com.example.demo.service.RedisService;
import com.example.demo.service.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeMapper employeeMapper;
    
    @Mock
    private RedisService redisService;
    
    @Mock
    private KafkaProducerService kafkaProducerService;
    
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    
    @Test
    public void testGetEmployeeById() {
        EmployeeEntity mockEntity = new EmployeeEntity();
        mockEntity.setId(1);
        mockEntity.setEmployeeName("Test Employee");
        mockEntity.setSalary(5000);
        mockEntity.setAge(30);
        mockEntity.setDepartmentId(1);
        
        when(employeeMapper.getEmployeeById(1)).thenReturn(mockEntity);
        when(redisService.getValue(eq("employee:detail:1"))).thenReturn(null);
        
        EmployeeVO result = employeeService.getEmployeeById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Employee", result.getEmployeeName());
        
        verify(redisService).setValue(eq("employee:detail:1"), any());
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(1);
        dto.setEmployeeName("Test Employee");
        dto.setSalary(5000.0);
        dto.setAge(30);
        dto.setDepartmentId(1);
        
        EmployeeEntity existingEmployee = new EmployeeEntity();
        existingEmployee.setId(1);
        when(employeeMapper.getEmployeeById(1)).thenReturn(existingEmployee);
        
        when(employeeMapper.updateEmployee(any(EmployeeEntity.class))).thenReturn(1);
        
        int result = employeeService.updateEmployee(dto);
        assertEquals(1, result);
        
        verify(redisService).delete(anyString());
    }
    
    @Test
    public void testInsertEmployee() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeName("New Employee");
        dto.setSalary(4000.0);
        dto.setAge(25);
        dto.setDepartmentId(1);
        
        when(employeeMapper.insertEmployee(any(EmployeeEntity.class))).thenReturn(1);
        doNothing().when(kafkaProducerService).send(anyString(), anyString());
        
        int result = employeeService.insertEmployee(dto);
        assertEquals(1, result);
        
        verify(kafkaProducerService).send(anyString(), anyString());
    }
} 