
package gt.edu.url.apoyo_educativo.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gt.edu.url.apoyo_educativo.model.Actividad;
import gt.edu.url.apoyo_educativo.repository.ActividadRepository;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    // Guardar una actividad nueva o actualizar una existente
    public void guardar(Actividad actividad) {
        actividadRepository.save(actividad);
    }

    // Consultar las actividades ordenadas por fecha
    public List<Actividad> listarTodos() {
        return actividadRepository.findAll(
            Sort.by(Sort.Direction.ASC, "fechaProgramada")
        );
    }

    // Contar las actividades registradas
    public long contar() {
        return actividadRepository.count();
    }

    // Buscar una actividad por su identificador
    public Actividad buscarPorId(Long id) {
        return actividadRepository.findById(id).orElse(null);
    }

    // Eliminar una actividad existente
    public void eliminar(Long id) {
        actividadRepository.deleteById(id);
    }
}
