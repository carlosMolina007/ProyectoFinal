package co.edu.uniquindio.hospitalproject.controller;

import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.model.Sala;
import java.util.Collection;

public class OcupacionHospitalController {

    Hospital hospital;
    Collection<Sala> listSalasVerificar;

    public OcupacionHospitalController(Hospital hospital) {
        this.hospital = hospital;
        listSalasVerificar = hospital.listarSala();
    }



    public int cantdSalasHospital() {
        return listSalasVerificar.size();
    }

    public int cantSalasOcupadas(){
        int contadorSalas = 0;
        for (Sala sala : listSalasVerificar) {
            if (!sala.getEstadoSala()){
                contadorSalas++;
            }
        }
        return contadorSalas;
    }

    public int porcentajeOcupacionHospital() {
        int cantSalasTotal = cantdSalasHospital();
        int cantSalasOcupadas = cantSalasOcupadas();

        return (int) (((double) cantSalasOcupadas / cantSalasTotal) * 100);
    }

}
