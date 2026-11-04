
package gt.edu.url.apoyo_educativo.controller;

import java.util.NoSuchElementException;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gt.edu.url.apoyo_educativo.model.Actividad;
import gt.edu.url.apoyo_educativo.service.ActividadService;

@Controller
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping("/actividades/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("actividad", new Actividad());
        return "formulario-actividad";
    }
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
    @GetMapping("/actividades")
    public String listarActividades(Model model) {
        model.addAttribute("actividades", actividadService.listarTodos());
        model.addAttribute("total", actividadService.contar());
        return "lista-actividades";
    }
    @GetMapping("/actividades/editar/{id}")
    public String mostrarFormularioEdicion(
            @PathVariable Long id,
            Model model) {

        try {
            Actividad actividad = actividadService.buscarPorId(id);
            model.addAttribute("actividad", actividad);
            return "formulario-actividad";
        } catch (NoSuchElementException e) {
            return "redirect:/actividades";
        }
    }

    @PostMapping("/actividades/actualizar/{id}")
    public String actualizarActividad(
            @PathVariable Long id,
            @Valid @ModelAttribute("actividad") Actividad actividad,
            BindingResult resultado) {

        try {
            actividadService.buscarPorId(id);
        } catch (NoSuchElementException e) {
            return "redirect:/actividades";
        }

        if (resultado.hasErrors()) {
            actividad.setId(id);
            return "formulario-actividad";
        }

        actividad.setId(id);
        actividadService.guardar(actividad);

        return "redirect:/actividades";
    }

    @PostMapping("/actividades/eliminar/{id}")
    public String eliminarActividad(@PathVariable Long id) {

        try {
            actividadService.buscarPorId(id);
            actividadService.eliminar(id);
        } catch (NoSuchElementException e) {
            return "redirect:/actividades";
        }

        return "redirect:/actividades";
    }
}
