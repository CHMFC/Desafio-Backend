package com.chmfc.desafioComicsApi.desafioapi.repository;

import com.chmfc.desafioComicsApi.desafioapi.entity.Cupom;
import com.chmfc.desafioComicsApi.desafioapi.entity.Quadrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositório para a entidade {@link Cupom}.
 * Extende {@link JpaRepository} para fornecer operações CRUD e outras
 * funções de persistência diretamente para a entidade Cupom.
 */
public interface CupomRepository extends JpaRepository<Cupom, Long> {

    /**
     * Encontra cupons pela raridade associada ao quadrinho.
     *
     * @param raridade A raridade do quadrinho.
     * @return Uma lista de cupons que correspondem à raridade fornecida.
     */
    List<Cupom> findByRaridade(Quadrinho.Raridade raridade);

}