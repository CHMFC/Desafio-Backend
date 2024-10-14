package com.chmfc.desafioComicsApi.desafioapi.repository;

import com.chmfc.desafioComicsApi.desafioapi.entity.Quadrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface de repositório para a entidade Quadrinho.
 * Extende JpaRepository para fornecer operações CRUD e outras funcionalidades JPA.
 */
public interface QuadrinhoRepository extends JpaRepository<Quadrinho, Long> {

    /**
     * Busca quadrinhos pela raridade.
     *
     * @param raridade Raridade do quadrinho.
     * @return Lista de quadrinhos com a raridade especificada.
     */
    List<Quadrinho> findByRaridade(Quadrinho.Raridade raridade);

    /**
     * Busca quadrinhos pelo autor.
     *
     * @param autor Nome do autor.
     * @return Lista de quadrinhos do autor especificado.
     */
    List<Quadrinho> findByAutor(String autor);
}