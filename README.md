# QA Guide: Local Setup & Testing Instructions (Flower Shop Project)

This document contains all the necessary information for the QA team to deploy the project locally and conduct testing, with a specific focus on the Authentication Microservice.

## Infrastructure Overview

Our project is built on a microservices architecture. When you run the local environment, the following containers are deployed:

- **API Gateway (Port 8080):** The single entry point for all client requests. **All requests from Postman or Swagger must be sent here (`http://localhost:8080`).**
- **Eureka Server (Port 8761):** Service Discovery. You can use its dashboard (`http://localhost:8761`) to check if the microservices have successfully started and registered.
- **Auth Service:** The authentication microservice. It interacts with the database and cache, and is only accessible from the outside via the API Gateway.
- **PostgreSQL (Port 5432):** The main relational database.
- **Redis (Port 6379):** Used for caching, specifically for rate limiting (restricting the number of login attempts).

---

## How to Run the Project Locally

Before you begin, ensure you have **Docker** and **Docker Compose** installed on your machine.

### Step 1: Environment Variables Setup (.env)

In the root directory of the project, you will find a `.env.example` file. You need to copy it and rename the copy to `.env`.

**Pay attention to these key variables:**

- `JWT_SECRET`: The secret key for token generation (you can use a dummy value for local testing or leave the default).
- `GOOGLE_CLIENT_ID`: **Important:** To test Google OAuth (`/auth/google`), you must insert a valid Client ID from the Google Cloud Console. Without this, the Google login integration will fail.
- The database and Redis credentials are automatically configured for local testing.

### Step 2: Start the Containers

Open your terminal in the root directory of the project (where the `docker-compose.yml` file is located) and run the following command:

```bash
docker-compose up -d --build
```

Note: The -d flag runs the containers in the background, and --build ensures that any fresh code changes are compiled into the images.

### Step 3: Verify Service Status

Before sending API requests, make sure the services are up and running:

1. Go to http://localhost:8761 in your browser (Eureka Dashboard).
2. Wait until you see API-GATEWAY and AUTHSERVICE listed under "Instances currently registered with Eureka". This usually takes 1-2 minutes after the containers start.

---

## What to Test: Endpoints (Auth Service)

All authentication requests are routed through the API Gateway.

-> Base URL: http://localhost:8080/auth

Below is the list of available endpoints and testing scenarios:

1. User Registration
    - Method: POST

    - Path: /register

    - Expected Status: 201 Created

    - Payload Example (RegisterRequest):

```JSON
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "StrongPassword123",
  "isMarketingAllow": true
}
```

(Note: Password must be 8+ chars, have 1 uppercase & 1 digit. Names must be letters only).

- What to Check: Successful user creation with valid data, and validation errors for invalid data (e.g., malformed email, password too short).

2. User Login (Pay attention to Rate Limiting!)
    - Method: POST

    - Path: /login

    - Expected Statuses:
        - 200 OK (Successful login, returns LoginResponseDto with a JWT token).

        - 401 Unauthorized (Incorrect email or password).

        - 429 Too Many Requests (Rate Limiter triggered).

    - Payload Example (LoginRequest):

```JSON
{
  "email": "john.doe@example.com",
  "password": "StrongPassword123"
}
```

- What to Check:
    - Successful login with correct credentials.

    - Login failure with a wrong password.

    - Rate Limiting Scenario: Try logging in with the wrong password multiple times in a row for the same email. Verify that Redis blocks further attempts and the API returns 429 Too Many Requests with the message "Too many attempts". After a successful login, the failed attempt counter should reset.

3. Google OAuth Login
    - Method: POST

    - Path: /google

    - Expected Status: 200 OK (Returns AuthResponse).

    - Payload Example (GoogleLoginRequest):

```JSON
{
  "credential": "your_google_credential_token"
}
```

- What to Check: Pass a valid Google credential token (usually provided by the frontend) in the request body to ensure the user is logged in or registered successfully.

4. Password Recovery Request
    - Method: POST

    - Path: /password-recovery/request

    - Expected Status: 200 OK

    - Payload Example (PasswordRecoveryRequest):

```JSON
{
  "email": "john.doe@example.com"
}
```

- What to Check: Submit a payload (PasswordRecoveryRequest) with an existing email, and test the behavior with a non-existent email.

5. Confirm Password Recovery
    - Method: POST

    - Path: /password-recovery/confirm

    - Expected Status: 200 OK

    - Payload Example (PasswordResetDto):

```JSON
{
  "token": "your_recovery_token",
  "newPassword": "StrongPassword123"
}
```

- What to Check: Submit the new password and the recovery code/token in the request body (PasswordResetDto).

## Shutting Down

To stop all services and free up the ports, run:

```bash
docker-compose down
```

If you want to completely wipe the local database and Redis cache (to start with a clean slate next time), add the -v flag to remove volumes:

```bash
docker-compose down -v
```
