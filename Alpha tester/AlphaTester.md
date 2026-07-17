# Test Management Platform

## Overview

This project is a modern **Test Management Platform** inspired by tools like **Testiny** and **Zephyr**. It is designed to simplify the software testing workflow by integrating directly with **Jira**, allowing QA teams to manage requirements, test cases, executions, and bug reports from a single dashboard.

The platform provides complete traceability between **User Stories**, **Test Cases**, and **Bugs**, making it easier to monitor testing progress and software quality throughout the Software Development Life Cycle (SDLC).

---

# Key Features

## 1. Jira Integration

The platform connects directly to **Jira** using its REST API.

After connecting to a Jira project, the system automatically imports:

- Epics
- User Stories
- Tasks
- Sprints
- Assignees

This allows testers to work with the same project structure used by the development team.

---

## 2. Import Test Cases

Users can import test cases into the platform from different sources.

Each test case contains information such as:

- Test Case ID
- Title
- Description
- Preconditions
- Test Steps
- Expected Result
- Priority
- Status

The platform stores all imported test cases inside the selected project.

---

## 3. Link Test Cases to User Stories

Every test case can be linked to its corresponding Jira User Story.

This creates complete requirement traceability by answering questions like:

- Which User Story does this test verify?
- Are all User Stories covered by tests?
- Which stories still have no test coverage?

---

## 4. Bug Management

When a test execution fails, users can create a bug directly from the failed test case.

Each bug includes:

- Bug ID
- Title
- Description
- Severity
- Priority
- Status
- Assigned Developer

Every bug remains linked to the test case that discovered it.

---

## 5. Complete Traceability View

One of the platform's main features is a unified traceability page that displays the complete relationship between requirements, testing, and defects.

For every User Story, users can easily view:

```
User Story
    │
    ├── Test Case 1
    │       ├── Bug 1
    │       └── Bug 2
    │
    ├── Test Case 2
    │       └── Passed
    │
    └── Test Case 3
            └── Bug 3
```

This provides a clear overview of:

- Which User Stories have test coverage
- Which Test Cases belong to each story
- Which Test Cases have failed
- Which Bugs were created from each failed test
- Overall testing progress

---

# Workflow

The testing workflow follows these steps:

1. Connect the platform to Jira.
2. Import project User Stories and Tasks.
3. Import or create Test Cases.
4. Link Test Cases to their corresponding User Stories.
5. Execute Test Cases.
6. Report Bugs for failed Test Cases.
7. Link Bugs to the related Test Cases.
8. Monitor the complete traceability through a single dashboard.

---

# Benefits

- Centralized test management
- Seamless Jira integration
- Requirement traceability
- Easy bug tracking
- Better collaboration between QA and Developers
- Improved test coverage visibility
- Faster defect analysis
- Simplified project monitoring

---

# Goal

The goal of this platform is to provide a lightweight, modern, and user-friendly alternative to tools like **Testiny** and **Zephyr**, while maintaining full integration with Jira and delivering complete traceability between **Requirements**, **Test Cases**, and **Bugs**.


---

# Futers plans 

We aim to add many futers relaed to Automation and API.