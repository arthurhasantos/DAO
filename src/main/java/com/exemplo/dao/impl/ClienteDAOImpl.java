package com.exemplo.dao.impl;

import com.exemplo.dao.ClienteDAO;
import com.exemplo.database.DatabaseConnection;
import com.exemplo.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta do ClienteDAO
 * Responsável por todas as operações de persistência de Cliente
 */
public class ClienteDAOImpl implements ClienteDAO {

    private static final String INSERT_SQL = 
        "INSERT INTO cliente (nome, email, telefone, cpf) VALUES (?, ?, ?, ?)";
    
    private static final String SELECT_BY_ID_SQL = 
        "SELECT * FROM cliente WHERE id = ?";
    
    private static final String SELECT_ALL_SQL = 
        "SELECT * FROM cliente ORDER BY nome";
    
    private static final String UPDATE_SQL = 
        "UPDATE cliente SET nome = ?, email = ?, telefone = ?, cpf = ? WHERE id = ?";
    
    private static final String DELETE_SQL = 
        "DELETE FROM cliente WHERE id = ?";
    
    private static final String SELECT_BY_CPF_SQL = 
        "SELECT * FROM cliente WHERE cpf = ?";
    
    private static final String SELECT_BY_NOME_SQL = 
        "SELECT * FROM cliente WHERE LOWER(nome) LIKE LOWER(?) ORDER BY nome";

    @Override
    public Cliente create(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setId(rs.getLong(1));
                }
            }
            
            return cliente;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }
    }

    @Override
    public Cliente findById(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setLong(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractClienteFromResultSet(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }
    }

    @Override
    public List<Cliente> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_SQL);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                clientes.add(extractClienteFromResultSet(rs));
            }
            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }
    }

    @Override
    public boolean update(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(UPDATE_SQL);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.setLong(5, cliente.getId());
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean delete(Long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(DELETE_SQL);
            stmt.setLong(1, id);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeResources(conn, stmt, null);
        }
    }

    @Override
    public Cliente findByCpf(String cpf) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_CPF_SQL);
            stmt.setString(1, cpf);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractClienteFromResultSet(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por CPF: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_NOME_SQL);
            stmt.setString(1, "%" + nome + "%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                clientes.add(extractClienteFromResultSet(rs));
            }
            
            return clientes;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar clientes por nome: " + e.getMessage(), e);
        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }
    }

    /**
     * Método auxiliar para extrair um Cliente do ResultSet
     */
    private Cliente extractClienteFromResultSet(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getLong("id"),
            rs.getString("nome"),
            rs.getString("email"),
            rs.getString("telefone"),
            rs.getString("cpf")
        );
    }
}

