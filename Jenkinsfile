pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Check out your code repository
                echo 'Checking out code...'
                // git url: 'your-repository-url'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building...'
                // Add any build steps if needed
            }
        }
        
        stage('Run FitNesse Tests') {
            steps {
                echo 'Running FitNesse tests...'
                
                // Run the FitNesse tests and generate XML results
                bat '''
                    cd D:\\Documents\\FitNesseFixtures
                    java -jar D:\\workspace\\university\\Nam3_HK2\\KiemDinhPhanMem\\Fitnesse\\fitnesse-standalone.jar -c "BasicTest?test&format=xml" -d D:\\Documents\\FitNesseFixtures -o -r fitnesse-results.xml -p 8083
                '''
                
                // Copy the results file to workspace and archive it
                bat '''
                    copy D:\\Documents\\FitNesseFixtures\\fitnesse-results.xml %WORKSPACE%\\fitnesse-results.xml
                '''
                
                // Archive the test results
                junit 'fitnesse-results.xml'
            }
        }
    }
    
    post {
        always {
            echo 'Test execution completed'
        }
        success {
            echo 'All tests passed!'
        }
        failure {
            echo 'Some tests failed!'
        }
    }
}
