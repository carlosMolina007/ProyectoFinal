package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.controller.OcupacionHospitalController;
import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminViewController {

    Hospital hospital;

    @FXML
    private Button btnConsultarOcupacionHP;

    @FXML
    private Button btnGenerarReporteCita;

    @FXML
    private Button btnGestionDoctor;

    @FXML
    private Button btnGestionHorarios;

    @FXML
    private Button btnGestionPaciente;

    @FXML
    private Button btnGestionSala;

    @FXML
    private Button btnGestionUsuarios;

    @FXML
    private Button btnLogOutAdmin;

    @FXML
    private Label lblTextAdmin;

    void agregarNombreAdminTitulo(String nombre){
        lblTextAdmin.setText("Bienvenido administrador " + nombre);
    }

    @FXML
    void btnBackToLoginAdmin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "main.fxml");
    }

    @FXML
    void btnGestionarUsuarios(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "crudUsers.fxml");
    }

    @FXML
    void consultarOcupacionHP(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/hospitalproject/ocupacionHospital.fxml"));
            Parent root = loader.load();

            OcupacionHospitalViewController ocupacionHPVC = loader.getController();

            // Pasar datos necesarios, por ejemplo:
            OcupacionHospitalController ocupacionHPC = new OcupacionHospitalController(Hospital.getInstancia());
            int cantSalasTotal = ocupacionHPC.cantdSalasHospital();
            int cantSalasOcupadas = ocupacionHPC.cantSalasOcupadas();
            int porcentajeOcupado = ocupacionHPC.porcentajeOcupacionHospital();
            ocupacionHPVC.mostrarMensajeOcupacionHospital(cantSalasTotal, cantSalasOcupadas, porcentajeOcupado);

            Stage dialog = new Stage();
            ocupacionHPVC.setStage(dialog); // Si tienes método para setear stage
            dialog.setTitle("Ocupación Hospital");
            dialog.setScene(new Scene(root));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void generarReporte(ActionEvent event) {

    }

    @FXML
    void gestionarDoctores(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "crudDoctor.fxml");
    }

    @FXML
    void gestionarHorarios(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "crudHorariosAtencion.fxml");
    }

    @FXML
    void gestionarPacientes(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "crudPaciente.fxml");
    }

    @FXML
    void gestionarSalas(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "crudSalasHospital.fxml");
    }

}
