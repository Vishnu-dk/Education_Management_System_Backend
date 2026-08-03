# Library Management System - Backend

A secure and scalable Library Management System backend built using Spring Boot, PostgreSQL, JWT Authentication, and jOOQ.

This application supports student and librarian workflows including authentication, book management, issue requests, approvals, returns, and fine management.

---

## Features

### Authentication & Authorization

- JWT-based authentication
- Role-based access control
- Student login and registration
- Admin login and registration
- Librarian role support
- Spring Security integration

### Student Features

- Register and login
- View available books
- Filter books by category and title
- Request books for issue
- Track issued books
- View request status
- View return and fine details
- Update profile information

### Librarian Features

- Manage library books
- Add new books
- Update book information
- Activate or deactivate books
- Approve issue requests
- Reject issue requests
- Process book returns
- Mark fines as paid
- Manage librarian profile

### Book Management

- CRUD operations for books
- Book categorization
- Availability tracking
- Copy management
- Search and sorting support

### Issue Management

- Book issue requests
- Approval workflow
- Return processing
- Overdue fine calculation
- Duplicate request prevention
- Active request tracking

---

## Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- jOOQ
- PostgreSQL
- Maven

### Additional Libraries

- Jakarta Validation
- JJWT
- BCrypt Password Encoder

---

# Project Architecture


src/main/java
│
├── config
│   ├── SecurityConfig
│   └── JwtFilter
│
├── controller
│   ├── studentController
│   ├── adminController
│   └── libraryController
│
├── dto
│   ├── studentDTO
│   ├── adminDTO
│   └── libraryDTO
│
├── entity
│   ├── studentEntity
│   ├── adminEntity
│   └── libraryEntity
│
├── repository
│   ├── studentRepository
│   ├── adminRepository
│   └── libraryRepository
│
├── service
│   ├── authentication services
│   ├── student services
│   └── library services
│
├── mapper
│   ├── studentMapper
│   ├── adminMapper
│   └── libraryMapper
│
└── exception


---

# Database Entities

## Student

- student_id
- email
- password
- active
- name
- admission_no
- roll_no
- age
- course

## Admin

- admin_id
- email
- password
- role
- active

## Librarian

- librarian_id
- librarian_name
- phone_no
- date_of_join

## Book

- book_id
- title
- author
- publisher
- publication_year
- category
- total_copies
- available_copies
- active

## Book Issue

- issue_id
- student_id
- book_id
- librarian_id
- request_date
- approved_date
- due_date
- return_date
- fine_amount
- fine_paid
- status
- active

---

# Authentication Flow

### Student

1. Register account
2. Login using email and password
3. Receive JWT token
4. Access student endpoints using Bearer Token

### Admin

1. Register admin account
2. Login
3. Receive JWT token
4. Access librarian/admin endpoints based on role

---

# Book Request Workflow

text
Student
    │
    ▼
Request Book
    │
    ▼
Status = REQUESTED
    │
    ▼
Librarian Reviews Request
   / \
  /   \
Approve Reject
  |      |
  ▼      ▼
ISSUED REJECTED
  |
  ▼
Return Book
  |
  ▼
RETURNED


---

# Fine Calculation

When a book is returned after its due date:


Fine = Days Late × ₹5


Example:

- Due Date: July 1
- Returned: July 6

Fine:


5 × ₹5 = ₹25


---

# Available Book Categories

- PROGRAMMING
- DATABASE
- NETWORKING
- CYBER_SECURITY
- ARTIFICIAL_INTELLIGENCE
- MACHINE_LEARNING
- CLOUD_COMPUTING
- DATA_SCIENCE
- SOFTWARE_ENGINEERING
- OPERATING_SYSTEM
- COMPUTER_ARCHITECTURE
- ELECTRONICS
- ELECTRICAL
- MECHANICAL
- CIVIL
- MATHEMATICS
- PHYSICS
- CHEMISTRY
- BIOLOGY
- COMMERCE
- ECONOMICS
- MANAGEMENT
- ACCOUNTING
- HISTORY
- GEOGRAPHY
- POLITICAL_SCIENCE
- ENGLISH
- LITERATURE
- GENERAL_KNOWLEDGE
- FICTION
- NON_FICTION

---

# API Modules

## Authentication

### Student


POST /api/student/auth/register
POST /api/student/auth/login


### Admin


POST /api/admin/auth/register
POST /api/admin/auth/login


---

## Student Management


GET    /api/students
GET    /api/students/{id}
PUT    /api/students/{id}
DELETE /api/students/{id}


---

## Librarian Management


GET    /api/librarians/{id}
PUT    /api/librarians/{id}
DELETE /api/librarians/{id}


---

## Book Management

### Admin


POST   /api/admin/books
GET    /api/admin/books
GET    /api/admin/books/{id}
PUT    /api/admin/books/{id}
PATCH  /api/admin/books/{id}/activate
PATCH  /api/admin/books/{id}/deactivate


### Students


GET /api/student/books
GET /api/student/books/{id}


---

## Issue Management

### Student


POST /api/student/book-requests
GET  /api/student/book-requests/my-books


### Admin


GET   /api/admin/book-requests
PATCH /api/admin/book-requests/{issueId}/approve
PATCH /api/admin/book-requests/{issueId}/reject
PATCH /api/admin/book-requests/{issueId}/return
PATCH /api/admin/book-requests/{issueId}/fine-paid


---

# Exception Handling

Custom exception support for:

- ResourceNotFoundException
- DuplicateUserException
- Validation Exceptions
- Runtime Exceptions

Global exception handling is implemented using:

java
@RestControllerAdvice


---

# Security Features

- Stateless Authentication
- JWT Token Validation
- BCrypt Password Encryption
- Role-based Endpoint Access
- Spring Security Filters
- Request Authorization

---

# Future Enhancements

- Email Notifications
- Book Reservation System
- Pagination
- Advanced Reporting Dashboard
- Audit Logs
- Refresh Tokens
- Swagger/OpenAPI Documentation
- Docker Deployment
- Unit and Integration Tests

---

# Author

Vishnu Divakar
Developer | Java | Spring Boot | PostgreSQL | jOOQ
