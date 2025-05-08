package TpFinalProgramacionIII.Biblioteca.Services;

import TpFinalProgramacionIII.Biblioteca.Models.Libro;
import TpFinalProgramacionIII.Biblioteca.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import lombok.Data;

@Service //LE digo al Spring que esta clase es un servicio. Esto me permite inyectar dependencias a partir de la anotación @Autowired. Con esa anotación, el contenedor ya sabe que la clase es un Bean.
public class LibroService {
    @Autowired //Inyecto dependencias por campo. En este caso, inyecto una dependencia de LibroRepository.
    private LibroRepository libroRepository;

    //Cada método de la clase servicio corresponde a una operación de negocio: crear un libro, eliminar un libro, atualizar un libro, obtener todos los libros...

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro actualizarLibro(Long id, Libro libro) {
        libro.setId(id);
        return libroRepository.save(libro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libro> buscarDisponibles() {
        return libroRepository.findByDisponibleTrue();
    }
}