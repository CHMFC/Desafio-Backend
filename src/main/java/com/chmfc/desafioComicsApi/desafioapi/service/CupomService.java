package com.chmfc.desafioComicsApi.desafioapi.service;

import com.chmfc.desafioComicsApi.desafioapi.entity.Cupom;
import com.chmfc.desafioComicsApi.desafioapi.entity.Quadrinho;
import com.chmfc.desafioComicsApi.desafioapi.repository.CupomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável pela lógica de negócios relacionada aos cupons.
 * Utiliza {@link CupomRepository} para operações de persistência.
 */
@RequiredArgsConstructor
@Service
public class CupomService {

    private final CupomRepository cupomRepository;

    /**
     * Gera um novo cupom com base na raridade do quadrinho.
     * Cupons para quadrinhos raros têm um desconto de 10%, enquanto cupons para quadrinhos comuns têm um desconto de 20%.
     * O cupom gerado é persistido no banco de dados.
     *
     * @param raridade A raridade do quadrinho para o qual o cupom será gerado.
     * @return O cupom recém-criado.
     */
    @Transactional
    public Cupom gerarCupom(Quadrinho.Raridade raridade) {
        double desconto = raridade == Quadrinho.Raridade.raro ? 0.1 : 0.2;
        Cupom cupom = new Cupom(raridade, desconto);
        return cupomRepository.save(cupom);
    }

    /**
     * Recupera uma lista de cupons com base na raridade do quadrinho.
     *
     * @param raridade A raridade do quadrinho.
     * @return Uma lista de cupons que correspondem à raridade fornecida.
     */
    @Transactional(readOnly = true)
    public List<Cupom> getCuponsPorRaridade(Quadrinho.Raridade raridade) {
        return cupomRepository.findByRaridade(raridade);
    }
}