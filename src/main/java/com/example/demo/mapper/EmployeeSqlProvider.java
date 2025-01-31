package com.example.demo.mapper;
import org.apache.ibatis.jdbc.SQL;

public class EmployeeSqlProvider {
    public String getEmployeesByCondition(String name, Integer age, Integer salary, Integer id) {
        SQL sqlBuilder = new SQL() {{
            SELECT("*");
            FROM("employee");
            StringBuilder whereClause = new StringBuilder();

            if (name!= null && !name.isEmpty()) {
                whereClause.append("name LIKE CONCAT('%', #{name}, '%')");
            }
            if (age!= null) {
                if (whereClause.length() > 0) {
                    whereClause.append(" AND ");
                }
                whereClause.append("age = #{age}");
            }
            if (salary!= null) {
                if (whereClause.length() > 0) {
                    whereClause.append(" AND ");
                }
                whereClause.append("salary > #{salary}");
            }
            if (id!= null) {
                if (whereClause.length() > 0) {
                    whereClause.append(" AND ");
                }
                whereClause.append("id = #{id}");
            }

            if (whereClause.length() > 0) {
                WHERE(whereClause.toString());
            }
        }};
        String sql = sqlBuilder.toString(); // 将 SQL 构建结果转换为字符串
        return sql;
    }
}



