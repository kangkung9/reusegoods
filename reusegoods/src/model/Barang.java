package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("StokBarang")
public class Barang {
    private String name;
    private String price;
    private String deskripsi;
    private String imgSrc="";

    public Barang(String name,String price,String Deskripsi){
        this.name=name;
        this.price=price;
        this.deskripsi=Deskripsi;
        this.imgSrc=imgSrc;
    }

    public Barang(){
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDeskripsi(String deskripsi){
        this.deskripsi=deskripsi;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDeskripsi(){
        return deskripsi;
    }

    public void setImgSrc(String string) {
        this.imgSrc = imgSrc;
    }
    public String getStok() {
        return null;
    }

}
