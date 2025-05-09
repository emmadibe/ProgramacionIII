package TpFinalProgramacionIII.Biblioteca.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrestamoCreateDTO {
    @NotNull(message = "El ID del libro es obligatorio")
    private Long libroId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;
}