
package TpFinalProgramacionIII.Biblioteca.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity //Anotación, proporcionada por JPA, que marca a la clase como una entidad, lo cual hace que sus atributos sean mapeables en la base de datos. Al ser una entidad, Spring ya toma a la clase como una tabla de la bdd y a sus atributos como los campos
@Table(name="libros", schema="public") //Anotación, proporcionada por JPA, que me permite personalizar el nombre de la tabla en la base de datos. Si no pongo esta anotación, el nombre de la tabla será idéntico al nombre de la clase.
@Data//Anotación, proporcionada por Lombok, que proporciona de manera automática métodos básicos: getter, setter, builder, equals, compareTo, toString y hasDat.
public class Libro {
    @Id //Anotación, proporcionada por JPA, que marca al atributo de abajo como el Id. Es obligatorio esta anotación.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Digo que es autoincremental
    private Long id;

    @NotBlank(message = "El título es obligatorio") //Anotación de validación, proporcionada por la API que nos da Jakarta Validation, que vuelve obligatorio a este campo.
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

    private boolean disponible = true;
}


