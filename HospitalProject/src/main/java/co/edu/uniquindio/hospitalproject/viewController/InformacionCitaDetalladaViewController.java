package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.model.CitaMedica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InformacionCitaDetalladaViewController {

    @FXML
    private Label lblDoctorNombre;

    @FXML
    private Label lblEstadoCita;

    @FXML
    private Label lblFechaCita;

    @FXML
    private Label lblHoraCita;

    @FXML
    private Label lblPacienteNombre;

    @FXML
    private Label lblSalaAsignada;

    @FXML
    private Button btnCerrar;

    public void cargarDatosCita(CitaMedica cita) {
        lblFechaCita.setText("Fecha de la cita: "+cita.getFechaCita().toString());
        lblHoraCita.setText("Hora de la cita: "+cita.getHoraCita().toString());
        lblEstadoCita.setText("Estado de la cita: "+cita.getEstadoCita().toString());
        lblPacienteNombre.setText("Paciente: "+cita.getPacienteAsignado().getNombre() + " " + cita.getPacienteAsignado().getApellido());
        lblDoctorNombre.setText("Doctor: "+cita.getDoctorAsignado().getNombre() + " " + cita.getDoctorAsignado().getApellido());
        lblSalaAsignada.setText("Nombre de la sala: "+cita.getSalaAsignada().getNombreSala());
    }

    @FXML
    void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

}
