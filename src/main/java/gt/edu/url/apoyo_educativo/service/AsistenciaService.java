
package gt.edu.url.apoyo_educativo.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import gt.edu.url.apoyo_educativo.model.Asistencia;
import gt.edu.url.apoyo_educativo.repository.AsistenciaRepository;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    // Guardar una asistencia y calcular su puntualidad
    public void guardar(Asistencia asistencia) {

        LocalTime horaLimite = LocalTime.of(13, 10);

        if (asistencia.getHoraIngreso().isAfter(horaLimite)) {
            asistencia.setEstado("Tarde");
        } else {
            asistencia.setEstado("Puntual");
        }

        asistenciaRepository.save(asistencia);
    }

    // Consultar todas las asistencias registradas
    public List<Asistencia> listarTodos() {
        return asistenciaRepository.findAll();
    }

    // Contar los registros de asistencia
    public long contar() {
        return asistenciaRepository.count();
    }
    
    // Eliminar una asistencia por su identificador
    public void eliminar(Long id) {
        asistenciaRepository.deleteById(id);
    }

    // Buscar una asistencia por su identificador
    public Asistencia buscarPorId(Long id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

}
