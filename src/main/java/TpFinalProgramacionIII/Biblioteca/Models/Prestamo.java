package TpFinalProgramacionIII.Biblioteca.Models;

import TpFinalProgramacionIII.Biblioteca.Enums.EstadoPrestamo;
import TpFinalProgramacionIII.Biblioteca.Models.Libro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity //JPA
@Data //Lombok
public class Prestamo {
    @Id //JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA
    private Long id;

    @ManyToOne //JPA
    @JoinColumn(name = "libro_id") //JPA
    @NotNull(message = "El libro es obligatorio") //JBV
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "El usuario es obligatorio")
    private Usuario usuario;

    @NotNull(message = "La fecha de préstamo es obligatoria")
    private LocalDate fechaPrestamo;

    private LocalDate fechaDevolucion;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado = EstadoPrestamo.ACTIVO;
}