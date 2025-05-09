package TpFinalProgramacionIII.Biblioteca.Services;

import TpFinalProgramacionIII.Biblioteca.DTO.UsuarioCreateDTO;
import TpFinalProgramacionIII.Biblioteca.DTO.UsuarioDTO;
import TpFinalProgramacionIII.Biblioteca.Enums.Rol;
import TpFinalProgramacionIII.Biblioteca.Models.Usuario;
import TpFinalProgramacionIII.Biblioteca.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO crearUsuario(UsuarioCreateDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasena()));
        usuario.setRol(Rol.valueOf(usuarioDTO.getRol()));

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return mapToDTO(savedUsuario);
    }

    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> obtenerPorId(Long id) {
        return usuarioRepository.findById(id).map(this::mapToDTO);
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioCreateDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasena()));
        usuario.setRol(Rol.valueOf(usuarioDTO.getRol()));

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return mapToDTO(updatedUsuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioDTO> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email).map(this::mapToDTO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    private UsuarioDTO mapToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol().name());
        return dto;
    }
}