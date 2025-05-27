package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.model.Paciente;
import co.edu.uniquindio.hospitalproject.model.enums.Genero;
import co.edu.uniquindio.hospitalproject.utils.SceneManager;
import co.edu.uniquindio.hospitalproject.utils.SessionActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActualizarDatosPacienteViewController {

    @FXML
    private Button btnBack;

    @FXML
    private MenuItem btnFemenino;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private MenuItem btnMasculino;

    @FXML
    private MenuItem btnOtro;

    @FXML
    private Label lblApellidoMostrar;

    @FXML
    private Label lblEmailMostrar;

    @FXML
    private Label lblGeneroMostrar;

    @FXML
    private Label lblNombreMostrar;

    @FXML
    private Label lblNumeroMostrar;

    @FXML
    private MenuButton menuGenero;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreoElect;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumeroTel;

    @FXML
    private VBox vBoxDatos;

    public void setInformacionLabels(String nombre, String apellido, String email, Genero genero, String numeroCel) {
        lblNombreMostrar.setText("Nombre: "+nombre);
        lblApellidoMostrar.setText("Apellido: "+apellido);
        lblEmailMostrar.setText("Correo: "+email);
        lblGeneroMostrar.setText("Género: "+genero.genero);
        lblNumeroMostrar.setText("Número de teléfono: "+numeroCel);
    }

    @FXML
    void backToPaciente(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "paciente.fxml");
    }

    @FXML
    void guardarCambios(ActionEvent event) {
        String idPaciente = SessionActual.idPacienteActivo;
        Hospital hospital = Hospital.getInstancia();
        Paciente paciente = hospital.buscarPacienteID(idPaciente);

        if (paciente != null) {
            String nuevoNombre = txtNombre.getText().trim();
            String nuevoApellido = txtApellido.getText().trim();
            String nuevoCorreo = txtCorreoElect.getText().trim();
            String nuevoTelefono = txtNumeroTel.getText().trim();
            String nuevoGenero = menuGenero.getText().trim();

            paciente.setNombre(nuevoNombre);
            paciente.setApellido(nuevoApellido);
            paciente.setEmail(nuevoCorreo);
            paciente.setTelefono(nuevoTelefono);
            paciente.setGenero(Genero.fromTexto(menuGenero.getText()));

            mostrarAlertaInfo("Datos actualizados correctamente.");
        } else {
            mostrarAlertaError("Paciente no encontrado.");
        }
    }

    @FXML
    void opcionFemenino(ActionEvent event) {
        menuGenero.setText("Femenino");
    }

    @FXML
    void opcionMasculino(ActionEvent event) {
        menuGenero.setText("Masculino");
    }

    @FXML
    void opcionOtro(ActionEvent event) {
        menuGenero.setText("Otro");
    }

    private void mostrarAlertaInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
