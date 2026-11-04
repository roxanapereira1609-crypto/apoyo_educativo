
package gt.edu.url.apoyo_educativo.service;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import gt.edu.url.apoyo_educativo.model.Asistencia;
import gt.edu.url.apoyo_educativo.repository.AsistenciaRepository;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public void guardar(Asistencia asistencia) {
        LocalTime horaLimite = LocalTime.of(13, 10);

        if (asistencia.getHoraIngreso().isAfter(horaLimite)) {
            asistencia.setEstado("Tarde");
        } else {
            asistencia.setEstado("Puntual");
        }

        asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> listarTodos() {
        return asistenciaRepository.findAll();
    }

    public long contar() {
        return asistenciaRepository.count();
    }

    public void eliminar(Long id) {
        asistenciaRepository.deleteById(id);
    }

    public Asistencia buscarPorId(Long id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encontró la asistencia con ID: " + id
                ));
    }
}
