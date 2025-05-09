package TpFinalProgramacionIII.Biblioteca.Services;

import TpFinalProgramacionIII.Biblioteca.DTO.PrestamoCreateDTO;
import TpFinalProgramacionIII.Biblioteca.DTO.PrestamoDTO;
import TpFinalProgramacionIII.Biblioteca.Enums.EstadoPrestamo;
import TpFinalProgramacionIII.Biblioteca.Models.Libro;
import TpFinalProgramacionIII.Biblioteca.Models.Prestamo;
import TpFinalProgramacionIII.Biblioteca.Models.Usuario;
import TpFinalProgramacionIII.Biblioteca.Repository.LibroRepository;
import TpFinalProgramacionIII.Biblioteca.Repository.PrestamoRepository;
import TpFinalProgramacionIII.Biblioteca.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PrestamoDTO crearPrestamo(PrestamoCreateDTO prestamoCreateDTO) {
        Libro libro = libroRepository.findById(prestamoCreateDTO.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        if (!libro.isDisponible()) {
            throw new RuntimeException("El libro no está disponible");
        }

        Usuario usuario = usuarioRepository.findById(prestamoCreateDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setEstado(EstadoPrestamo.ACTIVO);

        libro.setDisponible(false);
        libroRepository.save(libro);

        Prestamo savedPrestamo = prestamoRepository.save(prestamo);
        return mapToDTO(savedPrestamo);
    }

    public List<PrestamoDTO> obtenerTodos() {
        return prestamoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PrestamoDTO> obtenerPorId(Long id) {
        return prestamoRepository.findById(id).map(this::mapToDTO);
    }

    public PrestamoDTO devolverLibro(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
        if (prestamo.getEstado() == EstadoPrestamo.DEVUELTO) {
            throw new RuntimeException("El libro ya fue devuelto");
        }

        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.setEstado(EstadoPrestamo.DEVUELTO);

        Libro libro = prestamo.getLibro();
        libro.setDisponible(true);
        libroRepository.save(libro);

        Prestamo updatedPrestamo = prestamoRepository.save(prestamo);
        return mapToDTO(updatedPrestamo);
    }

    private PrestamoDTO mapToDTO(Prestamo prestamo) {
        PrestamoDTO dto = new PrestamoDTO();
        dto.setId(prestamo.getId());
        dto.setLibroId(prestamo.getLibro().getId());
        dto.setUsuarioId(prestamo.getUsuario().getId());
        dto.setFechaPrestamo(prestamo.getFechaPrestamo());
        dto.setFechaDevolucion(prestamo.getFechaDevolucion());
        dto.setEstado(prestamo.getEstado().name());
        return dto;
    }


}