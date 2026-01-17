# 💰 Digital Wallet Microservices Platform

![Architecture](https://github.com/user-attachments/assets/05d876f2-0d59-47fc-9cfa-bc12b059367b)

A scalable microservices-based digital wallet platform with secure transactions and real-time notifications.

## 🏗️ Architecture

**🌐 Client Layer:** Postman/Web Apps → REST API requests  
**🚪 Gateway:** API Gateway (JWT auth, rate limiting, load balancing)  
**🎯 Services:** User, Wallet, Transaction, Notification microservices  
**🔍 Infrastructure:** Eureka (discovery), Redis (cache), Kafka (messaging), Docker  

## 🔄 Flow
```
Client → API Gateway → Auth Filter → Service Discovery → Target Service
User Sign Up → Wallet Creation → Transaction Processing → Kafka Event → Notification
```


## 🎯 Services

| Service | Port | Purpose |
|---------|------|---------|
| 🚪 **API Gateway** | 9000 | Entry point, auth, routing |
| 👤 **User Service** | 8080 | Registration, login, JWT |
| 💳 **Wallet Service** | 8081 | Balance, currency management |
| 💸 **Transaction Service** | 8082 | Send/receive money, events |
| 📧 **Notification Service** | 8084 | Email/SMS/push notifications |
| 🔍 **Eureka Server** | 8761 | Service discovery |


## 🔧 Tech Stack
- ☕ **Backend:** Spring Boot 3.x, Spring Cloud Gateway
- 🔍 **Discovery:** Eureka Server
- 📨 **Messaging:** Apache Kafka
- ⚡ **Cache:** Redis
- 🐳 **Container:** Docker, Docker Compose
- 🔐 **Security:** JWT Authentication, Role-based Authorization

## 🔒 Security Features
- 🔐 JWT token authentication
- ⚡ API rate limiting
- 🔒 Service-to-service security


## 🤝 Contributing
Fork → Feature Branch → Commit → Push → Pull Request

**Built with ❤️ using Spring Boot & Cloud-Native technologies**
