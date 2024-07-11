# Preparing Environment

Before you begin, ensure that Maven and Docker are installed.

# Docker Setup

To start Redis and MySQL 5.7 using Docker, use the following commands. Both services will be configured with the
password `zgx1688`, and the MySQL root account will be used.

### Start Redis

```bash
docker run -d --name redis -p 6379:6379 redis --requirepass "zgx1688"
```

### Start MySQL 5.7

```bash
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=zgx1688 mysql:5.7
```

### Import Initial Data

After starting MySQL, import the `init.sql` file from the `sql` folder into the database

# Backend

To set up the backend, follow these instructions:

1. Clean and install the Maven project, skipping tests:

   ```bash
   mvn clean install -DskipTests
   ```

2. Copy the generated JAR file to the root directory:

   ```bash
   cp ruoyi-admin/target/ruoyi-admin.jar ./
   ```

3. Deploy the application using the provided script:
   ```bash
   sudo sh deploy.sh
   ```

# Management Frontend

## Development

To get started with the project, follow these steps:

1. Navigate to the project directory:

   ```bash
   cd ruoyi-ui
   ```

2. Install dependencies:

   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

Open your browser and go to [http://localhost:80](http://localhost:80)  
Login credentials:  
Username: `admin`  
Password: `admin123`

## Deployment

To build the project for different environments, use the following commands:

- Build for the staging environment:

  ```bash
  npm run build:stage
  ```

- Build for the production environment:
  ```bash
  npm run build:prod
  ```
