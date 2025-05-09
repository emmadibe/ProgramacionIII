package TpFinalProgramacionIII.Biblioteca.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    private String rol;
}