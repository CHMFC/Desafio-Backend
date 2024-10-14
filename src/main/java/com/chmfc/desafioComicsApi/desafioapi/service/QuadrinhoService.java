package com.chmfc.desafioComicsApi.desafioapi.service;

import com.chmfc.desafioComicsApi.desafioapi.entity.Quadrinho;
import com.chmfc.desafioComicsApi.desafioapi.repository.QuadrinhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para manipulação da entidade Quadrinho.
 * Provê métodos para operações CRUD, além de buscas específicas.
 */
@RequiredArgsConstructor
@Service
public class QuadrinhoService {

    private final QuadrinhoRepository quadrinhoRepository;

    /**
     * Salva um novo quadrinho ou atualiza um existente.
     *
     * @param quadrinho Entidade Quadrinho a ser salva.
     * @return Quadrinho salvo.
     */
    @Transactional
    public Quadrinho salvar(Quadrinho quadrinho) {
        return quadrinhoRepository.save(quadrinho);
    }

    /**
     * Deleta um quadrinho pelo id.
     *
     * @param id Identificador do quadrinho a ser deletado.
     * @return Quadrinho deletado.
     */
    @Transactional
    public Quadrinho deletar(Long id) {
        Quadrinho quadrinho = buscarPorId(id);
        quadrinhoRepository.delete(buscarPorId(id));
        return quadrinho;
    }

    /**
     * Busca um quadrinho pelo id.
     *
     * @param id Identificador do quadrinho a ser buscado.
     * @return Quadrinho encontrado.
     * @throws RuntimeException se não encontrar o quadrinho.
     */
    @Transactional(readOnly = true)
    public Quadrinho buscarPorId(Long id) {
        return quadrinhoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Quadrinho não encontrado.")
        );
    }

    /**
     * Busca quadrinhos pela raridade.
     *
     * @param raridade Raridade dos quadrinhos desejados.
     * @return Lista de quadrinhos com a raridade especificada.
     */
    @Transactional(readOnly = true)
    public List<Quadrinho> getQuadrinhosPorRaridade(Quadrinho.Raridade raridade) {
        return quadrinhoRepository.findByRaridade(raridade);
    }

    /**
     * Busca quadrinhos pelo autor.
     *
     * @param autor Nome do autor dos quadrinhos desejados.
     * @return Lista de quadrinhos do autor especificado.
     */
    @Transactional(readOnly = true)
    public List<Quadrinho> getQuadrinhosPorAutor(String autor) {
        return quadrinhoRepository.findByAutor(autor);
    }

    /**
     * Atualiza um quadrinho existente.
     *
     * @param id Identificador do quadrinho a ser atualizado.
     * @param quadrinhoAtualizado Entidade Quadrinho com os dados atualizados.
     * @return Quadrinho atualizado.
     * @throws RuntimeException se não encontrar o quadrinho.
     */
    @Transactional
    public Quadrinho atualizarQuadrinho(Long id, Quadrinho quadrinhoAtualizado) {
        Quadrinho quadrinhoExistente = quadrinhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quadrinho não encontrado"));

        // Atualiza os campos
        quadrinhoExistente.setNome(quadrinhoAtualizado.getNome());
        quadrinhoExistente.setAutor(quadrinhoAtualizado.getAutor());
        quadrinhoExistente.setRaridade(quadrinhoAtualizado.getRaridade());
        quadrinhoExistente.setDataModificacao(LocalDateTime.now());

        return quadrinhoRepository.save(quadrinhoExistente);
    }

    @Transactional
    public Quadrinho updateRaridade(Long id, Quadrinho.Raridade novaRaridade) throws Exception {
        Optional<Quadrinho> quadrinhoOptional = quadrinhoRepository.findById(id);

        if (quadrinhoOptional.isPresent()) {
            Quadrinho quadrinho = quadrinhoOptional.get();
            quadrinho.setRaridade(novaRaridade);  // Atualiza a raridade
            return quadrinhoRepository.save(quadrinho);  // Salva as mudanças no banco de dados
        } else {
            throw new Exception("Quadrinho não encontrado.");
        }
    }

    /**
     * Atualiza a raridade de um quadrinho existente.
     *
     * @param id Identificador do quadrinho a ser editado.
     * @param raridade Nova raridade do quadrinho.
     * @return Quadrinho com a raridade atualizada.
     */
    @Transactional
    public Quadrinho editarRaridade(Long id, Quadrinho.Raridade raridade) {
        Quadrinho quadrinho = buscarPorId(id);
        quadrinho.setRaridade(raridade);
        return quadrinho;
    }

    /**
     * Busca todos os quadrinhos cadastrados.
     *
     * @return Lista de todos os quadrinhos.
     */
    @Transactional(readOnly = true)
    public List<Quadrinho> buscarTodos() {
        return quadrinhoRepository.findAll();
    }
}