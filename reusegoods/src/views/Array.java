package views;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import model.Barang;

public class Array {
    ArrayList<Barang> array = new ArrayList<>();

     public static void simpan(ArrayList<Barang> arrayy){
        XStream xStream = new XStream(new StaxDriver());
        String sxml = xStream.toXML(arrayy);
        FileOutputStream fileOutput;
        try {
            byte[] bytes = sxml.getBytes("UTF-8");
            fileOutput = new FileOutputStream("src/Stock.xml");
            fileOutput.write(bytes);
            fileOutput.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void addStok(String nama, String harga, String deskripsi){
        array.add(new Barang(nama, harga, deskripsi));
        simpan(array);
    }
    
    public Barang getStok(int index){
        return array.get(index);
    }

    public ArrayList<Barang> getStokBarangList(){
        return array;
    }

    public Array(){
        array = baca(array);
    }

    public static ArrayList<Barang> baca(ArrayList<Barang> arrayy) {
        XStream xStream = new XStream(new StaxDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        FileInputStream fileInput;
        try {
            fileInput = new FileInputStream("src/Stock.xml");

            String sxml = "";
            int isi;
            char c;
            while ((isi = fileInput.read()) != -1) {
                c = (char) isi;
                sxml +=c;
            }

            arrayy = (ArrayList<Barang>) xStream.fromXML(sxml);
            fileInput.close();
            return arrayy;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return arrayy;
        }
    }
}
