
package gt.edu.url.apoyo_educativo.security;

import gt.edu.url.apoyo_educativo.model.Usuario;
import gt.edu.url.apoyo_educativo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadorUsuarios implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.docente.password}")
    private String docentePassword;

    public CargadorUsuarios(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario(
                    "admin",
                    passwordEncoder.encode(adminPassword),
                    "ADMIN"
            );
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByUsername("docente").isEmpty()) {
            Usuario docente = new Usuario(
                    "docente",
                    passwordEncoder.encode(docentePassword),
                    "DOCENTE"
            );
            usuarioRepository.save(docente);
        }
    }
}
