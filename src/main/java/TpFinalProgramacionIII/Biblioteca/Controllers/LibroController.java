package TpFinalProgramacionIII.Biblioteca.Controllers;


import TpFinalProgramacionIII.Biblioteca.Models.Libro;
import TpFinalProgramacionIII.Biblioteca.Services.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Con esa anotación, proporcionada por el módulo de Spring Spring MVC, el contenedor ya sabe que la clase es un Bean. Es la anotación usada en una APIRest. Devuelve un archivo Json, en vez de un HTML como  lo haría un @Controller.
@RequestMapping(value = "/api/libros") //Anotación proporcionada por el módulo de Spring Spring MVC. RequestMapping a nivel clase. Establece la ruta base de todos los métodos de este controlador. O sea, todos los métodos de este controlador tendrán como ruta base /api/libros. Todos los endpoint tendrán esa ruta base. Luego, cambia el método por delante: GET, POST... Por ejemplo, un método con la anotación PostMapping tendrá como ruta o endpoint Post/api/libros...
//Es que la anotación @RequestMapping a nivel clase se usa para definir la ruta común a todos los endpoints. Luego, en cada método particular de la clase, se definirá el método usando las anotaciones abreviadas de @RequestMapping. Por ejemplo, @GetMapping.
public class LibroController {
    @Autowired //Anotación, que ya existe en el corazón del framwork de Spring, para inyectar la dependencia. LibroService, al tener la anotación @Service, también es un Bean.
    private LibroService libroService;

    @PostMapping //Anotación proporcionada por Spring MVC. Acordate que el método Post es para crear
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Libro> crearLibro(@Valid @RequestBody Libro libro) { //@Valid lo que hace es comprobar que el objeto libro cuente con las validaciones definidas por las anotaciones, que me proporciona la API que nos da Jakarta Validation, en la clase Libro (por ejemplo, el size, los campos obligatorios...). @RequestBody, anotación proporcionada por el módulo de Spring Spring MVC, deserializa lo que se envía a la solicitud http (una instancia de Libro, en este caso).
        return ResponseEntity.ok(libroService.crearLibro(libro));
    }

    @GetMapping //El método Get es para obtener
    public ResponseEntity<List<Libro>> obtenerTodos() {
        return ResponseEntity.ok(libroService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) //La anotación PathVariable, proporcionada por el módulo de Spring Spring MVC, lo que hace es capturar el valor de la variable id (la cual es la que aparece en GetMapping) y la transforma a tipo Long. Pues, llega en formato String. El método usa ese id para buscar el libro deseado.
    {
        return libroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}") //El método PUT es para actualizar.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @Valid @RequestBody Libro libro)
    {
        return ResponseEntity.ok(libroService.actualizarLibro(id, libro));
    }

    @DeleteMapping("/{id}") //El método Delete es para borrar.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Libro>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
    }

    @GetMapping("/buscar/autor")
    public ResponseEntity<List<Libro>> buscarPorAutor(@RequestParam String autor) {
        return ResponseEntity.ok(libroService.buscarPorAutor(autor));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Libro>> buscarDisponibles() {
        return ResponseEntity.ok(libroService.buscarDisponibles());
    }
}