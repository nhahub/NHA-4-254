# OrangeHRM API Testing with Postman AI

## Overview

This project uses **Postman AI** to automatically generate a complete API testing collection for the **OrangeHRM REST API**.

The goal is to create a professional Postman collection that covers all available API endpoints and supports:

* CRUD operations (GET, POST, PUT/PATCH, DELETE)
* Positive testing
* Negative testing
* Performance validation
* Response validation
* Automated request chaining
* Collection variables
* Regression testing

---

# Environment Variables

Create the following environment variables inside Postman.

| Variable   | Description            |
| ---------- | ---------------------- |
| `baseUrl`  | OrangeHRM API Base URL |
| `username` | OrangeHRM Username     |
| `password` | OrangeHRM Password     |
| `cookies`  | Session Cookie         |

Example:

```text
baseUrl = https://opensource-demo.orangehrmlive.com/web/index.php/api/v2
username = your_username
password = your_password
cookies = prepaj1h1sbueik8ugjnbsrl4b
```

---

# Collection Structure

The generated collection should organize requests into logical folders.

Example structure:

```text
OrangeHRM API

├── Authentication
├── Admin
├── Users
├── Employees (PIM)
├── Job Titles
├── Pay Grades
├── Employment Status
├── Job Categories
├── Work Shifts
├── Organization
├── Locations
├── Skills
├── Education
├── Licenses
├── Languages
├── Memberships
├── Nationalities
├── Corporate Branding
├── Leave
├── Time
├── Attendance
├── Recruitment
├── Candidates
├── Vacancies
├── Performance
├── Buzz
├── Dashboard
├── Directory
└── Maintenance
```

---

# CRUD Coverage

Each resource should include every supported HTTP method.

* GET
* POST
* PUT or PATCH
* DELETE

Each request should include:

* Correct endpoint
* Required headers
* Authentication
* Cookies
* Request body
* Sample response
* Description

---

# Collection Variables

The collection should automatically store IDs returned from POST requests.

Examples:

```text
employeeId
userId
jobTitleId
skillId
educationId
candidateId
vacancyId
locationId
payGradeId
workShiftId
languageId
licenseId
```

Example script:

```javascript
pm.collectionVariables.set(
    "employeeId",
    pm.response.json().data.id
);
```

These variables are reused in later PUT, PATCH, GET, and DELETE requests.

---

# Request Chaining

The generated collection should support automatic execution.

Example workflow:

```text
Login
      │
      ▼
Create Employee
      │
      ▼
Save employeeId
      │
      ▼
Get Employee
      │
      ▼
Update Employee
      │
      ▼
Delete Employee
      │
      ▼
Verify Employee Deleted
```

This allows the collection to run end-to-end without manual intervention.

---

# Pre-request Script

Every request should include the following pre-request script.

```javascript
pm.environment.set("startTime", new Date().getTime());
```

This records the request start time before execution.

---

# Test Script

Every request should execute a reusable test script that validates:

* Status code
* Response time
* Response size
* Content type
* Success or error state

It should also print useful execution details in the Postman Console.

Example output:

```text
================================

API:
https://.../employees

Status Code:
200

Response Time:
184 ms

Performance:
VERY FAST

Status:
SUCCESS

================================
```

---

# Positive Testing

Every endpoint should include successful request scenarios.

Examples:

* Get all employees
* Get employee by ID
* Create employee
* Update employee
* Delete employee

---

# Negative Testing

Every module should include error handling tests.

Examples:

* Missing required fields
* Invalid IDs
* Unauthorized requests
* Forbidden requests
* Duplicate records
* Invalid JSON
* Empty request body
* Invalid data types
* Unsupported HTTP methods

---

# Validation Tests

Each request should validate:

* HTTP status code
* Response schema
* Required fields
* Data types
* Pagination
* Success messages
* Error messages
* Response time
* JSON response

---

# Performance Testing

Every request should measure:

* Response time
* Response size
* Performance category

Performance categories:

| Response Time | Result    |
| ------------- | --------- |
| < 200 ms      | Very Fast |
| 200–299 ms    | Fast      |
| 300–499 ms    | Average   |
| 500–999 ms    | Slow      |
| ≥ 1000 ms     | Very Slow |

---

# Automation Goals

The generated Postman collection should be suitable for:

* Manual API testing
* Automated API testing
* Smoke testing
* Regression testing
* Integration testing
* Performance validation
* Continuous Integration (CI/CD)

---

# Expected Result

The final Postman collection should provide:

* Complete OrangeHRM API coverage
* Organized request folders
* Automatic variable management
* Reusable scripts
* Request chaining
* CRUD testing
* Positive and negative scenarios
* Response validation
* Performance validation
* Easy execution using the Postman Collection Runner

This creates a scalable and maintainable API testing suite for the OrangeHRM application.
