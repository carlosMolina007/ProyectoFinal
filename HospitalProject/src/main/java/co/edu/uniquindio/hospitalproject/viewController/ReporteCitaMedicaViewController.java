package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.model.CitaMedica;
import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.utils.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ReporteCitaMedicaViewController {

    Hospital hospital;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnInformacionDetallada;

    @FXML
    private TableColumn<CitaMedica, String> tbcHoraCita, tbcFecha, tbcEstadoCita, tbcEspecialidad;

    @FXML
    private TableView<CitaMedica> tblCitasMedicas;

    @FXML
    public void initialize() {
        Hospital hospital = Hospital.getInstancia();
        Collection<CitaMedica> citas = new ArrayList<>(hospital.listarCitasMedicas());

        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        tbcHoraCita.setCellValueFactory(new PropertyValueFactory<>("horaCita"));
        tbcEstadoCita.setCellValueFactory(new PropertyValueFactory<>("estadoCita"));
        tbcEspecialidad.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDoctorAsignado().getEspecializacion().toString())
        );
        tblCitasMedicas.setItems(FXCollections.observableArrayList(citas));
    }

    @FXML
    void backToAdmin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "admin.fxml");
    }

    @FXML
    void mostrarInformacionDetallada(ActionEvent event) {
        CitaMedica citaSeleccionada = tblCitasMedicas.getSelectionModel().getSelectedItem();

        if (citaSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/hospitalproject/informacionDetalladaCita.fxml"));
                Parent root = loader.load();

                InformacionCitaDetalladaViewController controller = loader.getController();
                controller.cargarDatosCita(citaSeleccionada);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Información Detallada de la Cita");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Opcional: mostrar una alerta si no se ha seleccionado nada
            mostrarAlertaError("No se seleccionó ninguna cita");
        }
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
