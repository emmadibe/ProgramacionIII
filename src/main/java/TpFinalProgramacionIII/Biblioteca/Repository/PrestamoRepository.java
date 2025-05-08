package TpFinalProgramacionIII.Biblioteca.Repository;

import TpFinalProgramacionIII.Biblioteca.Models.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>
{
}
