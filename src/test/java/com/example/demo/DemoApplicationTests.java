package com.example.demo;

import com.example.demo.mapper.PerformanceMapper;
import com.example.demo.model.dto.PerformanceDTO;
import com.example.demo.model.entity.PerformanceEntity;
import com.example.demo.service.impl.PerformanceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Mock
    private PerformanceMapper performanceMapper;

    @InjectMocks
    private PerformanceServiceImpl performanceServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addPerformance() throws Exception {
        PerformanceDTO performanceDTO = new PerformanceDTO();
        performanceDTO.setEmployeeId(1);
        performanceDTO.setScore(50);
        performanceDTO.setComments("sss");
        performanceDTO.setPerformanceTime("2025-Q3");
        String str = objectMapper.writeValueAsString(performanceDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/performance/add")
                .contentType(MediaType.APPLICATION_JSON).content(str)).andExpect(MockMvcResultMatchers.status().isOk());
        performanceServiceImpl.addPerformanceRecord(performanceDTO);
    }

    @Test
    void getPerformanceByEmployeeId() {
        // Arrange
        PerformanceEntity performanceEntity = new PerformanceEntity();
        performanceEntity.setEmployeeId(100);
        performanceEntity.setScore(95);// 使用枚举值
        performanceEntity.setPerformanceTime("2025-Q1");


        // Act
        List<PerformanceEntity> result = performanceServiceImpl.getPerformanceRecords(100, null);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(1, result.size(), "The result size should be 1");
    }

}
