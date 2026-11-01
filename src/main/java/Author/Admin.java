/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Author;

import Model.Kategori;
import Service.ProdukService;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class  Admin extends User{
    private boolean loggedIn = false;
    
    public Admin(String username, String password) {
        super(username, password);
    }
    
    public boolean login(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            loggedIn = true;
            System.out.println("Admin '" + username + "' berhasil login.");
            return true;
        }
        return false;
    }
    
    public void logout(){
        loggedIn = false;
        System.out.println("Admin '" + username + "' telah logout.");
    }
    
    public void tambahProduk(Scanner in, ProdukService produkService){
        System.out.println("\n [Admin] Tambah Produk");
        produkService.tambahProduk(in);
        
        Kategori kategori = new Kategori(0, password);
        
    }
    
    public void lihatProduk(ProdukService produkService){
        produkService.lihatProduk();
    }
    
    public void ubahProduk(Scanner in, ProdukService produkService){
        System.out.println("\n[Admin] ubah Produk");
        produkService.ubahProduk(in);
    }
    
    public void hapusProduk(Scanner in, ProdukService produkService){
        System.out.println("\n[Admin] Hapus Produk");
        produkService.hapusProduk(in);
    }
}
