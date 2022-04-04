package co.com.sofka.bibliotecawebflux.useCases;

import co.com.sofka.bibliotecawebflux.dto.RecursoDTO;
import reactor.core.publisher.Flux;

public interface ObtenerPorAreaYTipo {
    Flux<RecursoDTO> get(String area, String tipo);
}
