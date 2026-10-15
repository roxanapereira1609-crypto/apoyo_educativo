
package gt.edu.url.apoyo_educativo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BienvenidaController {

    @GetMapping("/api/saludo")
    public String saludo() {
        return "¡Bienvenido(a) a Apoyo Educativo!";
    }

    @GetMapping("/api/bienvenida")
    public String bienvenida(
            @RequestParam(defaultValue = "visitante") String nombre) {
        return "Hola, " + nombre
                + ". Gracias por visitar Apoyo Educativo.";
    }
}
