package views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Barang;

public class FXMLDocumentController implements Initializable {
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Label label;

    @FXML
    private Button kembali;

    @FXML
    private BorderPane mainPane;

    @FXML
    private void keHalaman1(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(registrasiController.class.getResource("barang.fxml")); 

        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("Pesan Barang");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }
    
    @FXML
    private void bukaAkun(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(registrasiController.class.getResource("registrasi.fxml")); 

        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("Daftar");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }

    @FXML
    private void maaf(ActionEvent event) throws IOException {
        label.setText("Maaf, sistem kami sedang dalam perbaikan");
    }

    @FXML
    private void inputbarang(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(registrasiController.class.getResource("Admin.fxml")); 

        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("Input Data Barang");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

//konsep fruits (tidak diimplementasikan)
    // private ArrayList<Barang> fruits = new ArrayList<>();
    // private Image image;
    // private MyListener myListener;
    
    // void setBarangDefault(){
    //     Barang fruit;   fruit = new Barang();
    //     fruit.setName("Sepatu Adidas Forum Low");
    //     fruit.setPrice("200.000");
    //     fruit.setImgSrc("adidasthrift.jpg");
    //     fruits.add(fruit);

    //     fruit = new Barang();
    //     fruit.setName("Jersey MU 2018");
    //     fruit.setPrice("100.000");
    //     fruit.setDeskripsi("jarang dipakai");
    //     fruits.add(fruit);

    //     fruit = new Barang();
    //     fruit.setName("Kipas Angin 2020");
    //     fruit.setDeskripsi("masih bisa jalan");
    //     fruit.setPrice("100.000");
    //     fruit.setImgSrc("kipasangin.jpeg");
    //     fruits.add(fruit);}