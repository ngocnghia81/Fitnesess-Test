@echo off
echo Running FitNesse Comprehensive Test Suite for CI/CD...

rem Set the classpath
set CLASSPATH=D:\Documents\FitNesseFixtures
set CLASSPATH=%CLASSPATH%;D:\workspace\university\Nam3_HK2\KiemDinhPhanMem\Fitnesse\fitnesse-standalone.jar
set CLASSPATH=%CLASSPATH%;D:\Documents\FitNesseFixtures\sqlite-jdbc-3.42.0.0.jar

rem Run FitNesse tests and generate XML results
java -jar D:\workspace\university\Nam3_HK2\KiemDinhPhanMem\Fitnesse\fitnesse-standalone.jar -c "ComprehensiveSuite?suite&format=xml" -d D:\Documents\FitNesseFixtures -o -p 8083

echo Test execution completed. Results saved to fitnesse-results.xml
echo You can now integrate these results with your CI/CD pipeline.

pause
