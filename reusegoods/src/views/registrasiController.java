package views;

/**
 *
 * @author 22523222 - Novera Prestiana Putri
 *
 **/

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Pengguna;

public class registrasiController implements Initializable {
    private Scene scene;
    private Stage stage;
    private Parent root;
    @FXML
    private void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    LinkedList<Pengguna> LoginDataContainer=new LinkedList<>();//inisialisasi LinkedList untuk  menyimpan Pengguna yang dibutuhkan saat login
    //setiap node memiliki masing-masing Pengguna

    LinkedList<Pengguna>  getLoginData (){
       return LoginDataContainer;
    }

    Pengguna getPenggunaOnline(){
        for (int i = 0; i <LoginDataContainer.size(); i++) {
            if (LoginDataContainer.get(i).getStatusOnline()) {
                return LoginDataContainer.get(i);
            }
        }
        return null;
    }
    
    public void show(){
        daftarAkun.setText(LoginDataContainer.get(0).getUsername());
    }

    XStream xstream= new XStream(new StaxDriver());

    @FXML
    private TextField un;

    @FXML
    private TextField email;

    // @FXML
    // private TextField email;

    @FXML
    private Label usernameAlert;

    @FXML
    private TextField pw;

    @FXML
    private Button daftarAkun;

    @FXML
    private Button loginbtn;
    
    //method untuk buka xml
    void bukaXml(){
        FileInputStream cobi = null;
      try {
           // nama file yang akan dibuka (termasuk folder jika perlu
          cobi = new FileInputStream("src/dataLogin.xml");
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
        LoginDataContainer =  (LinkedList<Pengguna>) xstream.fromXML(stringnya);	  
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
}

    //form pendaftaran
    @FXML
    private void buttonDaftar(ActionEvent event) throws IOException{
        
        //mengambil text dari masing-masing textfield untuk dimasukkan dalam variabel
        String username=un.getText();
        String emai=email.getText();
        String password=pw.getText();
        
        boolean IDganda = false;//variabel untuk cek apakah ada ID yang Ganda
        
        //melakukan pencarian setiap indeks Linkedlist dan mendapatkan ID pada setiap indeks
        for (int i = 0; i < LoginDataContainer.size(); i++) {
            System.out.println(LoginDataContainer.get(i));
            if(username.equals(LoginDataContainer.get(i).getUsername())){//jika ID yang dimasukkan ditemukan kesamaan nilai maka ID dianggap ganda 
                IDganda=true;// IDganda set True (terdapat ID yang sama)
            }
        }
        
        if(IDganda){
            usernameAlert.setText("ID/Username telah digunakan");//jika terdapat ID yang sama maka akan muncul peringatan berikut
            
        }else if(!IDganda){
            LoginDataContainer.add(new Pengguna(username, emai, password));// jika tidak terdapat iD yang sama maka data dapat dimasukkan ke dalam Pengguna container
            
        // larik double diubah menjadi string dengan format XML
        String xml = xstream.toXML(LoginDataContainer);
 
        FileOutputStream coba = null;
        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            coba = new FileOutputStream("src/dataLogin.xml");
 
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

        root = FXMLLoader.load(registrasiController.class.getResource("registrasi.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
    }
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
     void clearLoginData(){
         LoginDataContainer.clear();
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
     
     void adAdmin(){
        Pengguna admin=new Pengguna("admin","admin",true);
        LoginDataContainer.add(admin);
        
        //simpang loginData ke xml
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        bukaXml();
    }
    
}

// @FXML
    // private void buttonDaftar(ActionEvent event) throws IOException{
    //     String A =username.getText();
    //     String B = email.getText();
    //     String B=password.getText();
    //     p.add(new Pembeli(A,B));
    // }