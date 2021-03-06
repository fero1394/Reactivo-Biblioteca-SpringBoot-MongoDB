package co.com.sofka.bibliotecaSpringBootMongoDBReactiva.router;

import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.useCases.UseCaseEliminarRecurso;
import co.com.sofka.bibliotecaSpringBootMongoDBReactiva.dto.RecursoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class EliminarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> eliminarRecurso(UseCaseEliminarRecurso useCaseEliminarRecurso) {
        return route(DELETE("/recursos/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(useCaseEliminarRecurso.deleteById(request.pathVariable("id")), RecursoDTO.class)
        );
    }
}
