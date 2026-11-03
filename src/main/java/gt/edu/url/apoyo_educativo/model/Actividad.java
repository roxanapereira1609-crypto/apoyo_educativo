
package gt.edu.url.apoyo_educativo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debes escribir el nombre de la actividad.")
    @Size(min = 3, max = 100,
          message = "El nombre de la actividad debe tener entre 3 y 100 caracteres.")
    private String nombreActividad;

@NotNull(message = "Debes seleccionar la fecha de la actividad.")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
private LocalDate fechaProgramada;

    @NotBlank(message = "Debes escribir el nombre del docente responsable.")
    @Size(min = 3, max = 80,
          message = "El nombre del docente debe tener entre 3 y 80 caracteres.")
    private String docenteResponsable;

    @NotBlank(message = "Debes seleccionar el estado de la actividad.")
    private String estado;

    public Actividad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getDocenteResponsable() {
        return docenteResponsable;
    }

    public void setDocenteResponsable(String docenteResponsable) {
        this.docenteResponsable = docenteResponsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
