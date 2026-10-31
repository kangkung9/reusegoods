package views;

/**
 *
 * @author 22523222 - Novera Prestiana Putri
 *
 **/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.scene.Node;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Pengguna;

public class loginController implements Initializable {

    //private MarketController marketController;
    XStream xstream= new XStream(new StaxDriver());
    //mengambil method dari FormPendaftaran controller untuk mendapatkan linked List yang disimpan ke data
    LinkedList<Pengguna> LoginDataContainer=bukaXml();

    LinkedList<Pengguna> bukaXml(){
         FileInputStream cobi = null;
      try {
           // nama file yang akan dibuka (termasuk folder jika perlu
          cobi = new FileInputStream("dataLogin.xml");
          // harus diingat objek apa yang dahulu disimpan di file 
          // program untuk membaca harus sinkron dengan program
          // yang dahulu digunakan untuk menyimpannya
          int isi;
          char charnya;
          String stringnya;
          // isi file dikembalikan menjadi string
          stringnya ="";
          while ((isi = cobi.read()) != -1) {
              charnya= (char) isi;
              stringnya = stringnya + charnya;
          }    
        // string isi file dikembalikan menjadi larik double
        return (LinkedList<Pengguna>) xstream.fromXML(stringnya);	  
      }
      catch (Exception e){
          System.err.println("test: "+e.getMessage());
      }
      finally{
        if(cobi != null){
            try{
                cobi.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }      
      } 
        return null;
    }
    
    @FXML
    private Button daftarbtn;

    @FXML
    private Button loginbtn;

    @FXML
    private TextField pw;

    @FXML
    private TextField un;

    @FXML
    private Label alert;

    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private void loginbtn(ActionEvent event) throws IOException{
        String username=un.getText();
        String password=pw.getText();
        
        boolean dataBenar=false;
        boolean isAdmin=false;
        
        // for (int i = 0; i < LoginDataContainer.size(); i++) {
        //     if(username.equals(LoginDataContainer.get(i).getUsername())&& (password.equals(LoginDataContainer.get(i).getPassword()))){
        //         LoginDataContainer.get(i).setStatusOnline(true);
        //         dataBenar=true;
        //     }else if((username.equals("admin")) && (password.equals("admin"))){
        //         dataBenar=true;
        //         isAdmin=true;
        //         LoginDataContainer.get(i).setAdmin(true);
        //         LoginDataContainer.get(i).setStatusOnline(true);
        //     }
          
        // }

        for (int i = 0; i <LoginDataContainer.size(); i++) {
            if ((username.equals(LoginDataContainer.get(i).getUsername()))&& (password.equals(LoginDataContainer.get(i).getPassword()))) {
                LoginDataContainer.get(i).setStatusOnline(true);
                dataBenar=true;
                break;
            }else if((username.equals("admin")) && (password.equals("admin"))){
                dataBenar=true;
                isAdmin=true;
                LoginDataContainer.get(i).setAdmin(true);
                LoginDataContainer.get(i).setStatusOnline(true);
            }
        }

        if(dataBenar && !isAdmin){
            root = FXMLLoader.load(loginController.class.getResource("FXMLDocument.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
            
            String xml = xstream.toXML(LoginDataContainer);
            
        FileOutputStream coba = null;
        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            coba = new FileOutputStream("dataLogin.xml");
 
            // mengubah karakter penyusun string xml sebagai 
            // bytes (berbentuk nomor2 kode ASCII
            byte[] bytes = xml.getBytes("UTF-8");
 
            // menyimpan file dari bytes
            coba.write(bytes);
        } 
        catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
        } 
        finally {
            if (coba != null) {
                try {
                    coba.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        }else if(dataBenar && isAdmin){
            root = FXMLLoader.load(loginController.class.getResource("FXMLDocument.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
            
            String xml = xstream.toXML(LoginDataContainer);
            
        FileOutputStream coba = null;
        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            coba = new FileOutputStream("dataLogin.xml");
 
            // mengubah karakter penyusun string xml sebagai 
            // bytes (berbentuk nomor2 kode ASCII
            byte[] bytes = xml.getBytes("UTF-8");
 
            // menyimpan file dari bytes
            coba.write(bytes);
        } 
        catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
        } 
        finally {
            if (coba != null) {
                try {
                    coba.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }
        else if(!dataBenar){
            alert.setText("ID/password Salah silahkan ulangi lagi");
        }
    }

    @FXML
    private void buttondaftar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registrasi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'initialize'");
        bukaXml();
    }
    
}
    
