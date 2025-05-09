package TpFinalProgramacionIII.Biblioteca.Controllers;

import TpFinalProgramacionIII.Biblioteca.DTO.PrestamoCreateDTO;
import TpFinalProgramacionIII.Biblioteca.DTO.PrestamoDTO;
import TpFinalProgramacionIII.Biblioteca.Models.Libro;
import TpFinalProgramacionIII.Biblioteca.Models.Prestamo;
import TpFinalProgramacionIII.Biblioteca.Services.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/prestamos")
public class PrestamoController
{
    @Autowired
    private PrestamoService prestamoService;

    @PostMapping //Acordate que el método Post es para crear
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PrestamoDTO> crearPrestamo(@Valid @RequestBody PrestamoCreateDTO prestamo) {
        return ResponseEntity.ok(prestamoService.crearPrestamo(prestamo));
    }

    @GetMapping //El método Get es para obtener
    public ResponseEntity<List<PrestamoDTO>> obtenerTodos() {
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> obtenerPorId(@PathVariable Long id) { //La anotación PathVariable lo que hace es capturar el valor de la variable id (la cual es la que aparece en GetMapping) y la transforma a tipo Long. Pues, llega en formato String. El método usa ese id para buscar el prestamo deseado.
        return prestamoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
