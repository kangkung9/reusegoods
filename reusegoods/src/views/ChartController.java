package views;

/**
 *
 * @author 22523222 - Novera Prestiana Putri
 *
 **/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.REUSEGOODS;
import model.Barang;

public class ChartController implements Initializable {
    
    Series<String, Integer> StokBarang = new XYChart.Series<>();

    private REUSEGOODS main;

    public void setMain(REUSEGOODS main) {
        this.main = main;
    }

    @FXML
    private BarChart<String, Integer> BarChart;

    @FXML
    private Label Label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/Stock.xml");

        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("Barang", Barang.class);

        List<Barang> stokBarangList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            stokBarangList = (List<Barang>) xstream.fromXML(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Barang stokBarang : stokBarangList) {
            String barang = stokBarang.getName();
            int harga = Integer.parseInt(stokBarang.getPrice());
            StokBarang.getData().add(new XYChart.Data<>(barang, harga));
        }
        BarChart.getData().add(StokBarang);
    }

    private Scene scene;
    private Stage stage;
    private Parent root;
    
    @FXML
    private void kembali(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Input Data Barang");
    }
}