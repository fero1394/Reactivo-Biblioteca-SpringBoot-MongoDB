package com.example.ReactivaBibliotecaSpringBootMongoDB.repository;

import com.example.ReactivaBibliotecaSpringBootMongoDB.model.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RepositorioRecurso extends ReactiveMongoRepository<Recurso, String> {
    Flux<Recurso> findByArea(String area);
    Flux<Recurso> findByTipo(String tipo);
    Flux<Recurso> findByAreaAndTipo(String area, String tipo);
}
