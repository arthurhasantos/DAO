package com.exemplo.dao;

import com.exemplo.model.Cliente;
import java.util.List;

/**
 * Interface específica para operações de DAO de Cliente
 * Estende GenericDAO e adiciona métodos específicos de Cliente
 */
public interface ClienteDAO extends GenericDAO<Cliente> {
    
    /**
     * Busca cliente por CPF
     * @param cpf CPF do cliente
     * @return Cliente encontrado ou null
     */
    Cliente findByCpf(String cpf);
    
    /**
     * Busca clientes por nome (busca parcial)
     * @param nome Nome ou parte do nome
     * @return Lista de clientes encontrados
     */
    List<Cliente> findByNome(String nome);
}

