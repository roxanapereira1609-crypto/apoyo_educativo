
package gt.edu.url.apoyo_educativo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gt.edu.url.apoyo_educativo.model.Actividad;
import gt.edu.url.apoyo_educativo.service.ActividadService;

import jakarta.validation.Valid;

@Controller
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    // Mostrar el formulario para registrar una actividad
    @GetMapping("/actividades/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute("actividad", new Actividad());

        return "formulario-actividad";
    }

    // Guardar una actividad nueva
    @PostMapping("/actividades")
    public String registrarActividad(
            @Valid @ModelAttribute("actividad") Actividad actividad,
            BindingResult resultado) {

        if (resultado.hasErrors()) {
            return "formulario-actividad";
        }

        actividadService.guardar(actividad);

        return "redirect:/actividades";
    }

    // Mostrar todas las actividades registradas
    @GetMapping("/actividades")
    public String listarActividades(Model model) {

        model.addAttribute(
            "actividades",
            actividadService.listarTodos()
        );

        model.addAttribute(
            "total",
            actividadService.contar()
        );

        return "lista-actividades";
    }

    // Mostrar el formulario para editar una actividad
    @GetMapping("/actividades/editar/{id}")
    public String mostrarFormularioEdicion(
            @PathVariable Long id,
            Model model) {

        Actividad actividad = actividadService.buscarPorId(id);

        if (actividad == null) {
            return "redirect:/actividades";
        }

        model.addAttribute("actividad", actividad);

        return "formulario-actividad";
    }

    // Actualizar una actividad existente
    @PostMapping("/actividades/actualizar/{id}")
    public String actualizarActividad(
            @PathVariable Long id,
            @Valid @ModelAttribute("actividad") Actividad actividad,
            BindingResult resultado) {

        Actividad actividadExistente = actividadService.buscarPorId(id);

        if (actividadExistente == null) {
            return "redirect:/actividades";
        }

        if (resultado.hasErrors()) {
            actividad.setId(id);
            return "formulario-actividad";
        }

        // Mantener el identificador original
        actividad.setId(id);

        // Actualizar los datos en H2
        actividadService.guardar(actividad);

        return "redirect:/actividades";
    }

    // Eliminar una actividad registrada
    @PostMapping("/actividades/eliminar/{id}")
    public String eliminarActividad(@PathVariable Long id) {

        if (actividadService.buscarPorId(id) != null) {
            actividadService.eliminar(id);
        }

        return "redirect:/actividades";
    }
}
