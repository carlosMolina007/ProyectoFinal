package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.model.Paciente;
import co.edu.uniquindio.hospitalproject.utils.SceneManager;
import co.edu.uniquindio.hospitalproject.utils.SessionActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PacienteViewController {

    Hospital hospital;

    @FXML
    private Button btnActualizarDatosP;

    @FXML
    private Button btnCancelarCitaM;

    @FXML
    private Button btnBackToLogin;

    @FXML
    private Button btnConsultarHistorial;

    @FXML
    private Button btnSolicitarCitaMedica;

    @FXML
    private Label lblNombrePaciente;

    void agregarNombrePaciente(String nombre){
        lblNombrePaciente.setText("Bienvenido paciente " + nombre);
    }

    @FXML
    void btnActualizarDatosPersonales(ActionEvent event) {
        hospital = Hospital.getInstancia();
        String idPaciente = SessionActual.idPacienteActivo;
        Paciente paciente = hospital.buscarPacienteID(idPaciente);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ActualizarDatosPacienteViewController actDatosPacienteVC = SceneManager.cambiarEscena(stage, "actualizarDatosPaciente.fxml");
        if (actDatosPacienteVC != null) {
            actDatosPacienteVC.setInformacionLabels(paciente.getNombre(),paciente.getApellido(), paciente.getEmail(),
                    paciente.getGenero(), paciente.getTelefono());
        }
    }

    @FXML
    void btnCancelarCitaMedica(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "CancelarCitaMedica.fxml");

    }

    @FXML
    void btnConsultarHistorialMedico(ActionEvent event) {

    }

    @FXML
    void btnSolicitarCitaM(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "crearCitaMedica.fxml");
    }

    @FXML
    void btnBackToLogin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "main.fxml");
    }
}
