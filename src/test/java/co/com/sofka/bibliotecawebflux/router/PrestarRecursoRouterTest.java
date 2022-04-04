package co.com.sofka.bibliotecawebflux.router;

import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.model.Recurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.router.PrestarRecursoRouter;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases.UseCasePrestarRecurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.utils.Area;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.utils.Tipo;
import org.assertj.core.api.Assertions;
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

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PrestarRecursoRouter.class, UseCasePrestarRecurso.class, RecursoMapper.class})
class PrestarRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void PrestarRecursoTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.ARTES);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFecha(LocalDate.now());



        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/prestar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo("El recurso fue prestado con exito");
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");

    }

    @Test
    public void PrestarRecursoNoEsPosibleTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.ARTES);
        recurso1.setDisponible(false);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFecha(LocalDate.now());



        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/prestar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo("El recurso no está disponible");
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");

    }

}