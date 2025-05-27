package co.edu.uniquindio.hospitalproject.model.Interfaces;

import co.edu.uniquindio.hospitalproject.model.Horario;

import java.util.Collection;

public interface ICRUDHorarios {

    boolean addHorario(Horario horario);
    boolean updateHorario(Horario horario);
    boolean deleteHorario(Horario horario);
    Collection<Horario> listarHorarios();
}
