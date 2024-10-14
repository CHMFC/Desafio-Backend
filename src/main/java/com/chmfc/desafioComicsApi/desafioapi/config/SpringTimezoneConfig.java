package com.chmfc.desafioComicsApi.desafioapi.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 * Classe de configuração de fuso horário da aplicação.
 * Esta classe é responsável por configurar o fuso horário padrão para "America/Sao_Paulo".
 */
@Configuration
public class SpringTimezoneConfig {

    /**
     * Método de inicialização que configura o fuso horário padrão da aplicação.
     * Define o fuso horário como "America/Sao_Paulo".
     * Este método é executado após a construção do bean.
     */
    @PostConstruct
    public void timezoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}