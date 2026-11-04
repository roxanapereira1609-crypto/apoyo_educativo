
package gt.edu.url.apoyo_educativo.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import gt.edu.url.apoyo_educativo.model.Actividad;
import gt.edu.url.apoyo_educativo.repository.ActividadRepository;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public void guardar(Actividad actividad) {
        actividadRepository.save(actividad);
    }

    public List<Actividad> listarTodos() {
        List<Actividad> actividades = actividadRepository.findAll();

        actividades.sort(
            Comparator.comparingInt(
                (Actividad actividad) -> prioridadEstado(actividad.getEstado())
            ).thenComparing(
                Actividad::getFechaProgramada,
                Comparator.nullsLast(Comparator.naturalOrder())
            )
        );

        return actividades;
    }

    private int prioridadEstado(String estado) {
        if (estado == null) {
            return 3;
        }

        switch (estado.trim().toLowerCase()) {
            case "pendiente":
                return 0;
            case "en proceso":
                return 1;
            case "finalizada":
            case "finalizado":
                return 2;
            default:
                return 3;
        }
    }

    public long contar() {
        return actividadRepository.count();
    }

    public Actividad buscarPorId(Long id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encontró la actividad con ID: " + id
                ));
    }

    public void eliminar(Long id) {
        actividadRepository.deleteById(id);
    }
}
