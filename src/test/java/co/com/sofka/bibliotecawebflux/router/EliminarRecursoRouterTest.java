package co.com.sofka.bibliotecawebflux.router;

import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.model.Recurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.router.EliminarRecursoRouter;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases.UseCaseEliminarRecurso;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {EliminarRecursoRouter.class, UseCaseEliminarRecurso.class, RecursoMapper.class})
class EliminarRecursoRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void eliminarRecursoTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");

        when(repositorio.deleteById("xxx")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/recursos/eliminar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        Mockito.verify(repositorio,Mockito.times(1)).deleteById("xxx");
    }
}