package co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases;

import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.dto.RecursoDTO;
import reactor.core.publisher.Flux;

public interface ObtenerPorString {
    Flux<RecursoDTO> get(String string);
}
