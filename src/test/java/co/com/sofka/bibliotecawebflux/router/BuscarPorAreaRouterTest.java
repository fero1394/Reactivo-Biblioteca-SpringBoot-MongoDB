package co.com.sofka.bibliotecawebflux.router;

import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.model.Recurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.router.BuscarPorAreaRouter;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases.UseCaseBuscarPorArea;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {BuscarPorAreaRouter.class, UseCaseBuscarPorArea.class, RecursoMapper.class})
class BuscarPorAreaRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void buscarPorAreaTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.ARTES);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setNombre("Documental");
        recurso1.setFecha(LocalDate.now());

        Recurso recurso2 = new Recurso();
        recurso2.setId("yyy");
        recurso2.setArea(Area.ARTES);
        recurso2.setDisponible(true);
        recurso2.setTipo(Tipo.LIBRO);
        recurso2.setNombre("Libro");
        recurso2.setFecha(LocalDate.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findByArea(recurso1.getArea().toString())).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos/filtrarArea/ARTES")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getArea()).isEqualTo(recurso1.getArea());
                            Assertions.assertThat(userResponse.get(1).getArea()).isEqualTo(recurso2.getArea());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findByArea("ARTES");
    }

}