package co.edu.uniquindio.hospitalproject.controller;

import co.edu.uniquindio.hospitalproject.model.Doctor;
import co.edu.uniquindio.hospitalproject.model.Horario;
import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.model.Persona;

import java.util.Collection;

public class CrudHorarioController {

    Hospital hospital;

    public CrudHorarioController(Hospital hospital) {
        this.hospital = hospital;
    }

    public Doctor buscarDoctor(String cedula) {
        return hospital.buscarDoctorID(cedula);
    }

    public boolean addHorario(Horario horario) {
        boolean agregado = hospital.addHorario(horario);
        if(agregado) {
            Doctor doctor = hospital.buscarDoctorID(horario.getDoctorAsignado().getCedula());
            if(doctor != null) {
                doctor.agregarHorario(horario);
            }
        }
        return agregado;
    }

    public boolean updateHorario(Horario horario) {
        boolean actualizado = hospital.updateHorario(horario);
        if(actualizado) {
            Doctor doctorAct = hospital.buscarDoctorID(horario.getDoctorAsignado().getCedula());
            if(doctorAct != null) {
                doctorAct.actualizarHorario(horario);
            }
        }
        return actualizado;
    }

    public boolean deleteHorario(Horario horario) {
        boolean eliminado = hospital.deleteHorario(horario);
        if(eliminado) {
            Doctor doctorEliminar = hospital.buscarDoctorID(horario.getDoctorAsignado().getCedula());
            if(doctorEliminar != null) {
                doctorEliminar.borrarHorario(horario);
            }
        }
        return eliminado;
    }

    public Collection<Horario> listHorarios() {
        return hospital.listarHorarios();
    }
}
