package co.edu.uniquindio.hospitalproject.viewController;

import co.edu.uniquindio.hospitalproject.model.Doctor;
import co.edu.uniquindio.hospitalproject.model.Horario;
import co.edu.uniquindio.hospitalproject.model.Hospital;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class HorarioViewController {

    @FXML
    private TableView<Horario> tablaHorarios;

    @FXML
    private TableColumn<Horario, String> columnaDia;

    @FXML
    private TableColumn<Horario, String> columnaHoraInicio;

    @FXML
    private TableColumn<Horario, String> columnaHoraFin;

    @FXML
    private TextField txtDia;

    @FXML
    private TextField txtHoraInicio;

    @FXML
    private TextField txtHoraFin;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    private ObservableList<Horario> listaHorarios = FXCollections.observableArrayList();

    private Hospital hospital;
    private Doctor doctor;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        listaHorarios.setAll(doctor.getHorariosAtencion());
        tablaHorarios.setItems(listaHorarios);
    }

    @FXML
    public void initialize() {
        columnaDia.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDiaSemana()));
        columnaHoraInicio.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                String.format("%02d:%02d", data.getValue().getHoraInicio(), data.getValue().getMinutosInicio())));
        columnaHoraFin.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                String.format("%02d:%02d", data.getValue().getHoraFin(), data.getValue().getMinutosFin())));

        tablaHorarios.setItems(listaHorarios);
        tablaHorarios.setOnMouseClicked(this::seleccionarHorario);
    }

    private void seleccionarHorario(MouseEvent event) {
        Horario seleccionado = tablaHorarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtDia.setText(seleccionado.getDiaSemana());
            txtHoraInicio.setText(String.format("%02d:%02d", seleccionado.getHoraInicio(), seleccionado.getMinutosInicio()));
            txtHoraFin.setText(String.format("%02d:%02d", seleccionado.getHoraFin(), seleccionado.getMinutosFin()));
        }
    }

    @FXML
    private void agregarHorario() {
        String dia = txtDia.getText();
        int hInicio = Integer.parseInt(txtHoraInicio.getText().split(":")[0]);
        int mInicio = Integer.parseInt(txtHoraInicio.getText().split(":")[1]);
        int hFin = Integer.parseInt(txtHoraFin.getText().split(":")[0]);
        int mFin = Integer.parseInt(txtHoraFin.getText().split(":")[1]);

        Horario nuevo = new Horario(dia, hInicio, mInicio, hFin, mFin, doctor);
        doctor.agregarHorario(nuevo);
        listaHorarios.setAll(doctor.getHorariosAtencion());
    }

    @FXML
    private void editarHorario() {
        Horario seleccionado = tablaHorarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            String dia = txtDia.getText();
            int hInicio = Integer.parseInt(txtHoraInicio.getText().split(":")[0]);
            int mInicio = Integer.parseInt(txtHoraInicio.getText().split(":")[1]);
            int hFin = Integer.parseInt(txtHoraFin.getText().split(":")[0]);
            int mFin = Integer.parseInt(txtHoraFin.getText().split(":")[1]);

            Horario nuevo = new Horario(dia, hInicio, mInicio, hFin, mFin, doctor);
            doctor.editarHorario(seleccionado, nuevo);
            listaHorarios.setAll(doctor.getHorariosAtencion());
        }
    }

    @FXML
    private void eliminarHorario() {
        Horario seleccionado = tablaHorarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            doctor.eliminarHorario(seleccionado);
            listaHorarios.setAll(doctor.getHorariosAtencion());
        }
    }
}
