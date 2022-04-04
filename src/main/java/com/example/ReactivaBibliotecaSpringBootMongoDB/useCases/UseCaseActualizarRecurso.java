package com.example.ReactivaBibliotecaSpringBootMongoDB.useCases;

import com.example.ReactivaBibliotecaSpringBootMongoDB.mapper.RecursoMapper;
import com.example.ReactivaBibliotecaSpringBootMongoDB.repository.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseActualizarRecurso implements GuardarRecurso{

    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;

    public UseCaseActualizarRecurso(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        return repositorio.save(mapper.mapperToRecurso(recursoDTO.getId()).apply(recursoDTO)).map(mapper.mapRecursoToDTO());
    }
}
