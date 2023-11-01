- MySQL
- MySQL
- io.jsonwebtoken

2. Cau hinh

# Cau hinh thong tin ket noi den CSDL
spring.datasource.url=jdbc:mysql://localhost:3306/Demo321
spring.datasource.username=root
spring.datasource.password=1234$

# Xac dinh lam viec voi MySQL
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect

# Xac dinh cach tao CSDL
spring.jpa.hibernate.ddl-auto=update

# Xem cac cau lenh SQL duoc sinh ra boi JPA/Hibernate
spring.jpa.properties.hibernate.format_sql=true

# Cau hinh dat ten field table theo ten nha phat trien
spring.jpa.hibernate.nameing.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategy
spring.jpa.hibernate.nameing.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategy

# Set timezone
spring.jpa.properties.hibernate.jdbc.time_zone=UTC

3. Xay dung phan quyen: Users, Roles, User_Role, ERole(Enum)

4. Xay dung UserRepository --> findByUserName

5. Xay dung CustomUserDetail, CustomUserDetailsService: Muc dich lay thong tin role cua user

6. Xay dung JWT. Thuong dua vao userId (or userName) de tao token. Unique la dc
JwtTokenProvider, JwtAuthenticationFilter

7. WebSecurityConfig
