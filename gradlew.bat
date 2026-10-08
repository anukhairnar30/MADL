@echo off
where gradle >nul 2>nul
if %ERRORLEVEL% EQU 0 (gradle %*) else (echo Gradle is not installed locally. Open this project in Android Studio and allow Gradle 8.7 to download.)
