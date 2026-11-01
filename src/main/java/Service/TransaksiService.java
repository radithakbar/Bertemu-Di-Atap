/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Transaksi;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Asus
 */
public class TransaksiService {
    public ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    
    private HashMap<String, Double> rekapPenjualanPerProduk = new HashMap<>();
    
    public void tambahTransaksi(Transaksi t){
        daftarTransaksi.add(t);
        rekapPenjualanPerProduk.merge(t.getNamaProduk(), t.getTotal(), Double::sum);
        
    }
    
    public void lihatTransaksi(){
        if(daftarTransaksi.isEmpty()){
            System.out.println("Belum ada transaksi.");
//            return;
        }
        System.out.println("\n---- Riwayat Transaksi ---");
        daftarTransaksi.forEach(Transaksi::tampilkanInfo);
        double grandTotal = daftarTransaksi.stream().mapToDouble(Transaksi::getTotal).sum();
        System.out.printf("Grand Total : Rp%.2f%n", grandTotal);
    }
    
    public void lihatRekapPenjualan(){
        if(rekapPenjualanPerProduk.isEmpty()){
            System.out.println("Belum ada data penjualan.");
            return;
        }
        System.out.println("\n--- Rekap Penjualan per Produk ---");
        for(String nama : rekapPenjualanPerProduk.keySet()){
            System.out.printf(" %-20s : Rp%.2f%n", nama, rekapPenjualanPerProduk.get(nama));
        }
    }
}
