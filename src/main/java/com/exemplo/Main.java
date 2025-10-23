package com.exemplo;

import com.exemplo.database.DatabaseConnection;

/**
 * Classe principal para inicializar o banco de dados
 * Cria a tabela CLIENTE se ela não existir
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Inicializando Banco de Dados H2");
        System.out.println("========================================");
        System.out.println();
        
        try {
            // Inicializa o banco de dados e cria a tabela CLIENTE
            DatabaseConnection.initDatabase();
            
            System.out.println("✓ Tabela CLIENTE criada com sucesso!");
            System.out.println();
            System.out.println("========================================");
            System.out.println("  Banco inicializado!");
            System.out.println("========================================");
            System.out.println();
            System.out.println("Proximo passo:");
            System.out.println("  Execute: abrir-console-h2.bat");
            System.out.println();
            
        } catch (Exception e) {
            String erro = e.getMessage();
            
            if (erro != null && erro.contains("already in use")) {
                System.out.println("⚠ ATENCAO: Banco de dados ja esta aberto!");
                System.out.println();
                System.out.println("O Console H2 ou outra aplicacao esta usando o banco.");
                System.out.println("Feche o console H2 e tente novamente.");
                System.out.println();
            } else {
                System.err.println("✗ Erro ao inicializar banco de dados:");
                System.err.println("  " + erro);
                System.out.println();
                System.out.println("Detalhes do erro:");
                e.printStackTrace();
            }
        }
    }
}

