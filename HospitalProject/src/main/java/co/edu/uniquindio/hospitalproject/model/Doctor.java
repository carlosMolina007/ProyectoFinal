package co.edu.uniquindio.hospitalproject.model;

import co.edu.uniquindio.hospitalproject.model.enums.TipoSangre;
import co.edu.uniquindio.hospitalproject.model.enums.Genero;
import co.edu.uniquindio.hospitalproject.model.enums.Especializacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Persona{

    //Atributos propios de Doctor
    private Especializacion especializacion;
    private String idProfesional;
    private Boolean doctorDisponible;
    private List<Horario> horariosAtencion = new ArrayList<>(); //Atributo para la gestión de horarios

    //Constructor de Doctor extendido de Persona
    public Doctor(String id, String nombre, String apellido, LocalDate fechaNacimiento,
                  Genero genero, TipoSangre tipoSangre, Especializacion especializacion,
                  String idProfesional, Boolean doctorDisponible) {
        super(id, nombre, apellido, fechaNacimiento, genero, tipoSangre);
        this.especializacion = especializacion;
        this.idProfesional = idProfesional;
        this.doctorDisponible = doctorDisponible;
    }

    //Metodos para la gestión de horarios
    public void agregarHorario(Horario horario) {
        horariosAtencion.add(horario);
    }

    public void actualizarHorario(Horario nuevoHorario) {
        for (int i = 0; i < horariosAtencion.size(); i++) {
            Horario actual = horariosAtencion.get(i);
            if (actual.getDiaSemana().equals(nuevoHorario.getDiaSemana())) {
                horariosAtencion.set(i, nuevoHorario);
                break;
            }
        }
    }

    public void borrarHorario(Horario horario) {
        for (int i = 0; i < horariosAtencion.size(); i++) {
            Horario actual = horariosAtencion.get(i);
            if (actual.getDiaSemana().equals(horario.getDiaSemana())) {
                horariosAtencion.remove(i);
                break;
            }
        }
    }

    public List<Horario> getHorariosAtencion() {
        return horariosAtencion;
    }

    //Getter's and setter's

    public Especializacion getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(Especializacion especializacion) {
        this.especializacion = especializacion;
    }

    public String getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(String idProfesional) {
        this.idProfesional = idProfesional;
    }

    public Boolean getDoctorDisponible() {
        return doctorDisponible;
    }

    public void setDoctorDisponible(Boolean doctorDisponible) {
        this.doctorDisponible = doctorDisponible;
    }
}
