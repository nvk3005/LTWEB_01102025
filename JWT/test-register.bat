@echo off
echo Creating test user account...
curl -X POST http://localhost:8005/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"fullName\":\"Test User\",\"email\":\"test@example.com\",\"password\":\"123456\"}"
echo.
echo Test account created:
echo Email: test@example.com
echo Password: 123456
pause
