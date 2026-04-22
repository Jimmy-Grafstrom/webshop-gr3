# Webshop-Gr3

A modern webshop application built with Spring Boot, focusing on security and user experience. The project features Multi-Factor Authentication (MFA) using One-Time Tokens (OTT).

## 🚀 Technologies

*   **Java 21**
*   **Spring Boot 4.0.5**
*   **Spring Security** (with MFA & OTT)
*   **Spring Data JPA**
*   **PostgreSQL** (Production) / **H2** (Test/Development)
*   **Thymeleaf** (Template Engine)
*   **Lombok**
*   **Docker**
*   **Maven**

## ✨ Features

*   **Product Catalog**: Browse and search for products.
*   **Shopping Cart**: Add products and manage your order.
*   **Checkout**: Secure checkout process.
*   **User Management**: Registration and profile management.
*   **Security**:
    *   Multi-Factor Authentication (MFA).
    *   One-Time Token (OTT) sent via email for login.
*   **Admin Panel**: Manage products (add, edit, delete).
*   **Responsive Design**: Optimized for both desktop and mobile devices.

## 🛠 Installation & Running

### Prerequisites

*   JDK 21
*   Maven 3.9+
*   Docker (optional, for PostgreSQL)
*   **Important**: This project depends on an external library (`mail-function-library`) hosted on GitHub Packages. You must configure your `settings.xml` with a GitHub Personal Access Token (PAT).

### Environment Variables

The application requires the following environment variables:

| Variable | Description |
| :--- | :--- |
| `DB_URL` | PostgreSQL database URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `EMAIL_ADDRESS` | Email address for sending OTTs |
| `EMAIL_PASSWORD` | Email password/App password |
| `admin.password` | (Optional) Password for admin@test.com (default: `admin`) |

### Run Locally

1.  **Configure Maven**: Ensure your `~/.m2/settings.xml` has access to GitHub Packages.
2.  **Build the project**:
    ```bash
    mvn clean package
    ```
3.  **Run the application**:
    ```bash
    java -jar target/app.jar
    ```

### Run with Docker

1.  **Build the image**:
    ```bash
    docker build -t webshop-gr3 .
    ```
2.  **Run the container**:
    ```bash
    docker run -p 8080:8080 \
      -e DB_URL=jdbc:postgresql://host:port/db \
      -e DB_USERNAME=user \
      -e DB_PASSWORD=pass \
      webshop-gr3
    ```

## 🔐 Authentication & Testing

The following test users are automatically created at startup:

*   **User**: `user@test.com` / `user`
*   **Admin**: `admin@test.com` / `admin`

### MFA Flow
1.  Log in with username and password.
2.  The system generates a One-Time Token (OTT).
3.  The link is sent via email (in development mode, the link is also logged to the **console**).
4.  Click the link to complete the login process.

## 🚀 CI/CD

The project uses GitHub Actions for Continuous Integration and Deployment.
*   Builds and tests the code on every push to `main`.
*   Builds and pushes a Docker image to Docker Hub upon a successful build.