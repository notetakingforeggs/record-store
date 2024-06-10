# 🎵 Record Shop Backend

This project is a backend API for a record shop, built during the Northcoders Bootcamp using the Spring-Boot Framework. It provides a REST API for managing records, created with a test-driven development (TDD) approach using Mockito for mocking.

##  🧗🏻 Features

- **🛠️ Architecture**: MVC (Model-View-Controller)
- **🗄️ ORM**: Hibernate
- **✅ Data Validation**: DTO (Data Transfer Objects)
- **🛢 Database**: PostgreSQL
- **💻 User Interface**: Terminal UI
- **🔍 Testing**: TDD with Mockito

##  🏄🏿 Getting Started

### 📋 Prerequisites

- An IDE such as IntelliJ IDEA
- PostgreSQL database
- Postman (optional, for API interaction)

### 📦 Installation

1. **Clone the repository**:
   
2. **Configure the Database:**

Set up a PostgreSQL database on your local machine.
Configure the application.properties file in src/main/resources to connect to your local database.

3. **Run the Application:**

Open the project in IntelliJ.
Run the JvRecordShopApplication file.

4. **Populate the Database:**

A bash script is available in the resources directory to populate the database with sample albums. Or feel free to use the UI, curl, or postman to add your own.  

## Usage
You can interact with the API using tools like Postman or through the terminal interface provided. If using postman, the base URL is localhost:8080/api/v1/records . You can find the various endpoints by looking in the controller file.
