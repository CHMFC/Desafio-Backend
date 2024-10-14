package com.chmfc.desafioComicsApi.desafioapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe de entidade JPA que representa um Quadrinho.
 * Utiliza anotações do JPA e Lombok para reduzir código boilerplate.
 */
@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "quadrinhos")
@EntityListeners(AuditingEntityListener.class)
public class Quadrinho implements Serializable {

    /**
     * Identificador único da entidade.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nome do quadrinho.
     * Não pode ser nulo e deve ser único.
     */
    @Column(name = "nome", nullable = false, unique = true, length = 200)
    private String nome;

    /**
     * Nome do autor do quadrinho.
     * Não pode ser nulo.
     */
    @Column(name = "autor", nullable = false, length = 200)
    private String autor;

    /**
     * Enum que representa a raridade do quadrinho.
     * Pode ser "raro" ou "comum".
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "raridade", nullable = false, length = 25)
    private Raridade raridade;

    /**
     * Data de criação da entidade.
     * O valor é gerado automaticamente na criação e não pode ser atualizado posteriormente.
     */
    @CreatedDate
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    /**
     * Data da última modificação da entidade.
     * O valor é gerado automaticamente durante a atualização.
     */
    @LastModifiedDate
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    /**
     * Enum que define os possíveis valores de raridade.
     */
    public enum Raridade {
        raro, comum
    }

    /**
     * Método de igualdade baseado no campo ID.
     *
     * @param o Objeto a ser comparado.
     * @return true se os objetos forem iguais, caso contrário false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadrinho quadrinho = (Quadrinho) o;
        return Objects.equals(id, quadrinho.id);
    }

    /**
     * Método que retorna o código hash da entidade baseado no campo ID.
     *
     * @return código hash da entidade.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Método toString que retorna uma representação da entidade.
     *
     * @return Representação textual do objeto.
     */
    @Override
    public String toString() {
        return "Quadrinho{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", raridade=" + raridade +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}