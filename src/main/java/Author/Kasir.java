/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Author;

import Service.PesananService;
import Service.ProdukService;
import Service.TransaksiService;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Kasir extends User{

    public Kasir(String username, String password) {
        super(username, password);
    }
    public void login(){
        
    }
    
    public void logout(){
    
    }
    
    public void lihatProduk(ProdukService produkService){
   
    }
    
    public void tambahPesanan(Scanner in, ProdukService produkService, PesananService pesananService){
    
    }
    
    public void chekout(PesananService pesananService, ProdukService produkService, TransaksiService transaksiService){
    
    }
    
    public void buatTransaksi(){
    
    }
    
    public void lihatTransaksi(){
    
    }
    
}
