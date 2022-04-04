package co.com.sofka.bibliotecawebflux.useCases;

import co.com.sofka.bibliotecawebflux.dto.RecursoDTO;
import reactor.core.publisher.Flux;

public interface ObtenerPorString {
    Flux<RecursoDTO> get(String string);
}
