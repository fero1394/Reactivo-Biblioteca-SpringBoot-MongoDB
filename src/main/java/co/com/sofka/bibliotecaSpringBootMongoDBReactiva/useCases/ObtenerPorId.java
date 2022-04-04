package co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases;

import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.dto.RecursoDTO;
import reactor.core.publisher.Mono;

public interface ObtenerPorId {
    Mono<RecursoDTO> get(String id);
}
