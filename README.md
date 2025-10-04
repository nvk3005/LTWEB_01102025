# Spring Security

## Thông tin sinh viên
- **Họ và tên**: Nguyễn Văn Kế
- **MSSV**: 23110234
- **Môn học**: Lập trình Web


## Cấu trúc dự án

### 1. Demo01_SpringSecurity - Authentication Cơ Bản
**Mục đích**: Giới thiệu Spring Security với authentication đơn giản

**Tính năng chính**:
- Authentication với In-Memory User Storage
- Form Login và HTTP Basic Authentication
- Endpoint công khai và bảo vệ
- Demo với 2 user: `admin/123` và `user/123`

**Công nghệ sử dụng**:
- Spring Boot 3.5.6
- Spring Security
- Thymeleaf
- Lombok

**Endpoints**:
- `GET /hello` - Công khai
- `GET /getCustomerList` - Yêu cầu authentication

---

### 2. Demo02_SpringSecurity - Database Integration
**Mục đích**: Tích hợp Spring Security với database

**Tính năng chính**:
- Custom UserDetailsService
- Database persistence với SQL Server
- Entity UserInfo với các trường cơ bản
- Repository pattern

**Công nghệ sử dụng**:
- Spring Boot 3.5.6
- Spring Security
- Spring Data JPA
- SQL Server Database
- Thymeleaf
- Lombok

**Database**:
- Database: `LTWEB34`
- Entity: `UserInfo` (id, name, email, password, roles)

---

### 3. Demo03_SpringSecurity - Role-Based Authorization
**Mục đích**: Demo phân quyền dựa trên role và CRUD operations

**Tính năng chính**:
- Role-based access control
- Custom login/signup pages
- Product management với CRUD operations
- BCrypt password encoding
- Thymeleaf Security integration

**Công nghệ sử dụng**:
- Spring Boot 3.5.6
- Spring Security
- Spring Data JPA
- SQL Server Database
- Thymeleaf với Security extras
- Validation
- Lombok

**Entities**:
- `Users` - Thông tin người dùng
- `Role` - Vai trò người dùng
- `Product` - Quản lý sản phẩm

**Authorization**:
- Chỉ ADMIN có thể thêm/sửa/xóa sản phẩm
- USER chỉ có thể xem danh sách sản phẩm

---

### 4. JWT - Modern Authentication
**Mục đích**: Demo JWT-based authentication cho ứng dụng hiện đại

**Tính năng chính**:
- JWT Token-based authentication
- Stateless session management
- CORS configuration
- Frontend integration với JavaScript
- API endpoints cho authentication
- Custom JWT service với secret key

**Công nghệ sử dụng**:
- Spring Boot 3.5.6
- Spring Security
- Spring Data JPA
- SQL Server Database
- JWT (jjwt library)
- Thymeleaf
- Lombok

**API Endpoints**:
- `POST /api/auth/register` - Đăng ký user mới
- `POST /api/auth/login` - Đăng nhập và nhận JWT token
- `GET /login` - Trang đăng nhập
- `GET /user/profile` - Trang profile (yêu cầu JWT)

## Cài đặt và chạy dự án


### Cấu hình Database
```properties
# Cấu hình SQL Server
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=LTWEB34;trustServerCertificate=true;encrypt=false
spring.datasource.username=sa
spring.datasource.password=123456789
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### Chạy từng project
```bash
# Chạy Demo01
cd Demo01_SpringSecurity
mvn spring-boot:run

# Chạy Demo02
cd Demo02_SpringSecurity
mvn spring-boot:run

# Chạy Demo03
cd Demo03_SpringSecurity
mvn spring-boot:run

# Chạy JWT
cd JWT
mvn spring-boot:run
```
