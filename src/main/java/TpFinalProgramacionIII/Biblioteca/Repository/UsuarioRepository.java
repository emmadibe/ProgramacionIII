package TpFinalProgramacionIII.Biblioteca.Repository;

import TpFinalProgramacionIII.Biblioteca.Models.Usuario;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    List<Usuario> findByName(String name);

    Optional<UserDetails> findByEmail(String username);
}
