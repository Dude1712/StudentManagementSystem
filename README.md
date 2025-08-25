# Student Management System

A simple **Java + MySQL** based project to manage student records.  
This system allows users to **add, view, update, and delete student details** easily.

---

## 🚀 Features
- Add new student details (Name, Course, Fee, Email, Phone).
- View all student records in a table format.
- Update existing student details.
- Delete a student record by ID.
- MySQL Database connectivity using JDBC.

---

## 🛠️ Technologies Used
- **Java** (JDK 17+ recommended)
- **MySQL** (Workbench/Server)
- **JDBC Driver** (MySQL Connector)
- **VS Code / NetBeans / IntelliJ IDEA**

---

## 📂 Database Setup
Run the following SQL commands in MySQL Workbench:

```sql
CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    course VARCHAR(50),
    fee DOUBLE,
    email VARCHAR(100),
    phone VARCHAR(15)
);
