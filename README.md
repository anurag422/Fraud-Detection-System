# 💳 Fraud Detection System (Full-Stack)

🚀 A production-inspired full-stack Fraud Detection System built using Spring Boot and React that detects suspicious transactions using intelligent rule-based and behavioral analysis.

---

## 🌐 Live Architecture

Frontend (React) → Backend (Spring Boot) → Database (MySQL)

---

## 🛠️ Tech Stack

### 🔹 Backend
- Java + Spring Boot
- Spring Security + JWT Authentication
- JPA / Hibernate
- REST APIs

### 🔹 Frontend
- React.js
- Axios (API Integration)
- React Router

### 🔹 Database
- MySQL

### 🔹 Security
- AES Encryption (Card Data)
- Password Hashing
- Role-Based Access Control

---

## 🔥 Key Features

### 🔐 Authentication & Security
- User Registration & Login (JWT-based)
- Secure password storage
- Encrypted card details (AES)
- Masked card number in responses

### 💳 Card & Payment System
- Add and manage cards
- Perform transactions
- Real-time fraud detection during payment

### 🚨 Fraud Detection Engine
- High-value transaction detection
- Location change detection
- High-frequency transaction monitoring
- Night-time transaction analysis
- Dynamic Risk Scoring System

### 📊 Dashboard
- View transaction history
- Fraud detection highlighting:
  - 🔴 BLOCKED
  - 🟡 SUSPICIOUS
  - ⚪ NORMAL

---

## 🧠 Fraud Detection Logic (Simplified)

```text
Risk Score = 
  High Amount + Location Change + Frequency + Time + Balance Ratio
