package com.exemplo.dao;

import java.util.List;

/**
 * Interface genérica para operações de DAO (Data Access Object)
 * Define os métodos básicos de um CRUD
 * 
 * @param <T> Tipo da entidade
 */
public interface GenericDAO<T> {
    
    /**
     * Insere uma nova entidade no banco de dados
     * @param entity Entidade a ser inserida
     * @return Entidade inserida com ID gerado
     */
    T create(T entity);
    
    /**
     * Busca uma entidade pelo ID
     * @param id ID da entidade
     * @return Entidade encontrada ou null
     */
    T findById(Long id);
    
    /**
     * Lista todas as entidades
     * @return Lista de entidades
     */
    List<T> findAll();
    
    /**
     * Atualiza uma entidade existente
     * @param entity Entidade com dados atualizados
     * @return true se atualizado com sucesso, false caso contrário
     */
    boolean update(T entity);
    
    /**
     * Remove uma entidade pelo ID
     * @param id ID da entidade a ser removida
     * @return true se removido com sucesso, false caso contrário
     */
    boolean delete(Long id);
}

