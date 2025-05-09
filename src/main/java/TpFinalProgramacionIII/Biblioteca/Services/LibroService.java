package TpFinalProgramacionIII.Biblioteca.Services;


import TpFinalProgramacionIII.Biblioteca.DTO.LibroDTO;
import TpFinalProgramacionIII.Biblioteca.Models.Libro;
import TpFinalProgramacionIII.Biblioteca.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public LibroDTO crearLibro(com.biblioteca.dto.LibroCreateDTO libroDTO) {
        Libro libro = new Libro();
        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setAnioPublicacion(libroDTO.getAnioPublicacion());
        libro.setDisponible(true);

        Libro savedLibro = libroRepository.save(libro);
        return mapToDTO(savedLibro);
    }

    public List<LibroDTO> obtenerTodos() {
        return libroRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<LibroDTO> obtenerPorId(Long id) {
        return libroRepository.findById(id).map(this::mapToDTO);
    }

    public LibroDTO actualizarLibro(Long id, com.biblioteca.dto.LibroCreateDTO libroDTO) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setAnioPublicacion(libroDTO.getAnioPublicacion());

        Libro updatedLibro = libroRepository.save(libro);
        return mapToDTO(updatedLibro);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public List<LibroDTO> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<LibroDTO> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<LibroDTO> buscarDisponibles() {
        return libroRepository.findByDisponibleTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LibroDTO mapToDTO(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAutor(libro.getAutor());
        dto.setIsbn(libro.getIsbn());
        dto.setAnioPublicacion(libro.getAnioPublicacion());
        dto.setDisponible(libro.isDisponible());
        return dto;
    }
}