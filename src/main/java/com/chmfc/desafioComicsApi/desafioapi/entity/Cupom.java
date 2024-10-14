package com.chmfc.desafioComicsApi.desafioapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa um cupom de desconto no sistema.
 * Cada cupom possui um código único, uma raridade associada,
 * um valor de desconto e uma data de validade.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cupons")
public class Cupom {

    /**
     * Identificador único do cupom.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Código único do cupom.
     * Gerado automaticamente ao criar um novo cupom.
     */
    @Column(nullable = false, unique = true)
    private String codigo;

    /**
     * Raridade associada ao quadrinho que o cupom aplica.
     * Utiliza o tipo enumerado {@link Quadrinho.Raridade}.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Quadrinho.Raridade raridade;

    /**
     * Valor do desconto aplicado pelo cupom.
     */
    @Column(nullable = false)
    private double desconto;

    /**
     * Data e hora de validade do cupom.
     * Após essa data, o cupom não poderá mais ser utilizado.
     */
    @Column(nullable = false)
    private LocalDateTime validade;

    /**
     * Construtor que cria um cupom com base na raridade e valor de desconto fornecidos.
     * O código do cupom é gerado automaticamente e a validade é definida para 30 dias a partir da data de criação.
     *
     * @param raridade Raridade associada ao quadrinho que o cupom aplica.
     * @param desconto Valor do desconto aplicado pelo cupom.
     */
    public Cupom(Quadrinho.Raridade raridade, double desconto) {
        this.codigo = UUID.randomUUID().toString(); // Gera um código único para o cupom
        this.raridade = raridade;
        this.desconto = desconto;
        this.validade = LocalDateTime.now().plusDays(30); // 30 dias de validade
    }
}