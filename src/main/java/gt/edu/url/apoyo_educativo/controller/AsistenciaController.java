
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

import gt.edu.url.apoyo_educativo.model.Asistencia;
import gt.edu.url.apoyo_educativo.service.AsistenciaService;

@Controller
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping("/asistencias/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        return "formulario";
    }

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

    @GetMapping("/asistencias")
    public String listarAsistencias(Model model) {
        model.addAttribute("asistencias", asistenciaService.listarTodos());
        model.addAttribute("total", asistenciaService.contar());
        return "lista";
    }

    @PostMapping("/asistencias/eliminar/{id}")
    public String eliminarAsistencia(@PathVariable Long id) {
        try {
            asistenciaService.buscarPorId(id);
            asistenciaService.eliminar(id);
        } catch (NoSuchElementException e) {
            return "redirect:/asistencias";
        }

        return "redirect:/asistencias";
    }

    @GetMapping("/asistencias/editar/{id}")
    public String mostrarFormularioEdicion(
            @PathVariable Long id,
            Model model) {

        try {
            Asistencia asistencia = asistenciaService.buscarPorId(id);
            model.addAttribute("asistencia", asistencia);
            return "formulario";
        } catch (NoSuchElementException e) {
            return "redirect:/asistencias";
        }
    }

    @PostMapping("/asistencias/actualizar/{id}")
    public String actualizarAsistencia(
            @PathVariable Long id,
            @Valid @ModelAttribute("asistencia") Asistencia asistencia,
            BindingResult resultado) {

        try {
            asistenciaService.buscarPorId(id);
        } catch (NoSuchElementException e) {
            return "redirect:/asistencias";
        }

        if (resultado.hasErrors()) {
            asistencia.setId(id);
            return "formulario";
        }

        asistencia.setId(id);
        asistenciaService.guardar(asistencia);

        return "redirect:/asistencias";
    }
}
