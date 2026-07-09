CREATE DATABASE orangehrm_clone;
USE orangehrm_clone;

-- =========================
-- ROLES
-- =========================
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
);

INSERT INTO roles (role_name) VALUES
('Admin'),
('HR Manager'),
('Supervisor'),
('Employee');

-- =========================
-- DEPARTMENTS
-- =========================
CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

INSERT INTO departments (name, description) VALUES
('IT', 'Information Technology'),
('HR', 'Human Resources'),
('Finance', 'Finance Department'),
('Marketing', 'Marketing Department');

-- =========================
-- LOCATIONS
-- =========================
CREATE TABLE locations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    country VARCHAR(100),
    city VARCHAR(100),
    address TEXT
);

INSERT INTO locations (name, country, city, address) VALUES
('Head Office', 'Egypt', 'Alexandria', 'Smouha'),
('Branch Office', 'Egypt', 'Cairo', 'Nasr City');

-- =========================
-- JOB TITLES
-- =========================
CREATE TABLE job_titles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    description TEXT
);

INSERT INTO job_titles (title, description) VALUES
('Software Engineer', 'Develop software'),
('HR Specialist', 'Manage HR tasks'),
('Accountant', 'Financial operations'),
('Project Manager', 'Manage projects');

-- =========================
-- EMPLOYMENT STATUS
-- =========================
CREATE TABLE employment_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status_name VARCHAR(100)
);

INSERT INTO employment_status (status_name) VALUES
('Full Time'),
('Part Time'),
('Contract'),
('Intern');

-- =========================
-- EMPLOYEES
-- =========================
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_number VARCHAR(50) UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    gender ENUM('Male','Female'),
    birth_date DATE,
    joined_date DATE,
    department_id INT,
    location_id INT,
    job_title_id INT,
    employment_status_id INT,
    supervisor_id INT NULL,

    FOREIGN KEY (department_id) REFERENCES departments(id),
    FOREIGN KEY (location_id) REFERENCES locations(id),
    FOREIGN KEY (job_title_id) REFERENCES job_titles(id),
    FOREIGN KEY (employment_status_id) REFERENCES employment_status(id),
    FOREIGN KEY (supervisor_id) REFERENCES employees(id)
);

INSERT INTO employees (
employee_number,
first_name,
last_name,
gender,
birth_date,
joined_date,
department_id,
location_id,
job_title_id,
employment_status_id,
supervisor_id
)
VALUES
('EMP001','Ahmed','Hassan','Male','1990-05-10','2020-01-01',1,1,4,1,NULL),
('EMP002','Sara','Ali','Female','1995-08-15','2021-02-01',2,1,2,1,1),
('EMP003','Omar','Mohamed','Male','1998-10-20','2022-03-01',1,1,1,1,1),
('EMP004','Mona','Khaled','Female','1993-11-11','2023-04-01',3,2,3,1,1);

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password_hash VARCHAR(255),
    role_id INT,
    employee_id INT,
    status ENUM('active','inactive'),

    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

INSERT INTO users (
username,
password_hash,
role_id,
employee_id,
status
)
VALUES
('admin','$2a$10$hashedpassword',1,1,'active'),
('hrmanager','$2a$10$hashedpassword',2,2,'active'),
('employee1','$2a$10$hashedpassword',4,3,'active');

-- =========================
-- CONTACTS
-- =========================
CREATE TABLE employee_contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    email VARCHAR(255),
    phone VARCHAR(50),
    address TEXT,

    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

INSERT INTO employee_contacts (
employee_id,email,phone,address
)
VALUES
(1,'ahmed@company.com','01000000001','Alexandria'),
(2,'sara@company.com','01000000002','Alexandria'),
(3,'omar@company.com','01000000003','Cairo');

-- =========================
-- LEAVE TYPES
-- =========================
CREATE TABLE leave_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    default_days INT
);

INSERT INTO leave_types (name, default_days)
VALUES
('Annual Leave',21),
('Sick Leave',15),
('Unpaid Leave',30);

-- =========================
-- LEAVE ENTITLEMENTS
-- =========================
CREATE TABLE leave_entitlements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    leave_type_id INT,
    year INT,
    entitled_days INT,
    used_days INT DEFAULT 0,

    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(id)
);

INSERT INTO leave_entitlements (
employee_id,
leave_type_id,
year,
entitled_days,
used_days
)
VALUES
(1,1,2026,21,5),
(2,1,2026,21,2),
(3,1,2026,21,1);

-- =========================
-- LEAVE REQUESTS
-- =========================
CREATE TABLE leave_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    leave_type_id INT,
    from_date DATE,
    to_date DATE,
    days DECIMAL(5,2),
    reason TEXT,
    status ENUM(
        'Pending',
        'Approved',
        'Rejected',
        'Cancelled'
    ),
    approved_by INT,

    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(id),
    FOREIGN KEY (approved_by) REFERENCES employees(id)
);

