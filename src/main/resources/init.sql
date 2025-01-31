CREATE TABLE IF NOT EXISTS employee(
   id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    salary INT NOT NULL,
    department_id INT NOT NULL,
    age INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS department(
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS leave_record(
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    day INT,
    reason VARCHAR(255),
    type VARCHAR(255),
    start_date VARCHAR(255),
    end_date VARCHAR(255),
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS performance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    performance_time VARCHAR(255) NOT NULL COMMENT '考核时间段，例如 2025-Q1',
    score INT NOT NULL,
    grade VARCHAR(10) NOT NULL COMMENT '绩效等级',
    comment TEXT,
    create_time VARCHAR(100),
    update_time VARCHAR(100)
    );



