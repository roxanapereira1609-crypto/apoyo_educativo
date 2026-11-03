
package gt.edu.url.apoyo_educativo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Es obligatorio escribir el nombre del docente.")
    @Size(min = 3, max = 80,
          message = "El nombre debe tener entre 3 y 80 caracteres.")
    private String nombreDocente;
    
@NotNull(message = "Debes seleccionar la fecha de asistencia.")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
private LocalDate fecha;


@NotNull(message = "Debes indicar la hora de ingreso.")
@DateTimeFormat(pattern = "HH:mm")
private LocalTime horaIngreso;

    private String estado;

    public Asistencia() {
    }

    public Asistencia(String nombreDocente, LocalDate fecha,
                      LocalTime horaIngreso, String estado) {
        this.nombreDocente = nombreDocente;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
