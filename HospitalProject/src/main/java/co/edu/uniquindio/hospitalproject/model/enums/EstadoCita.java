package co.edu.uniquindio.hospitalproject.model.enums;

public enum EstadoCita {

    PENDIENTE("Pendiente"),
    CANCELADA("Cancelada");

    public final String estadoCita;

    EstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    @Override
    public String toString() {
        return estadoCita;
    }
}
