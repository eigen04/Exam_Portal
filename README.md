📘 Exam Portal

A full-stack Exam Portal web application built using Spring Boot and React.js.

This platform provides functionality to conduct and take quizzes.

Admin users can create, update, and delete quizzes, categories, and questions.

Normal users can attempt quizzes and view their scorecards of previous attempts.

🚀 Features

✔️ User Authentication – Secure Login & Registration
✔️ Role-based Access – Separate functionality for Admin and Users
✔️ Category Management – Admin can create & manage categories
✔️ Quiz Management – Add, update, delete quizzes
✔️ Question Management – Add questions to quizzes
✔️ Take Quizzes – Users can attempt quizzes in real-time
✔️ Scorecard – Users can track their previous attempts
✔️ Backend APIs with Spring Boot – RESTful endpoints for all features

🛠 Tech Stack
Frontend (exam-portal-frontend)

React.js ⚛️

Axios 🌐

Tailwind CSS 🎨

React Router 🔗

Backend (exam-portal-backend)

Spring Boot 🚀

Spring Data JPA

Spring Security (JWT Authentication 🔐)

MySQL 🗄️

📂 Folder Structure
Exam_Portal/
│── exam-portal-frontend/    # React frontend
│   ├── public/              # Static assets
│   ├── src/                 # React components & pages
│   └── package.json
│
│── exam-portal-backend/     # Spring Boot backend
│   ├── src/main/java/...    # Java source files
│   ├── src/main/resources/  # Application properties
│   └── pom.xml
│
└── README.md

📊 Backend API Endpoints
🔐 Authentication

Login: POST /login

Register: POST /register

📂 Categories

Create a Category: POST /api/category/

Get All Categories: GET /api/category/

Get a Category by ID: GET /api/category/{catId}

Update a Category: PUT /api/category/{catId}

Delete a Category: DELETE /api/category/{catId}

📝 Quizzes

Create a Quiz: POST /api/quiz/

Get All Quizzes: GET /api/quiz/

Get a Quiz by ID: GET /api/quiz/{quizId}

Get Quizzes by Category: GET /api/quiz/?catId={catId}

Update a Quiz: PUT /api/quiz/{quizId}

Delete a Quiz: DELETE /api/quiz/{quizId}

❓ Questions

Create a Question: POST /api/question/

Get All Questions: GET /api/question/

Get a Question by ID: GET /api/question/{quesId}

Get Questions by Quiz: GET /api/question/?quizId={quizId}

Update a Question: PUT /api/question/{quesId}

Delete a Question: DELETE /api/question/{quesId}

🔧 Setup Instructions
1. Clone the repository
git clone https://github.com/eigen04/Exam_Portal.git
cd Exam_Portal

2. Backend Setup (Spring Boot)

Open exam-portal-backend in your IDE (Eclipse/IntelliJ).

Configure application.properties with your MySQL credentials.

Run the Spring Boot application.

3. Frontend Setup (React.js)
cd exam-portal-frontend
npm install
npm start

4. Access the App

Frontend: http://localhost:3000
Backend: http://localhost:8080

🎯 Learning Outcomes

Full-stack development with React + Spring Boot

Role-based authentication (Admin vs User)

Quiz/Exam Management System in real-world workflow

REST API design with Spring Boot

Integration of frontend with secure backend

👨‍💻 Author

Anant Agarwal
📌 Passionate Java & Full-Stack Developer
📌 Skilled in React.js, Spring Boot, MySQL, REST APIs
