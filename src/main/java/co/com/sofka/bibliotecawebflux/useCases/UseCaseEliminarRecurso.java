package co.com.sofka.bibliotecawebflux.useCases;

import co.com.sofka.bibliotecawebflux.mapper.RecursoMapper;
import co.com.sofka.bibliotecawebflux.repository.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseEliminarRecurso implements BorrarRecurso{

    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;

    public UseCaseEliminarRecurso(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }


    @Override
    public Mono<Void> deleteById(String id) {
        return repositorio.deleteById(id);
    }
}
