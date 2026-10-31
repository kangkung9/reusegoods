package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class barangController implements Initializable {

    @FXML
    private Button kembali;

    @FXML
    private Button tawar;

    private Scene scene;
    private Stage stage;
    @FXML
    private void kembali(ActionEvent event) throws IOException {
        //Platform.exit();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void tawar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tawarharga.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tawar Harga");
    }

    @FXML
    private void bayar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PesanBarang.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Bayar Pesanan");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
    
}
