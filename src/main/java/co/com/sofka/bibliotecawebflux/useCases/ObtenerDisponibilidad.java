package co.com.sofka.bibliotecawebflux.useCases;

import co.com.sofka.bibliotecawebflux.dto.RecursoDTO;
import reactor.core.publisher.Mono;

public interface ObtenerDisponibilidad {
    Mono<String> get(String id);
}
