package co.edu.uniquindio.hospitalproject.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OcupacionHospitalViewController {

    private Stage stage;

    @FXML
    private Label lblCantSalas;

    @FXML
    private Label lblPorcentajeOcupacionHP;

    @FXML
    private Label lblSalasOcupadas;

    void mostrarMensajeOcupacionHospital(int cantSalas, int salasOcupadas, int porcentajeOcupado){
        lblCantSalas.setText("Cantidad total de salas: "+cantSalas);
        lblSalasOcupadas.setText("Salas ocupadas: "+salasOcupadas);
        lblPorcentajeOcupacionHP.setText("Porcentaje ocupado del hospital: "+porcentajeOcupado+"%");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void btnCerrar(ActionEvent event) {
        stage.close();
    }
}
