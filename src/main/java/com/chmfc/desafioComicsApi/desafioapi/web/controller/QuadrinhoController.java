package com.chmfc.desafioComicsApi.desafioapi.web.controller;

import com.chmfc.desafioComicsApi.desafioapi.entity.Cupom;
import com.chmfc.desafioComicsApi.desafioapi.entity.Quadrinho;
import com.chmfc.desafioComicsApi.desafioapi.service.CupomService;
import com.chmfc.desafioComicsApi.desafioapi.service.QuadrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manipulação da entidade Quadrinho.
 * Provê endpoints para operações CRUD e buscas específicas.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/quadrinhos")
public class QuadrinhoController {

    private final QuadrinhoService quadrinhoService;
    private final CupomService cupomService;

    /**
     * Endpoint para criação de um novo quadrinho.
     *
     * @param quadrinho Entidade Quadrinho a ser criada.
     * @return Resposta contendo o quadrinho criado e o status HTTP 201 (Created).
     */
    @PostMapping("cadastrar")
    public ResponseEntity<Quadrinho> create(@RequestBody Quadrinho quadrinho) {
        Quadrinho nome = quadrinhoService.salvar(quadrinho);
        return ResponseEntity.status(HttpStatus.CREATED).body(nome);
    }

    /**
     * Endpoint para atualização de um quadrinho existente.
     *
     * @param id                  Identificador do quadrinho a ser atualizado.
     * @param quadrinhoAtualizado Entidade Quadrinho com os dados atualizados.
     * @return Resposta contendo o quadrinho atualizado e o status HTTP 200 (OK).
     */
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Quadrinho> atualizarQuadrinho(@PathVariable Long id, @RequestBody Quadrinho quadrinhoAtualizado) {
        Quadrinho quadrinho = quadrinhoService.atualizarQuadrinho(id, quadrinhoAtualizado);
        return ResponseEntity.ok(quadrinho);
    }

    /**
     * Endpoint para atualizar a raridade de um quadrinho pelo seu identificador.
     *
     * @param id Identificador do quadrinho cuja raridade será atualizada.
     * @param novaRaridade Nova raridade a ser atribuída ao quadrinho.
     * @return Resposta HTTP 200 (OK) com o quadrinho atualizado ou HTTP 400 (Bad Request) em caso de erro.
     */
    @PutMapping("/{id}/raridade")
    public ResponseEntity<?> updateRaridade(@PathVariable Long id, @RequestBody Quadrinho.Raridade novaRaridade) {
        try {
            Quadrinho quadrinhoAtualizado = quadrinhoService.updateRaridade(id, novaRaridade);
            return ResponseEntity.ok(quadrinhoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar a raridade: " + e.getMessage());
        }
    }

    /**
     * Endpoint para deletar um quadrinho pelo id.
     *
     * @param id Identificador do quadrinho a ser deletado.
     * @return Resposta contendo o status HTTP 204 (No Content) se a exclusão for bem-sucedida.
     */
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarQuadrinho(@PathVariable Long id) {
        quadrinhoService.deletar(id);
        return ResponseEntity.noContent().build();  // Retorna status 204 se a exclusão for bem-sucedida
    }

    /**
     * Endpoint para buscar todos os quadrinhos cadastrados.
     *
     * @return Resposta contendo a lista de todos os quadrinhos e o status HTTP 200 (OK).
     */
    @GetMapping("/buscar/todos")
    public ResponseEntity<List<Quadrinho>> getAll() {
        List<Quadrinho> nomes = quadrinhoService.buscarTodos();
        return ResponseEntity.ok(nomes);
    }

    /**
     * Endpoint para buscar um quadrinho pelo id.
     *
     * @param id Identificador do quadrinho a ser buscado.
     * @return Resposta contendo o quadrinho encontrado e o status HTTP 200 (OK).
     */
    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<Quadrinho> getById(@PathVariable Long id) {
        Quadrinho nome = quadrinhoService.buscarPorId(id);
        return ResponseEntity.ok(nome);
    }

    /**
     * Endpoint para buscar quadrinhos pela raridade.
     *
     * @param raridade Raridade dos quadrinhos desejados.
     * @return Resposta contendo a lista de quadrinhos com a raridade especificada e o status HTTP 200 (OK).
     */
    @GetMapping("/buscar/raridade/{raridade}")
    public ResponseEntity<List<Quadrinho>> getByRarity(@PathVariable Quadrinho.Raridade raridade) {
        List<Quadrinho> nomes = quadrinhoService.getQuadrinhosPorRaridade(raridade);
        return ResponseEntity.ok(nomes);
    }

    /**
     * Endpoint para buscar cupons associados à raridade de um quadrinho específico.
     *
     * @param id Identificador do quadrinho pelo qual os cupons serão buscados.
     * @return Resposta contendo a lista de cupons associados à raridade do quadrinho e o status HTTP 200 (OK).
     */
    @GetMapping("/buscar/cupons/{id}")
    public ResponseEntity<List<Cupom>> getCuponsByQuadrinho(@PathVariable Long id) {
        Quadrinho quadrinho = quadrinhoService.buscarPorId(id);
        List<Cupom> cupons = cupomService.getCuponsPorRaridade(quadrinho.getRaridade());
        return ResponseEntity.ok(cupons);
    }


}