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
- 🔐 **Proper validations in form

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot, Spring Security, Spring Data JPA, Hibernate  
- **Frontend:** Thymeleaf, Bootstrap/Tailwind CSS, JavaScript  
- **Database:** MySQL  
- **Auth:** Google OAuth2  
- **Build Tool:** Maven  

---

<img width="1920" height="1285" alt="screencapture-localhost-8888-home-2025-09-06-18_11_07" src="https://github.com/user-attachments/assets/384eb7f7-c71f-4096-bf58-8eade6467b48" />
<img width="1920" height="1020" alt="Screenshot 2025-09-06 182400" src="https://github.com/user-attachments/assets/2696da18-77f9-4b3f-845c-00a5294b5a24" />
<img width="1920" height="1132" alt="screencapture-localhost-8888-about-2025-09-06-18_11_49" src="https://github.com/user-attachments/assets/c8d979ac-7e93-472e-a2da-a80e7721ef34" />
<img width="1920" height="927" alt="screencapture-localhost-8888-services-2025-09-06-18_12_04" src="https://github.com/user-attachments/assets/117ec8cc-880e-42bf-9c1a-6a575b04a444" />
<img width="1920" height="1243" alt="screencapture-localhost-8888-signup-2025-09-06-18_13_16" src="https://github.com/user-attachments/assets/2e9ac805-f6dc-4693-9d1c-516aca738922" />
<img width="1920" height="912" alt="screencapture-localhost-8888-login-2025-09-06-18_13_29" src="https://github.com/user-attachments/assets/965b8519-4208-46db-8720-d9e75ecce28b" />
<img width="1920" height="1018" alt="screencapture-localhost-8888-authenticate-2025-09-06-18_14_26" src="https://github.com/user-attachments/assets/fe5e0f8f-d104-417b-bffb-903f3610b1fb" />
<img width="1920" height="868" alt="screencapture-localhost-8888-user-contacts-add-2025-09-06-18_14_47" src="https://github.com/user-attachments/assets/f31cab5f-3304-47bb-afb5-bd31c1b74b0a" />
<img width="1920" height="990" alt="screencapture-localhost-8888-user-contacts-view-2025-09-06-18_15_17" src="https://github.com/user-attachments/assets/5927f4b1-702d-478a-977d-15df7eaad760" />
<img width="1920" height="1030" alt="screencapture-localhost-8888-user-contacts-view-2025-09-06-18_18_37" src="https://github.com/user-attachments/assets/2c7e3abc-2f76-41e5-be39-75f196e65c3d" />
<img width="1920" height="952" alt="screencapture-localhost-8888-login-2025-09-06-18_19_36" src="https://github.com/user-attachments/assets/ef831792-ec3f-46f8-8139-f1cba5e4f3de" />
<img width="1920" height="957" alt="screencapture-localhost-8888-user-contacts-adding-2025-09-06-18_20_09" src="https://github.com/user-attachments/assets/cc719a1f-c81e-43fb-9c7f-7e7a93aa2ace" />
** 



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
mvn spring-boot:run
-----------------------------------------------
