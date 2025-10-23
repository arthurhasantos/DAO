@echo off
echo ========================================
echo   Compilando Projeto DAO
echo ========================================
echo.

REM Compila o projeto
echo Compilando o projeto...
call mvn clean package -q

if %ERRORLEVEL% NEQ 0 (
    echo ERRO: Falha na compila��o!
    pause
    exit /b 1
)

echo.
echo ========================================
echo   Compila��o conclu�da com sucesso!
echo ========================================
echo.
echo Projeto compilado e pronto para uso!
echo Para testar, abra o console H2:
echo   abrir-console-h2.bat
echo.
pause

