package co.edu.uniquindio.hospitalproject.model;

import javax.print.Doc;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario {
    //Se crea esta clase para la gestión de horarios (funcionalidad de admin)

    //Atributos
    private String diaSemana;
    private int horaInicio;
    private int minutosInicio;
    private int horaFin;
    private int minutosFin;
    private Doctor doctorAsignado;

    //Constructor
    public Horario(String diaSemana, int horaInicio, int minutosInicio, int horaFin, int minutosFin, Doctor doctorAsignado) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.minutosInicio = minutosInicio;
        this.horaFin = horaFin;
        this.minutosFin = minutosFin;
        this.doctorAsignado = doctorAsignado;
    }

    //Getter's and setter's

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinutosInicio() {
        return minutosInicio;
    }

    public void setMinutosInicio(int minutosInicio) {
        this.minutosInicio = minutosInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getMinutosFin() {
        return minutosFin;
    }

    public void setMinutosFin(int minutosFin) {
        this.minutosFin = minutosFin;
    }

    public Doctor getDoctorAsignado() {
        return doctorAsignado;
    }

    public void setNombreDoctor(Doctor doctorAsignado) {
        this.doctorAsignado = doctorAsignado;
    }
}
