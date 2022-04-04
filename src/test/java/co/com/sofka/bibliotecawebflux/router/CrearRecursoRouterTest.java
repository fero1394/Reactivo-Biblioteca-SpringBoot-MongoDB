package co.com.sofka.bibliotecawebflux.router;

import co.com.sofka.bibliotecawebflux.collections.Recurso;
import co.com.sofka.bibliotecawebflux.dto.RecursoDTO;
import co.com.sofka.bibliotecawebflux.mapper.RecursoMapper;
import co.com.sofka.bibliotecawebflux.repository.RepositorioRecurso;
import co.com.sofka.bibliotecawebflux.useCases.UseCaseCrear;
import co.com.sofka.bibliotecawebflux.utils.Area;
import co.com.sofka.bibliotecawebflux.utils.Tipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearRecursoRouter.class, UseCaseCrear.class, RecursoMapper.class})
class CrearRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCrearRecurso() {

        Recurso recurso = new Recurso();
        recurso.setId("xxx");
        recurso.setArea(Area.ARTES);
        recurso.setDisponible(true);
        recurso.setTipo(Tipo.DOCUMENTAL);
        recurso.setNombre("Documental");
        recurso.setFecha(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(recurso.getId(),
                recurso.getTipo(),
                recurso.isDisponible(),
                recurso.getArea(),
                recurso.getNombre(),
                recurso.getFecha());

        Mono<Recurso> datoMono = Mono.just(recurso);

        when(repositorio.save(any())).thenReturn(datoMono);

        webTestClient.post()
                .uri("/recursos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getArea()).isEqualTo(recurso.getArea());
                            Assertions.assertThat(userResponse.getTipo()).isEqualTo(recurso.getTipo());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso.getNombre());
                            Assertions.assertThat(userResponse.isDisponible()).isEqualTo(recurso.isDisponible());
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso.getId());
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).save(any());
    }


}