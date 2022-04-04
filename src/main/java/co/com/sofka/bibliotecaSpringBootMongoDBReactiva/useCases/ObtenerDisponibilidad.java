package co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases;

import reactor.core.publisher.Mono;

public interface ObtenerDisponibilidad {
    Mono<String> get(String id);
}
