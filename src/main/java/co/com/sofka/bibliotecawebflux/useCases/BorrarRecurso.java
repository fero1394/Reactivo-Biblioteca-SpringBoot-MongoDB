package co.com.sofka.bibliotecawebflux.useCases;

import reactor.core.publisher.Mono;

public interface BorrarRecurso {
    Mono<Void> deleteById(String id);
}
