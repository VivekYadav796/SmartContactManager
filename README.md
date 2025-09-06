# 📇 Smart Contact Manager

A full-stack **Spring Boot** application to securely manage your personal and professional contacts with OAuth2 login support, database integration, and a modern UI.

---

## 🚀 Features
- 🔐 **Authentication & Authorization** using Spring Security
- 🌐 **OAuth2 Login** with Google (secure environment variables, no hardcoding)
- 🗄️ **MySQL Database Integration** with JPA & Hibernate
- 📇 **Contact Management**
  - Add, edit, delete, and search contacts
  - Upload and manage profile pictures
- 🎨 **Responsive UI** with Thymeleaf, Tailwind/Bootstrap
- ⚡ **Production-ready configs** (profiles for dev & prod)

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot, Spring Security, Spring Data JPA, Hibernate  
- **Frontend:** Thymeleaf, Bootstrap/Tailwind CSS, JavaScript  
- **Database:** MySQL  
- **Auth:** Google OAuth2  
- **Build Tool:** Maven  

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repo

git clone https://github.com/VivekYadav796/SmartContactManager.git
cd SmartContactManager
2️⃣ Configure Database
Create a MySQL database:

CREATE DATABASE user;
Update your local credentials in application-dev.properties:

DB_USER=root
DB_PASS=yourpassword
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret

3️⃣ Run the application
Using Maven:

Copy code
mvn spring-boot:run