INSERT INTO leave_requests (
employee_id,
leave_type_id,
from_date,
to_date,
days,
reason,
status,
approved_by
)
VALUES
(3,1,'2026-06-01','2026-06-03',3,'Vacation','Approved',1),
(2,2,'2026-06-10','2026-06-11',2,'Medical','Pending',NULL);

-- =========================
-- ATTENDANCE
-- =========================
CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    check_in DATETIME,
    check_out DATETIME,
    total_hours DECIMAL(5,2),

    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

INSERT INTO attendance (
employee_id,
check_in,
check_out,
total_hours
)
VALUES
(1,'2026-06-01 08:00:00','2026-06-01 17:00:00',9),
(2,'2026-06-01 08:30:00','2026-06-01 17:00:00',8.5),
(3,'2026-06-01 09:00:00','2026-06-01 18:00:00',9);

-- =========================
-- PROJECTS
-- =========================
CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(255),
    customer_name VARCHAR(255)
);

INSERT INTO projects (
project_name,
customer_name
)
VALUES
('HRMS Development','Internal'),
('E-Commerce System','ABC Company');

-- =========================
-- TIMESHEETS
-- =========================
CREATE TABLE timesheets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    week_start DATE,
    week_end DATE,
    status ENUM(
        'Draft',
        'Submitted',
        'Approved',
        'Rejected'
    ),

    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

INSERT INTO timesheets (
employee_id,
week_start,
week_end,
status
)
VALUES
(3,'2026-05-25','2026-05-31','Approved');

-- =========================
-- TIMESHEET ENTRIES
-- =========================
CREATE TABLE timesheet_entries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timesheet_id INT,
    project_id INT,
    work_date DATE,
    hours DECIMAL(5,2),

    FOREIGN KEY (timesheet_id) REFERENCES timesheets(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

INSERT INTO timesheet_entries (
timesheet_id,
project_id,
work_date,
hours
)
VALUES
(1,1,'2026-05-26',8),
(1,1,'2026-05-27',7),
(1,2,'2026-05-28',5);

-- =========================
-- RECRUITMENT
-- =========================
CREATE TABLE vacancies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vacancy_name VARCHAR(255),
    job_title_id INT,
    hiring_manager_id INT,
    positions INT,
    status ENUM('Open','Closed'),

    FOREIGN KEY (job_title_id) REFERENCES job_titles(id),
    FOREIGN KEY (hiring_manager_id) REFERENCES employees(id)
);

INSERT INTO vacancies (
vacancy_name,
job_title_id,
hiring_manager_id,
positions,
status
)
VALUES
('Junior Backend Developer',1,1,3,'Open'),
('HR Coordinator',2,2,1,'Open');

CREATE TABLE candidates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    phone VARCHAR(50)
);

INSERT INTO candidates (
first_name,
last_name,
email,
phone
)
VALUES
('Youssef','Mahmoud','youssef@gmail.com','01111111111'),
('Nada','Samir','nada@gmail.com','01222222222');

CREATE TABLE applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    candidate_id INT,
    vacancy_id INT,
    application_date DATE,
    status ENUM(
        'Applied',
        'Interview',
        'Offered',
        'Hired',
        'Rejected'
    ),

    FOREIGN KEY (candidate_id) REFERENCES candidates(id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id)
);

INSERT INTO applications (
candidate_id,
vacancy_id,
application_date,
status
)
VALUES
(1,1,'2026-05-15','Interview'),
(2,2,'2026-05-20','Applied');

-- =========================
-- PERFORMANCE REVIEWS
-- =========================
CREATE TABLE performance_reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    reviewer_id INT,
    review_period_start DATE,
    review_period_end DATE,
    overall_rating DECIMAL(3,2),
    comments TEXT,

    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (reviewer_id) REFERENCES employees(id)
);

INSERT INTO performance_reviews (
employee_id,
reviewer_id,
review_period_start,
review_period_end,
overall_rating,
comments
)
VALUES
(3,1,'2026-01-01','2026-06-01',4.5,'Excellent performance');

-- =========================
-- GOALS
-- =========================
CREATE TABLE goals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    title VARCHAR(255),
    description TEXT,
    target_date DATE,
    progress INT,

    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

INSERT INTO goals (
employee_id,
title,
description,
target_date,
progress
)
VALUES
(3,
'Node.js Mastery',
'Become advanced backend developer',
'2026-12-31',
70);




-- ==========================================
-- ORANGEHRM CLONE - SELECT & JOIN QUERIES
-- ==========================================

USE orangehrm_clone;

-- ==========================================================
-- 1. All Employees
-- ==========================================================
SELECT * FROM employees;

