package TpFinalProgramacionIII.Biblioteca.Repository;

import TpFinalProgramacionIII.Biblioteca.Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Anotación, proporcionada por JPA, que marca a la clase como un repositorio. Eso me permitirá inyectar dependencias de esta clase en otra. Se hace necesario ello en la clase servicio, dado que necesito los métodos de consulta del repositorio. Con esa anotación, el contenedor ya sabe que la clase es un Bean.
public interface LibroRepository extends JpaRepository<Libro, Long> //Al exteder de JpaRepository, ya tiene acceso a las consultas básicas del CRUD.
{
    //Dado que, al extender de Jpa, tengo acceso todos los métodos básicos del CRUD, solo debo escribir la firma de aquellos métodos más personalizados como buscar a un libro por su autor, por su título o por si está disponible.
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    List<Libro> findByDisponibleTrue();
}