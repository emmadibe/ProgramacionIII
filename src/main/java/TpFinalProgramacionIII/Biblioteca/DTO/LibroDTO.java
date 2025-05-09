package TpFinalProgramacionIII.Biblioteca.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LibroDTO {
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder los 100 caracteres")
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 100, message = "El autor no puede exceder los 100 caracteres")
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(min = 10, max = 13, message = "El ISBN debe tener entre 10 y 13 caracteres")
    private String isbn;

    @Min(value = 1800, message = "El año debe ser mayor a 1800")
    private int anioPublicacion;

    private boolean disponible;
}