package co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases;

import reactor.core.publisher.Mono;

public interface BorrarRecurso {
    Mono<Void> deleteById(String id);
}
