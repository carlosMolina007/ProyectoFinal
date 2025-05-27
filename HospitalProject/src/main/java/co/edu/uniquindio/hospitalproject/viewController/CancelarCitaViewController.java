package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.model.CitaMedica;
import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.model.Sala;
import co.edu.uniquindio.hospitalproject.model.enums.EstadoCita;
import co.edu.uniquindio.hospitalproject.utils.SceneManager;
import co.edu.uniquindio.hospitalproject.utils.SessionActual;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Collection;

public class CancelarCitaViewController {

    Hospital hospital;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancelarCita;

    @FXML
    private TableColumn<CitaMedica, String> tbcEstadoCita;

    @FXML
    private TableColumn<CitaMedica, String> tbcEspecialidad;

    @FXML
    private TableColumn<CitaMedica, String> tbcFecha;

    @FXML
    private TableColumn<CitaMedica, String> tbcHora;

    @FXML
    private TableView<CitaMedica> tblListCitas;

    @FXML
    public void initialize() {
        hospital = Hospital.getInstancia();
        String idPaciente = SessionActual.idPacienteActivo;
        Collection<CitaMedica> citasPaciente = hospital.obtenerCitasPorPaciente(idPaciente);
        // Lo que se muestra en la tabla por columna
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        tbcHora.setCellValueFactory(new PropertyValueFactory<>("horaCita"));
        tbcEspecialidad.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDoctorAsignado().getEspecializacion().toString()));
        tbcEstadoCita.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstadoCita().toString()));;
        // Cargar datos en la tabla
        tblListCitas.setItems(FXCollections.observableArrayList(citasPaciente));
    }

    @FXML
    void backToPaciente(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "paciente.fxml");
    }

    @FXML
    void cancelarCita(ActionEvent event) {
        CitaMedica citaSeleccionada = tblListCitas.getSelectionModel().getSelectedItem();

        if (citaSeleccionada == null) {
            mostrarAlertaError("Seleccione una cita para cancelar.");
            return;
        }
        // Cambiar el estado de la cita
        citaSeleccionada.setEstadoCita(EstadoCita.CANCELADA);
        // Liberar la sala
        Sala sala = citaSeleccionada.getSalaAsignada();
        if (sala != null) {
            sala.setEstadoSala(true);
        }

        boolean actualizado = hospital.actualizarCitaMedica(citaSeleccionada);

        if (actualizado) {
            mostrarAlertaInfo("Cita cancelada correctamente.");
            // Refrescar tabla para mostrar el cambio
            tblListCitas.refresh();
        } else {
            mostrarAlertaError("Error al cancelar la cita.");
        }
    }

    private void mostrarAlertaInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
