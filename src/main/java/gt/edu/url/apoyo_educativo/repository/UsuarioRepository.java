
package gt.edu.url.apoyo_educativo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.url.apoyo_educativo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
