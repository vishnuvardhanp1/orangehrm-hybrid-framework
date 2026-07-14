# OrangeHRM Hybrid Automation Framework

## Overview

This project is an end-to-end UI Automation Framework developed for the OrangeHRM application using Selenium WebDriver and TestNG. The framework follows a Hybrid Framework approach by combining PageFactory, Data-Driven Testing, reusable utility classes, reporting, and logging to provide a scalable, maintainable, and reusable automation solution.

---

# Technology Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* PageFactory
* Apache POI (Excel Data-Driven Testing)
* Extent Reports
* Log4j2 Logging
* Git & GitHub

---

# Framework Features

* Hybrid Automation Framework
* PageFactory Design Pattern
* Data-Driven Testing using Excel
* Reusable Utility Classes
* Explicit Waits
* Cross Browser Execution
* Parallel Execution using TestNG
* Extent Reports
* Log4j2 Logging
* Screenshot Capture on Failure
* Retry Analyzer
* TestNG Listeners
* Maven Project Structure
* Git Version Control

---

# Test Scenarios Automated

* Login
* Add Employee
* Search Employee
* Edit Employee
* Delete Employee
* Logout

---

# Project Structure

```text
src
│
├── main
│   ├── java
│   │   ├── pageObjects
│   │   └── utilities
│   │
│   └── resources
│       ├── config.properties
│       └── log4j2.xml
│
├── test
│   ├── java
│   │   ├── listeners
│   │   ├── testCases
│   │   └── testData
│   │
│   └── resources
│       └── testng.xml
│
├── Screenshots
├── ExtentReports
└── pom.xml
```

---

# Reporting

* Extent Reports
* Screenshot Capture on Test Failure

---

# Logging

Log4j2 is used for execution logging, debugging, and tracking test execution.

---

# Parallel Execution

The framework supports parallel execution using TestNG with ThreadLocal WebDriver implementation.

Example `testng.xml`

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<suite name="OrangeHRM Suite"
       parallel="classes"
       thread-count="3">

    <test name="OrangeHRM Tests">

        <classes>
            <class name="testCases.LoginTest"/>
            <class name="testCases.AddEmployeeTest"/>
            <class name="testCases.SearchEmployeeTest"/>
        </classes>

    </test>

</suite>
```

---

# Running the Framework

### Run from Eclipse

* Right-click **testng.xml**
* Select **Run As → TestNG Suite**

### Run using Maven

```bash
mvn clean test
```

---

# Author

**Vishnu Vardhan**
