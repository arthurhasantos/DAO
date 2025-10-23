@echo off
echo ========================================
echo   Console Web do H2 Database
echo ========================================
echo.
echo Iniciando o servidor H2 com console web...
echo.
echo O navegador sera aberto automaticamente.
echo Para conectar ao banco de dados, use:
echo.
echo   JDBC URL: jdbc:h2:./data/clientedb
echo   User: sa
echo   Password: (deixe vazio)
echo.
echo Pressione Ctrl+C para encerrar o servidor.
echo ========================================
echo.

REM Inicia o H2 Console (servidor web + abre navegador)
java -cp "%USERPROFILE%\.m2\repository\com\h2database\h2\2.2.224\h2-2.2.224.jar" org.h2.tools.Console

pause