-- ==========================================================
-- 2. Employees with Department
-- ==========================================================
SELECT
    e.id,
    e.employee_number,
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    d.name AS Department
FROM employees e
JOIN departments d
ON e.department_id = d.id;

-- ==========================================================
-- 3. Employees with Job Title
-- ==========================================================
SELECT
    e.employee_number,
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    j.title AS JobTitle
FROM employees e
JOIN job_titles j
ON e.job_title_id = j.id;

-- ==========================================================
-- 4. Employees with Department & Job Title
-- ==========================================================
SELECT
    e.employee_number,
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    d.name AS Department,
    j.title AS JobTitle
FROM employees e
JOIN departments d
ON e.department_id = d.id
JOIN job_titles j
ON e.job_title_id = j.id;

-- ==========================================================
-- 5. Employees with Employment Status
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    es.status_name
FROM employees e
JOIN employment_status es
ON e.employment_status_id = es.id;

-- ==========================================================
-- 6. Employees with Location
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    l.name AS Office,
    l.city,
    l.country
FROM employees e
JOIN locations l
ON e.location_id = l.id;

-- ==========================================================
-- 7. Employees with Supervisor (SELF JOIN)
-- ==========================================================
SELECT
    emp.employee_number,
    CONCAT(emp.first_name,' ',emp.last_name) AS Employee,
    CONCAT(sup.first_name,' ',sup.last_name) AS Supervisor
FROM employees emp
LEFT JOIN employees sup
ON emp.supervisor_id = sup.id;

-- ==========================================================
-- 8. Employees with User Accounts
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    u.username,
    r.role_name,
    u.status
FROM users u
JOIN employees e
ON u.employee_id = e.id
JOIN roles r
ON u.role_id = r.id;

-- ==========================================================
-- 9. Employees Without User Accounts
-- ==========================================================
SELECT
    e.employee_number,
    CONCAT(e.first_name,' ',e.last_name) AS Employee
FROM employees e
LEFT JOIN users u
ON e.id = u.employee_id
WHERE u.id IS NULL;

-- ==========================================================
-- 10. Employee Contact Information
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    c.email,
    c.phone,
    c.address
FROM employee_contacts c
JOIN employees e
ON c.employee_id = e.id;

-- ==========================================================
-- 11. Leave Requests
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    lt.name AS LeaveType,
    lr.from_date,
    lr.to_date,
    lr.days,
    lr.reason,
    lr.status
FROM leave_requests lr
JOIN employees e
ON lr.employee_id = e.id
JOIN leave_types lt
ON lr.leave_type_id = lt.id;

-- ==========================================================
-- 12. Leave Requests with Approver
-- ==========================================================
SELECT
    CONCAT(emp.first_name,' ',emp.last_name) AS Employee,
    CONCAT(app.first_name,' ',app.last_name) AS ApprovedBy,
    lr.status
FROM leave_requests lr
JOIN employees emp
ON lr.employee_id = emp.id
LEFT JOIN employees app
ON lr.approved_by = app.id;

-- ==========================================================
-- 13. Leave Balance
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    lt.name,
    le.entitled_days,
    le.used_days,
    (le.entitled_days - le.used_days) AS RemainingDays
FROM leave_entitlements le
JOIN employees e
ON le.employee_id = e.id
JOIN leave_types lt
ON le.leave_type_id = lt.id;

-- ==========================================================
-- 14. Attendance Report
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    a.check_in,
    a.check_out,
    a.total_hours
FROM attendance a
JOIN employees e
ON a.employee_id = e.id;

-- ==========================================================
-- 15. Employees Worked More Than 8 Hours
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    a.total_hours
FROM attendance a
JOIN employees e
ON a.employee_id = e.id
WHERE a.total_hours > 8;

-- ==========================================================
-- 16. Timesheets
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    t.week_start,
    t.week_end,
    t.status
FROM timesheets t
JOIN employees e
ON t.employee_id = e.id;

-- ==========================================================
-- 17. Timesheet Entries
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    p.project_name,
    te.work_date,
    te.hours
FROM timesheet_entries te
JOIN timesheets t
ON te.timesheet_id = t.id
JOIN employees e
ON t.employee_id = e.id
JOIN projects p
ON te.project_id = p.id;

-- ==========================================================
-- 18. Total Hours Per Project
-- ==========================================================
SELECT
    p.project_name,
    SUM(te.hours) AS TotalHours
FROM projects p
JOIN timesheet_entries te
ON p.id = te.project_id
GROUP BY p.project_name;

-- ==========================================================
-- 19. Vacancies
-- ==========================================================
SELECT
    v.vacancy_name,
    j.title,
    CONCAT(e.first_name,' ',e.last_name) AS HiringManager,
    v.positions,
    v.status
FROM vacancies v
JOIN job_titles j
ON v.job_title_id = j.id
JOIN employees e
ON v.hiring_manager_id = e.id;

