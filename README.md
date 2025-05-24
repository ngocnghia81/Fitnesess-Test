# FitNesse Test Fixtures with GitHub CI/CD

## Overview
This repository contains FitNesse test fixtures for testing database, API, and UI functionality. The project is configured with GitHub Actions for continuous integration and deployment.

## CI/CD Setup

### CI Pipeline (GitHub Actions)
The CI pipeline automatically runs whenever changes are pushed to the main/master branch or when a pull request is created. It performs the following steps:

1. Sets up a Windows environment with JDK 11
2. Sets up Chrome and ChromeDriver for UI tests
3. Downloads FitNesse standalone JAR
4. Sets up the project environment with necessary classpath configuration
5. Runs the comprehensive test suite
6. Runs specific test types (Database, API)
7. Publishes test results

### CD Pipeline (GitHub Actions)
The CD pipeline runs after a successful CI pipeline execution or can be triggered manually. It:

1. Creates a deployment package with all necessary files
2. Uploads the package as an artifact
3. Creates a GitHub Release when a tag is pushed
4. Simulates deployment (in a real environment, this would deploy to your server)

## How to Use

### Running Tests Locally
Use the `run_fitnesse.bat` script to start FitNesse:
```
run_fitnesse.bat
```

Then access FitNesse at http://localhost:8082.

### Running CI/CD Manually
1. Go to the "Actions" tab in your GitHub repository
2. Select the "FitNesse Tests" workflow
3. Click "Run workflow" and select the branch to run on
4. After tests pass, manually run the "FitNesse Deploy" workflow if needed

### Creating Releases
To create a release:
1. Tag your commit with a version number:
```
git tag v1.0.0
git push origin v1.0.0
```
2. The CD pipeline will automatically create a GitHub Release with the deployment package

## Test Structure
- `ComprehensiveSuite.wiki`: Runs all tests
- `LoginDbTest.wiki`: Database-specific tests
- `LoginApiTest.wiki`: API-specific tests
- `LoginUiTest.wiki`: UI-specific tests

## Workflow Files
- `.github/workflows/fitnesse-tests.yml`: CI workflow definition
- `.github/workflows/fitnesse-deploy.yml`: CD workflow definition 