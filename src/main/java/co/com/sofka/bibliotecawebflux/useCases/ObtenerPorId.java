package co.com.sofka.bibliotecawebflux.useCases;

import co.com.sofka.bibliotecawebflux.dto.RecursoDTO;
import reactor.core.publisher.Mono;

public interface ObtenerPorId {
    Mono<RecursoDTO> get(String id);
}
