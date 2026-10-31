package views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class pesanbarang {
    @FXML
    private Label label;

    @FXML
    private TextField tfAlamat;

    private Scene scene;
    private Stage stage;
    private Parent root;
    @FXML
    private void keHalaman3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("barang.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void maaf(ActionEvent event) throws IOException {
        label.setText("Maaf, sistem kami sedang dalam perbaikan");
    }
}
