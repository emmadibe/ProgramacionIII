package TpFinalProgramacionIII.Biblioteca.Repository;

import TpFinalProgramacionIII.Biblioteca.Models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>
{
    List<Prestamo> findByUsuarioId(Long usuarioId);

}
