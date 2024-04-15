<h1>Simple JWT Authentication App</h1>

Spring Boot, H2, Spring Security, JWT, OpenApi UI

Для тестирования используйте PostMan,

1. Swagger UI: http://localhost:8080/swagger-ui/index.html
2. Sign Up: http://localhost:8080/auth/sign-up - POST<br><br>
   {<br>
   "username": "UserName",<br>
   "email": "email@example.com",<br>
   "password": "useruser"<br>
   }<br><br>
   Вернется токен. Декодируем его на https://jwt.io/<br><br>

   {<br>
   "role": "ROLE_USER",<br>
   "id": 1,<br>
   "email": "email@example.com",<br>
   "sub": "UserName",<br>
   "iat": 1713186866,<br>
   "exp": 1713330866<br>
   }<br>


3. Sign In: http://localhost:8080/auth/sign-in - POST
4. Проходим аутентификацию с полученным токеном, по адресу /demo получаем ожидаемый для ROLE_USER отклик «Hello, world!»
5. Сетим /get-admin (GET) и получаем в /demo ожидаемый для ROLE_ADMIN отклик «Hello, admin!»

P. S. По референсам с Хабра.