package com.example.ReactivaBibliotecaSpringBootMongoDB.useCases;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class UseCaseBuscarPorArea implements ObtenerPorString{

    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;

    public UseCaseBuscarPorArea(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get(String area) {
        return repositorio.findByArea(area).map(mapper.mapRecursoToDTO());
    }
}
