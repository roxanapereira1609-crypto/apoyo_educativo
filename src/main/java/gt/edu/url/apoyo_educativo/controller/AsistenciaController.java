
package gt.edu.url.apoyo_educativo.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import gt.edu.url.apoyo_educativo.model.Asistencia;
import gt.edu.url.apoyo_educativo.service.AsistenciaService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    // Mostrar el formulario
    @GetMapping("/asistencias/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        return "formulario";
    }

    // Validar y guardar la asistencia
    @PostMapping("/asistencias")
    public String registrarAsistencia(
            @Valid @ModelAttribute("asistencia") Asistencia asistencia,
            BindingResult resultado) {

        if (resultado.hasErrors()) {
            return "formulario";
        }

        asistenciaService.guardar(asistencia);
        return "redirect:/asistencias";
    }

    // Mostrar los registros
    @GetMapping("/asistencias")
    public String listarAsistencias(Model model) {
        model.addAttribute("asistencias", asistenciaService.listarTodos());
        model.addAttribute("total", asistenciaService.contar());
        return "lista";
    }
    
    // Eliminar una asistencia registrada
    @PostMapping("/asistencias/eliminar/{id}")
    public String eliminarAsistencia(@PathVariable Long id) {

        asistenciaService.eliminar(id);

        return "redirect:/asistencias";
    }
    
    // Mostrar el formulario para editar una asistencia
    @GetMapping("/asistencias/editar/{id}")
    public String mostrarFormularioEdicion(
            @PathVariable Long id,
            Model model) {

        Asistencia asistencia = asistenciaService.buscarPorId(id);

        if (asistencia == null) {
            return "redirect:/asistencias";
        }

        model.addAttribute("asistencia", asistencia);

        return "formulario";
    }

    // Actualizar una asistencia existente
    @PostMapping("/asistencias/actualizar/{id}")
    public String actualizarAsistencia(
            @PathVariable Long id,
            @Valid @ModelAttribute("asistencia") Asistencia asistencia,
            BindingResult resultado) {

        // Comprobar que el registro existe
        Asistencia asistenciaExistente = asistenciaService.buscarPorId(id);

        if (asistenciaExistente == null) {
            return "redirect:/asistencias";
        }

        // Si hay errores, regresar al formulario
        if (resultado.hasErrors()) {
            asistencia.setId(id);
            return "formulario";
        }

        // Conservar el identificador original
        asistencia.setId(id);

        // Guardar los cambios y recalcular la puntualidad
        asistenciaService.guardar(asistencia);

        return "redirect:/asistencias";
    }


}
