package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.controller.CrudHorarioController;
import co.edu.uniquindio.hospitalproject.model.Doctor;
import co.edu.uniquindio.hospitalproject.model.Horario;
import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.model.Sala;
import co.edu.uniquindio.hospitalproject.utils.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CrudHorariosAtencion {

    Hospital hospital;
    private ObservableList<Horario> listaHorarios = FXCollections.observableArrayList();
    CrudHorarioController controllerHorario;
    private Horario selectedHorario;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAgregar;

    @FXML
    private ComboBox<String> comboDiasSemana;

    @FXML
    private ComboBox<Integer> comboMinutosInicio, comboMinutosFin, comboHoraInicio, comboHoraFin;

    @FXML
    private TableColumn<Horario, String> tbcDiaHorario, tbcHoraFin, tbcHoraInicio, tbcNombreDoctor;

    @FXML
    private TableView<Horario> tblHorariosAtencion;

    @FXML
    private TextField txtCedulaDoctor;

    @FXML
    private Button btnDeseleccionar;

    @FXML
    private Button btnEliminar;

    @FXML
    void backToAdmin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.cambiarEscena(stage, "admin.fxml");
    }

    @FXML
    void deseleccionar(ActionEvent event) {
        limpiarSeleccion();
    }

    @FXML
    void eliminar(ActionEvent event) {
        if (selectedHorario == null) {
            showAlert("Debe seleccionar un horario para eliminar.");
            return;
        }

        boolean eliminado = controllerHorario.deleteHorario(selectedHorario);
        if (eliminado) {
            listaHorarios.remove(selectedHorario);
            limpiarCampos();
            limpiarSeleccion();
        } else {
            showAlert("No se pudo eliminar el horario.");
        }
    }

    @FXML
    void actualizar(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }
        if (selectedHorario != null) {
            Horario horarioActualizado = buildHorario();
            if (horarioActualizado == null) return;

            boolean actualizado = controllerHorario.updateHorario(horarioActualizado);
            if (actualizado) {
                int index = listaHorarios.indexOf(selectedHorario);
                listaHorarios.set(index, horarioActualizado);
                tblHorariosAtencion.refresh();
                limpiarCampos();
                limpiarSeleccion();
            }
        }
    }

    @FXML
    void agregar(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }
        Horario newHorario = buildHorario();
        if (newHorario == null) return;
        if (controllerHorario.addHorario(newHorario)) {
            listaHorarios.add(newHorario);
            limpiarCampos();
        }
    }


    //Cargamos datos
    @FXML
    public void initialize() {
        hospital = Hospital.getInstancia();
        controllerHorario = new CrudHorarioController(hospital);
        initView();
    }

    void initView() {
        initDataBinding();
        cargarHoras();
        obtenerHorarios();
        tblHorariosAtencion.getItems().clear();
        tblHorariosAtencion.setItems(listaHorarios);
        listenerSelection();
    }

    private Horario buildHorario() {
        String dia = comboDiasSemana.getValue();
        int horaInicio = comboHoraInicio.getValue();
        int minutosInicio = comboMinutosInicio.getValue();
        int horaFin = comboHoraFin.getValue();
        int minutosFin = comboMinutosFin.getValue();

        String cedula = txtCedulaDoctor.getText();
        Doctor doctor = controllerHorario.buscarDoctor(cedula);

        if (doctor == null) {
            showAlert("No se encontró un doctor con la cédula ingresada.");
            return null;
        }

        return new Horario(dia, horaInicio, minutosInicio, horaFin, minutosFin, doctor);
    }

    private void initDataBinding() {
        tbcDiaHorario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDiaSemana()));
        tbcNombreDoctor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDoctorAsignado().getNombre()));

        tbcHoraInicio.setCellValueFactory(data -> {
            Horario horarioInicio = data.getValue();
            return new SimpleStringProperty(String.format("%02d:%02d", horarioInicio.getHoraInicio(), horarioInicio.getMinutosInicio()));
        });
        tbcHoraFin.setCellValueFactory(data -> {
            Horario horarioFin = data.getValue();
            return new SimpleStringProperty(String.format("%02d:%02d", horarioFin.getHoraFin(), horarioFin.getMinutosFin()));
        });
    }

    private void obtenerHorarios() {
        listaHorarios.addAll(controllerHorario.listHorarios());
    }

    private void listenerSelection() {
        tblHorariosAtencion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedHorario = newSelection;
            informacionHorario(newSelection);
        });
    }

    private void informacionHorario(Horario horario) {
        if (horario != null) {
            txtCedulaDoctor.setText(horario.getDoctorAsignado().getCedula());
            comboDiasSemana.setValue(horario.getDiaSemana());
            comboHoraInicio.setValue(horario.getHoraInicio());
            comboMinutosInicio.setValue(horario.getMinutosInicio());
            comboHoraFin.setValue(horario.getHoraFin());
            comboMinutosFin.setValue(horario.getMinutosFin());
        }
    }

    //Limpia la selección en la tabla (sombreado en pocas palabras)
    private void limpiarSeleccion() {
        tblHorariosAtencion.getSelectionModel().clearSelection();
        limpiarCampos();
    }

    //Limpia los campos mostrados
    private void limpiarCampos() {
        txtCedulaDoctor.clear();
        comboDiasSemana.getSelectionModel().clearSelection();
        comboHoraInicio.getSelectionModel().clearSelection();
        comboMinutosInicio.getSelectionModel().clearSelection();
        comboHoraFin.getSelectionModel().clearSelection();
        comboMinutosFin.getSelectionModel().clearSelection();
    }

    //Una alerta/advertencia que se le muestra al usuario al momento de no cumplir con validaciones
    private void showAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean validarCampos() {
        if (txtCedulaDoctor.getText().isEmpty() ||
                comboDiasSemana.getValue() == null ||
                comboHoraInicio.getValue() == null ||
                comboMinutosInicio.getValue() == null ||
                comboHoraFin.getValue() == null ||
                comboMinutosFin.getValue() == null) {
            showAlert("Por favor, complete todos los campos obligatorios.");
            return false;
        }
        return true;
    }

    public void cargarHoras(){
        //Llenamos el ComboBox de días
        comboDiasSemana.getItems().addAll("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");

        //Añadimos horas y minutos a las ComboBox
        for (int i = 0; i < 24; i++) {
            comboHoraInicio.getItems().add(i);
            comboHoraFin.getItems().add(i);
        }
        comboMinutosInicio.getItems().addAll(0, 15, 30, 45);
        comboMinutosFin.getItems().addAll(0, 15, 30, 45);
    }

}
