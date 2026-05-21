---
name: s3-storage-skill
description: Manage and interact with LocalStack S3 image storage in the Balistic System backend
---

# S3 Storage Skill for Agentic AI

This skill contains instructions, troubleshooting commands, and testing procedures for interacting with AWS S3 via LocalStack in the `sistema-balistico` project.

## How S3 Storage Works in this Project

The application uses Hexagonal Architecture. The file storage behavior is defined by the `FileStorageService` port in the application core:
- **Interface**: [FileStorageService](file:///c:/Users/GUEST/IdeaProjects/sistema%20balistico/src/main/java/com/ccc/sistema_balistico/core/application/services/FileStorageService.java)
- **Local Adapter**: [FileStorageImpl](file:///c:/Users/GUEST/IdeaProjects/sistema%20balistico/src/main/java/com/ccc/sistema_balistico/core/application/usecase/FileStorageImpl.java) (activated via `storage.type=local`)
- **S3 Adapter**: [S3FileStorageImpl](file:///c:/Users/GUEST/IdeaProjects/sistema%20balistico/src/main/java/com/ccc/sistema_balistico/core/infrastructure/out/storage/S3FileStorageImpl.java) (activated via `storage.type=s3`)

### Database Persistence
Only the unique **S3 Object Key** (e.g. `uuid-of-image.png`) is stored in the database. The URL is resolved dynamically when loading or streaming resources.

---

## LocalStack S3 Setup and Commands

### Running LocalStack
LocalStack is configured via `docker-compose.yml`. Start it using:
```powershell
docker compose up -d
```

### Initializing the S3 Bucket
On startup, Docker Compose automatically executes the initialization script:
- [init-s3.sh](file:///c:/Users/GUEST/IdeaProjects/sistema%20balistico/localstack-init/init-s3.sh)

This script runs the following command to create the bucket:
```bash
awslocal s3 mb s3://sistema-balistico-images
```

### AWS CLI Helper Commands (LocalStack Gateway: http://localhost:4566)
If you need to query the status of S3 manually, you can use these commands (assuming AWS CLI is configured or using awslocal):

1. **List all buckets:**
   ```powershell
   aws --endpoint-url=http://localhost:4566 s3 ls
   ```

2. **List all objects inside the bucket:**
   ```powershell
   aws --endpoint-url=http://localhost:4566 s3 ls s3://sistema-balistico-images --recursive
   ```

3. **Download an image from the bucket:**
   ```powershell
   aws --endpoint-url=http://localhost:4566 s3 cp s3://sistema-balistico-images/your-image-key.png ./downloaded.png
   ```

---

## Verification and Testing

### Verifying API integration
To check if the Spring Boot S3 client can successfully communicate with LocalStack:
1. Ensure LocalStack is up and running.
2. Run `mvn clean test` to execute integration tests.
3. Use a tool like Curl or Postman to upload images via `/api/v1/bullet` or `/api/v1/bullet/{id}/images`.
