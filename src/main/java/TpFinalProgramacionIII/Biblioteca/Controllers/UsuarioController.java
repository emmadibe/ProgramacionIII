package TpFinalProgramacionIII.Biblioteca.Controllers;

import TpFinalProgramacionIII.Biblioteca.Models.Usuario;
import TpFinalProgramacionIII.Biblioteca.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController
{
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping //Acordate que el método Post es para crear
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> crearPrestamo(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @GetMapping //El método Get es para obtener
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) { //La anotación PathVariable lo que hace es capturar el valor de la variable id (la cual es la que aparece en GetMapping) y la transforma a tipo Long. Pues, llega en formato String. El método usa ese id para buscar el usuario deseado.
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}") //El método PUT es para actualizar.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> actualizarPrestamo(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}") //El método Delete es para borrar.
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
