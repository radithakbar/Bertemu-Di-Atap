/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tagihan;

import Model.Pesanan;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Struk {
  private int nomorStruk;
  private double totalBayar;

    public Struk(int nomorStruk, double totalBayar) {
        this.nomorStruk = nomorStruk;
        this.totalBayar = totalBayar;
    }
  
    public void tampilkanStruk(List<Pesanan> pesananList, double uangBayar){
        System.out.println("\n========================================");
        System.out.println("            STRUK PEMBAYARAN            ");
        System.out.println("========================================");
        System.out.println("Nomor Struk : #" + nomorStruk);
        System.out.println("----------------------------------------");
        System.out.println("Detail Pesanan:");
        for (Pesanan p : pesananList) {
            p.tampilkanInfo();
        } 
        System.out.println("----------------------------------------");
        System.out.printf("Total Bayar : Rp%.2f%n", totalBayar);
        System.out.printf("Uang Bayar  : Rp%.2f%n", uangBayar);
        System.out.printf("Kembalian   : Rp%.2f%n", uangBayar - totalBayar);
        System.out.println("========================================");
        System.out.println("        Terima kasih sudah belanja!     ");
        System.out.println("========================================\n");
    }
}
