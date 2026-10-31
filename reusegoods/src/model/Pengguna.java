package model;
/**
 *
 * @author 22523222 - Novera Prestiana Putri
 *
 **/
public class Pengguna {
    String username;
    String password;
    String email;
    // String Alamat;
    // Barang barang;

    private boolean admin;
    boolean statusOnline;

    public Pengguna(String username,String password){
        this.username=username;
        this.password=password;
    }

    public Pengguna(String username, String email, String password){
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public Pengguna(String username,String password,boolean admin){
        this.username=username;
        this.password=password;
        this.admin=admin;
    }
    
    public void setAdmin(boolean Admin){
        this.admin=Admin;
    }

    // public void setID(String ID){
    //     this.ID=ID;
    // }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setStatusOnline(boolean status){
        this.statusOnline=status; 
    }
    // public void setAlamat(String alamat){
    //     this.Alamat=alamat;
    // }
    
    public String getUsername(){
        return this.username;
    }
    
    // public String getID(){
    //     return this.ID;
    // }
    public boolean getAdmin(){
        return this.admin;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    // public String getAlamat(){
    //     return this.Alamat;
    // }

    public boolean getStatusOnline(){
        return this.statusOnline;
    }

}
