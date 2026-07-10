# 🍊 OrangeHRM End-to-End Testing Project

![QA Engineering](https://img.shields.io/badge/Testing%20Domain-HRMS%20Platform-orange?style=for-the-badge)
![Testing Pillars](https://img.shields.io/badge/Pillars-Manual%20%7C%20Automation%20%7C%20API%20%7C%20DB-blue?style=for-the-badge)
![Framework](https://img.shields.io/badge/Framework-Java%20%7C%20Selenium%20%7C%20TestNG%20%7C%20POM-green?style=for-the-badge)
![Status](https://img.shields.io/badge/Allure%20Report-100%25%20Passed-success?style=for-the-badge)

## 📌 Overview
This repository contains a comprehensive, enterprise-grade Software Quality Assurance (SQA) lifecycle executed on the **OrangeHRM** platform[cite: 1, 2]. Human Resource Management Systems involve highly interdependent workflows and data-critical processes. This project demonstrates the systematic planning, design, and execution of test strategies across multiple testing types to ensure backend robustness, data integrity, and a seamless UI/UX user experience[cite: 2].

🗓️ **Project Timeline:** April 25th, 2026 – July 10th, 2026[cite: 2]

---

## 👥 Meet Alpha Team
* **Instructor:** Ahmed Essam
* **Manual Testing Specialists:** Afram Hanna & Abdelrahman Sakr[cite: 1, 2]
* **Automation Testing & Architecture:** Aly Salem & Omer[cite: 1, 2]
* **API Validation & Database Testing:** Aly Salem[cite: 1, 2]
* **Team Contributors:** Ayman

---

## 📈 Project Scope & QA Metrics
Our metrics encapsulate a rigorous testing lifecycle conducted with zero-regression goals[cite: 1, 2]:
* **User Stories Covered:** 80[cite: 1]
* **Acceptance Criteria (BDD Framework):** 1,066[cite: 1, 2]
* **Total Defects Logged:** 106 Bugs[cite: 1]

### 🐛 Bug Severity Breakdown
| Severity | Logged Count | Status |
| :--- | :---: | :--- |
| 🔴 **Critical** | 6 | Resolved / Verified |
| 🟠 **High** | 44 | Resolved / Verified |
| 🟡 **Medium** | 48 | Resolved / Verified |
| 🟢 **Low** | 8 | Resolved / Verified |
| 📊 **Total** | **106** | **100% Tracked**[cite: 1] |

---

## 🛡️ Strategic Pillars of Testing

### 1. 📝 Manual Testing Strategy
Driven by a modular, user-profile approach using **Behavior-Driven Development (BDD)** syntax to write strict test scenarios[cite: 2]:
* **Exploratory Testing:** Targeted unscripted edge cases and workflow vulnerabilities[cite: 2].
* **UI/UX Verification:** Evaluated interface responsiveness, usability layouts, and input constraint behaviors[cite: 1, 2].
* **Defect Documentation:** Generated professional bug reports equipped with screen recordings, logs, and clear reproduction steps[cite: 1, 2].

### 2. ⚙️ Automation Strategy
Built a scalable and robust automation framework from scratch[cite: 1]:
* **Design Patterns:** Implemented **Page Object Model (POM)** to isolate page UI from test logic, integrated **Fluent Interfaces** for readable method chaining, and utilized **Action Bots** to eliminate repetitive low-level browser interactions[cite: 1].
* **Core Stack:** Java, Selenium WebDriver, and TestNG for execution lifecycle control and assertion handling[cite: 1].
* **Reporting:** Integrated **Allure Reports** to generate interactive HTML dashboards with nested visual steps and attached failure evidence[cite: 1].

### 3. 🌐 API Validation
Direct verification of backend service logic and data communication contracts bypassing the visual layout[cite: 2]:
* **CRUD Integration:** Automated test coverage for `GET`, `POST`, `PUT`, and `DELETE` requests[cite: 1].
* **Smart Scripting:** Utilized Postman Pre-request and Post-Response scripts (JavaScript) to dynamically pass authentication tokens and timestamps[cite: 1].
* **Performance Tiering:** Categorized responses into performance brackets (`<200ms` for Very Fast to `>1000ms` for Slow) alongside payload structural assertions[cite: 1].

### 4. 🗄️ Database & Data Integrity
Ensured data persistence states perfectly mirror frontend actions[cite: 2]:
* **CRUD Verification:** Executed direct DML SQL scripts (`INSERT`, `SELECT`, `UPDATE`) to validate back-end entries[cite: 1, 2].
* **Relational Logic:** Utilized complex multi-table `JOIN` statements and conditional clauses to audit the application database schema[cite: 1].

---

## 🛠️ Core Tool Stack
* **Project Management & Agile Sprints:** Jira[cite: 1, 2]
* **Test Case Management:** Testiny[cite: 1, 2]
* **Traceability & Sync:** Zephyr Scale[cite: 2]
* **IDE & Coding Framework:** IntelliJ IDEA (Java)[cite: 1, 2]
* **Browser Driving Engine:** Selenium WebDriver[cite: 1]
* **API Testing Tool:** Postman Cloud[cite: 1, 2]
* **Database Engine:** MySQL[cite: 1, 2]
* **Test Visualization & Reports:** Allure Report[cite: 1]
* **Data Modeling:** Microsoft Excel[cite: 1, 2]

---

## 🚀 Advanced QA Management Ecosystem
To maintain maximum visibility and full transparency, our process integrated a 5-tier orchestration model[cite: 1, 2]:
1. **Level 1 (Jira):** Sprint configuration, requirement hierarchy uploading, and full mapping of active User Stories[cite: 1, 2].
2. **Level 2 (Testiny):** Scripting test cases and linking them dynamically directly into active Jira tracking tickets[cite: 1, 2].
3. **Level 3 (Zephyr in Jira):** Deep synchronization of manual and automation validation states under a centralized single-pane-of-glass dashboard[cite: 2].
4. **Level 4 (Allure + Drive):** Hosting graphical execution dashboards on public cloud accounts for cross-team analysis[cite: 1].
5. **Level 5 (Alpha Tester Web App):** A tailored platform uploading and showcasing every finalized artifact[cite: 1].

---

## 🔮 Future Roadmap
- [ ] Expand overall endpoint test coverage to discover further microservice edge cases[cite: 1].
- [ ] Advance script integration by gathering all global helper methods into a unified custom library class[cite: 1].
- [ ] Incorporate dedicated system performance and stress execution suites[cite: 1].
- [ ] Enhance and scale features on the custom Alpha Tester website portal[cite: 1].
