package TpFinalProgramacionIII.Biblioteca.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoDTO {
    private Long id;

    @NotNull(message = "El ID del libro es obligatorio")
    private Long libroId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado;
}