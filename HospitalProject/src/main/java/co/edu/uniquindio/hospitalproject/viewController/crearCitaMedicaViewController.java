package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.model.*;
import co.edu.uniquindio.hospitalproject.model.enums.Especializacion;
import co.edu.uniquindio.hospitalproject.model.enums.EstadoCita;
import co.edu.uniquindio.hospitalproject.utils.SceneManager;
import co.edu.uniquindio.hospitalproject.utils.SessionActual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class crearCitaMedicaViewController {

    Hospital hospital;
    String idPaciente = SessionActual.idPacienteActivo;
    String nombrePaciente = SessionActual.nombrePacienteActivo;
    @FXML
    private MenuButton MenuEspecialidad;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSolicitarCita;

    @FXML
    private ComboBox<Integer> comboHoraCita;

    @FXML
    private ComboBox<Integer> comboMinutosCita;

    @FXML
    private DatePicker fechaCita;

    @FXML
    private MenuItem mBtnInmunologia;

    @FXML
    private MenuItem mBtnMedicinaGeneral;

    @FXML
    private MenuItem mBtnNeurologia;

    @FXML
    private MenuItem mBtnOdontologia;

    @FXML
    private MenuItem mBtnOftalmologia;

    @FXML
    private MenuItem mBtnOncologia;

    @FXML
    private MenuItem mBtnReumatologia;

    @FXML
    private Label lblIdPaciente;

    @FXML
    private Label lblNombrePaciente;

    @FXML
    void backToPaciente(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "paciente.fxml");
    }

    @FXML
    void optInmunologia(ActionEvent event) {
        MenuEspecialidad.setText(Especializacion.INMUNOLOGO.especializacion);
    }

    @FXML
    void optMedicinaGeneral(ActionEvent event) {
        MenuEspecialidad.setText(Especializacion.MEDICOGENERAL.especializacion);
    }

    @FXML
    void optNeurologia(ActionEvent event) {
        MenuEspecialidad.setText(Especializacion.NEUROLOGO.especializacion);
    }

    @FXML
    void optOdontologia(ActionEvent event) {
        MenuEspecialidad.setText(Especializacion.ODONTOLOGO.especializacion);
    }

    @FXML
    void optOftalmologia(ActionEvent event) {
        MenuEspecialidad.setText(Especializacion.OFTALMOLOGO.especializacion);
    }

    @FXML
    void optOncologia(ActionEvent event) {
        MenuEspecialidad.setText(Especializacion.ONCOLOGO.especializacion);
    }

    @FXML
    void optReumatologia(ActionEvent event) {
        MenuEspecialidad.setText(Especializacion.REUMATOLOGO.especializacion);
    }

    @FXML
    public void initialize() {
        cargarHoras();
        lblNombrePaciente.setText("Nombre del paciente: "+nombrePaciente);
        lblIdPaciente.setText("Cédula del paciente: "+idPaciente);
    }

    @FXML
    void solicitarCita(ActionEvent event) {
        hospital = Hospital.getInstancia();

        Paciente paciente = hospital.buscarPacienteID(idPaciente);

        if (paciente == null) {
            mostrarAlertaError("Paciente no encontrado. Intente nuevamente");
            return;
        }

        // Obtener fecha seleccionada
        LocalDate fecha = fechaCita.getValue();
        if (fecha == null) {
            mostrarAlertaError("Por favor, seleccione una fecha para la cita.");
            return;
        }

        // Obtener hora y minutos seleccionados
        Integer hora = comboHoraCita.getValue();
        Integer minutos = comboMinutosCita.getValue();
        if (hora == null || minutos == null) {
            mostrarAlertaError("Por favor, seleccione la hora y los minutos para la cita.");
            return;
        }

        LocalTime horaCita = LocalTime.of(hora, minutos);

        // Obtener especialización seleccionada
        String especialidadTexto = MenuEspecialidad.getText();
        if (especialidadTexto == null || especialidadTexto.isBlank()) {
            mostrarAlertaError("Por favor, seleccione una especialidad.");
            return;
        }

        // Convertir el texto a enum Especializacion
        Especializacion especializacion;
        try {
            especializacion = Especializacion.fromTexto(especialidadTexto);
        } catch (IllegalArgumentException e) {
            mostrarAlertaError("Especialidad inválida");
            return;
        }

        // Buscar un doctor disponible por especialización
        Doctor doctor = hospital.buscarDoctorPorEspecialidad(especializacion);
        if (doctor == null) {
            mostrarAlertaError("No hay doctores disponibles para la especialidad seleccionada.");
            return;
        }

        // Aquí puedes asignar una sala, por ejemplo:
        Sala salaDisponible = hospital.buscarSalaDisponible();

        if (salaDisponible != null) {
            salaDisponible.setEstadoSala(false); // ya está ocupada
        } else {
            mostrarAlertaInfo("No hay salas disponibles.");
            return;
        }

        String idCita = java.util.UUID.randomUUID().toString();

        CitaMedica cita = new CitaMedica(
                idCita,
                fecha,
                horaCita,
                paciente,
                doctor,
                salaDisponible,
                EstadoCita.PENDIENTE
        );

        // Guardar la cita en hospital o base de datos
        boolean guardado = hospital.crearCitaMedica(cita);

        if (guardado) {
            mostrarAlertaInfo("Cita médica creada con éxito.");
            // Opcional: regresar a otra ventana o limpiar campos
        } else {
            mostrarAlertaError("Error al crear la cita médica.");
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

    public void cargarHoras(){
        //Añadimos horas y minutos a las ComboBox
        for (int i = 0; i < 24; i++) {
            comboHoraCita.getItems().add(i);
        }
        comboMinutosCita.getItems().addAll(0, 15, 30, 45);
    }

}
