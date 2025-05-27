package co.edu.uniquindio.hospitalproject.model.Interfaces;

import co.edu.uniquindio.hospitalproject.model.CitaMedica;

import java.util.Collection;

public interface ICRUDCitaMedica {

    boolean crearCitaMedica(CitaMedica citaMedica);
    boolean actualizarCitaMedica(CitaMedica citaMedica);
    boolean eliminarCitaMedica(CitaMedica citaMedica);
    Collection<CitaMedica> listarCitasMedicas();

}