-- ==========================================================
-- 20. Applications
-- ==========================================================
SELECT
    CONCAT(c.first_name,' ',c.last_name) AS Candidate,
    v.vacancy_name,
    a.application_date,
    a.status
FROM applications a
JOIN candidates c
ON a.candidate_id = c.id
JOIN vacancies v
ON a.vacancy_id = v.id;

-- ==========================================================
-- 21. Performance Reviews
-- ==========================================================
SELECT
    CONCAT(emp.first_name,' ',emp.last_name) AS Employee,
    CONCAT(rev.first_name,' ',rev.last_name) AS Reviewer,
    pr.overall_rating,
    pr.comments
FROM performance_reviews pr
JOIN employees emp
ON pr.employee_id = emp.id
JOIN employees rev
ON pr.reviewer_id = rev.id;

-- ==========================================================
-- 22. Employee Goals
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    g.title,
    g.progress,
    g.target_date
FROM goals g
JOIN employees e
ON g.employee_id = e.id;

-- ==========================================================
-- 23. Complete Employee Information
-- ==========================================================
SELECT
    e.employee_number,
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    d.name AS Department,
    j.title AS JobTitle,
    l.city,
    es.status_name,
    CONCAT(s.first_name,' ',s.last_name) AS Supervisor
FROM employees e
LEFT JOIN departments d
ON e.department_id = d.id
LEFT JOIN job_titles j
ON e.job_title_id = j.id
LEFT JOIN locations l
ON e.location_id = l.id
LEFT JOIN employment_status es
ON e.employment_status_id = es.id
LEFT JOIN employees s
ON e.supervisor_id = s.id;

-- ==========================================================
-- 24. Number of Employees Per Department
-- ==========================================================
SELECT
    d.name,
    COUNT(e.id) AS TotalEmployees
FROM departments d
LEFT JOIN employees e
ON d.id = e.department_id
GROUP BY d.name;

-- ==========================================================
-- 25. Average Performance Rating
-- ==========================================================
SELECT
    AVG(overall_rating) AS AverageRating
FROM performance_reviews;

-- ==========================================================
-- 26. Total Leave Used By Employee
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    SUM(le.used_days) AS UsedLeave
FROM leave_entitlements le
JOIN employees e
ON le.employee_id = e.id
GROUP BY e.id;

-- ==========================================================
-- 27. Employees Ordered By Join Date
-- ==========================================================
SELECT
    employee_number,
    CONCAT(first_name,' ',last_name) AS Employee,
    joined_date
FROM employees
ORDER BY joined_date;

-- ==========================================================
-- 28. Employees Born After 1995
-- ==========================================================
SELECT
    employee_number,
    CONCAT(first_name,' ',last_name) AS Employee,
    birth_date
FROM employees
WHERE birth_date > '1995-01-01';

-- ==========================================================
-- 29. Top Employee By Worked Hours
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    SUM(a.total_hours) AS TotalHours
FROM attendance a
JOIN employees e
ON a.employee_id = e.id
GROUP BY e.id
ORDER BY TotalHours DESC;

-- ==========================================================
-- 30. Count Employees Per Job Title
-- ==========================================================
SELECT
    j.title,
    COUNT(e.id) AS TotalEmployees
FROM job_titles j
LEFT JOIN employees e
ON j.id = e.job_title_id
GROUP BY j.title;

-- ==========================================================
-- 31. Count Applications Per Vacancy
-- ==========================================================
SELECT
    v.vacancy_name,
    COUNT(a.id) AS Applications
FROM vacancies v
LEFT JOIN applications a
ON v.id = a.vacancy_id
GROUP BY v.id;

-- ==========================================================
-- 32. Employees With Remaining Leave > 15 Days
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    (le.entitled_days - le.used_days) AS RemainingLeave
FROM leave_entitlements le
JOIN employees e
ON le.employee_id = e.id
WHERE (le.entitled_days - le.used_days) > 15;

-- ==========================================================
-- 33. Employees and Number of Attendance Records
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    COUNT(a.id) AS AttendanceDays
FROM employees e
LEFT JOIN attendance a
ON e.id = a.employee_id
GROUP BY e.id;

-- ==========================================================
-- 34. Projects Ordered By Total Hours
-- ==========================================================
SELECT
    p.project_name,
    SUM(te.hours) AS TotalHours
FROM projects p
JOIN timesheet_entries te
ON p.id = te.project_id
GROUP BY p.id
ORDER BY TotalHours DESC;

-- ==========================================================
-- 35. Employees Working in Alexandria
-- ==========================================================
SELECT
    CONCAT(e.first_name,' ',e.last_name) AS Employee,
    l.city
FROM employees e
JOIN locations l
ON e.location_id = l.id
WHERE l.city = 'Alexandria';