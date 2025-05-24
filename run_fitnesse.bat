@echo off
rem Thiết lập PYTHONPATH để bao gồm thư mục FitNesseFixtures
set PYTHONPATH=D:\Documents\FitNesseFixtures;%PYTHONPATH%

rem Hiển thị PYTHONPATH để kiểm tra
echo PYTHONPATH đã được thiết lập thành: %PYTHONPATH%

rem Chạy FitNesse với đường dẫn chính xác đến file JAR
java -jar D:\workspace\university\Nam3_HK2\KiemDinhPhanMem\Fitnesse\fitnesse-standalone.jar -p 8082

rem Dừng lại để xem kết quả
pause
