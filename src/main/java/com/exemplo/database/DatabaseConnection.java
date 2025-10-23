package com.exemplo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados H2
 */
public class DatabaseConnection {
    
    // URL de conexão com o banco H2 em modo embedded (arquivo local)
    private static final String URL = "jdbc:h2:./data/clientedb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    // Para usar H2 em memória, descomente a linha abaixo:
    // private static final String URL = "jdbc:h2:mem:clientedb";

    /**
     * Obtém uma conexão com o banco de dados
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    /**
     * Fecha os recursos do banco de dados
     */
    public static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    /**
     * Inicializa o banco de dados criando as tabelas necessárias
     */
    public static void initDatabase() {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            
            // Cria a tabela cliente se não existir
            String createTableSQL = 
                "CREATE TABLE IF NOT EXISTS cliente (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "nome VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100), " +
                "telefone VARCHAR(20), " +
                "cpf VARCHAR(14) UNIQUE NOT NULL" +
                ")";
            
            stmt.execute(createTableSQL);
            System.out.println("Banco de dados inicializado com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar banco de dados: " + e.getMessage(), e);
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * Testa a conexão com o banco de dados
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}

