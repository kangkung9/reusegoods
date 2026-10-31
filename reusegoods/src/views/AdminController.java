package views;

/**
 *
 * @author 22523222 - Novera Prestiana Putri
 *
 **/

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.collections.ObservableList;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.REUSEGOODS;
import model.Barang;

public class AdminController implements Initializable {

    //TableView
    @FXML
    private AnchorPane anchorId;
    @FXML
    private TableColumn<Barang, String> tcNama;
    @FXML
    private TableColumn<Barang, String> tcHarga;
    @FXML
    private TableColumn<Barang, String> tcDeskripsi;
    @FXML
    private TableView<Barang> tableView;
    @FXML
    private Label label;

    //TextField
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfHarga;
    @FXML
    private TextArea tfDeskripsi;

    private ObservableList<Barang> data = FXCollections.observableArrayList();
    private Array barang = new Array();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String A = tfNama.getText();
        String B = tfHarga.getText();
        String C = tfDeskripsi.getText();
        barang.addStok(A, B, C);

        Barang newBarang = new Barang(A, B, C);
        data.add(newBarang);
        Array.simpan(barang.getStokBarangList());

        tcNama.setText("");
        tcHarga.setText("");
        tcDeskripsi.setText("");
    }

    @FXML
    private void ButtonHapusData(ActionEvent event) {
       int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
       if (selectedIndex >= 0) {
        Barang selectedBarang = tableView.getItems().get(selectedIndex);
        tableView.getItems().remove(selectedIndex);
        barang.getStokBarangList().remove(selectedBarang);
        Array.simpan(barang.getStokBarangList());
       }  
    }

    @FXML 
    private void Buttonedit(ActionEvent event) {
        
        Barang selectedBarang = tableView.getSelectionModel().getSelectedItem();

        if (selectedBarang != null){

            String name = selectedBarang.getName();
            String price = selectedBarang.getPrice();
            String desc = selectedBarang.getDeskripsi();

            
            String nama = tfNama.getText();
            String harga = tfHarga.getText();
            String deskripsi = tfDeskripsi.getText();

            if(nama.isEmpty()){
                selectedBarang.setName(name);
            } else {
                selectedBarang.setName(nama);
            }

            if(harga.isEmpty()){
                selectedBarang.setPrice(price);
            }else {
                selectedBarang.setPrice(harga);
            }
            if(deskripsi.isEmpty()){
                selectedBarang.setDeskripsi(desc);
            }else {
                selectedBarang.setDeskripsi(deskripsi);
            }
          
            tableView.refresh();
            Array.simpan(barang.getStokBarangList());
            tfNama.clear();
            tfHarga.clear();
            tfDeskripsi.clear();
        }
    }

    private Scene scene;
    private Stage stage;
    @FXML
    private void kembali(ActionEvent event) throws IOException {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void maaf(ActionEvent event) throws IOException {
        label.setText("Maaf, sistem kami sedang dalam perbaikan");
    }

    @FXML
    private void chart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Chart");
    }
    
    public void tampilkanBarang(){
        for (int i = 0; i < barang.getStokBarangList().size(); i++) {
            data.add(barang.getStok(i));
        }
    } 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tampilkanBarang();
        tcNama.setCellValueFactory(new PropertyValueFactory<Barang, String>("name"));
        tcHarga.setCellValueFactory(new PropertyValueFactory<Barang, String>("price"));
        tcDeskripsi.setCellValueFactory(new PropertyValueFactory<Barang, String>("deskripsi"));
        tableView.setItems(data);
    }
    
}

    // private REUSEGOODS main;

    // public void setMainApp(REUSEGOODS main) {
    //     this.main = main;
    // }