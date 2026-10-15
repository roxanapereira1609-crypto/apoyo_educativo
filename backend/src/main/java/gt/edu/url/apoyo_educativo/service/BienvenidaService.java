
package gt.edu.url.apoyo_educativo.service;

import org.springframework.stereotype.Service;
import gt.edu.url.apoyo_educativo.model.Visitante;

@Service
public class BienvenidaService {

    private static final String INSTITUCION = "Apoyo Educativo";

    public String mensajeGeneral() {
        return "¡Bienvenido(a) a " + INSTITUCION + "!";
    }

    public String mensajePara(String nombre) {
        Visitante visitante = new Visitante(nombre);

        return "Hola, " + visitante.getNombre()
                + ". Gracias por visitar " + INSTITUCION + ".";
    }
}
