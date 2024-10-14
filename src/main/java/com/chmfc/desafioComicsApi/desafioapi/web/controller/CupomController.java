package com.chmfc.desafioComicsApi.desafioapi.web.controller;

import com.chmfc.desafioComicsApi.desafioapi.entity.Cupom;
import com.chmfc.desafioComicsApi.desafioapi.entity.Quadrinho;
import com.chmfc.desafioComicsApi.desafioapi.service.CupomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciar cupons.
 * Fornece endpoints para gerar cupons e recuperar cupons por raridade.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cupons")
public class CupomController {

    private final CupomService cupomService;

    /**
     * Endpoint para gerar um novo cupom com base na raridade do quadrinho.
     *
     * @param raridade A raridade do quadrinho para o qual o cupom será gerado.
     * @return {@link ResponseEntity} contendo o cupom recém-criado.
     */
    @PostMapping("/gerar/{raridade}")
    public ResponseEntity<Cupom> gerarCupom(@PathVariable Quadrinho.Raridade raridade) {
        Cupom cupom = cupomService.gerarCupom(raridade);
        return ResponseEntity.ok(cupom);
    }

    /**
     * Endpoint para recuperar uma lista de cupons com base na raridade do quadrinho.
     *
     * @param raridade A raridade do quadrinho.
     * @return {@link ResponseEntity} contendo uma lista de cupons que correspondem à raridade fornecida.
     */
    @GetMapping("/raridade/{raridade}")
    public ResponseEntity<List<Cupom>> getCuponsByRaridade(@PathVariable Quadrinho.Raridade raridade) {
        List<Cupom> cupons = cupomService.getCuponsPorRaridade(raridade);
        return ResponseEntity.ok(cupons);
    }

}