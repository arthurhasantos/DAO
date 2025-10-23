@echo off
echo ========================================
echo   Inicializando Projeto DAO
echo ========================================
echo.

REM Compila o projeto
echo [1/2] Compilando o projeto...
call mvn clean package -q

if %ERRORLEVEL% NEQ 0 (
    echo ERRO: Falha na compila��o!
    pause
    exit /b 1
)

echo [2/2] Criando tabela CLIENTE...
echo.

REM Executa o Main para criar a tabela
java -cp "target/classes;%USERPROFILE%\.m2\repository\com\h2database\h2\2.2.224\h2-2.2.224.jar" com.exemplo.Main

echo.
echo ========================================
echo   Tudo pronto!
echo ========================================
echo.
echo Para visualizar o banco:
echo   abrir-console-h2.bat
echo.
pause

