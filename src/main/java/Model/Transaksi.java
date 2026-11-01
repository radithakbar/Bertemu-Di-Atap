/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Transaksi {
   private String namaProduk;
   private int jumlah;
   private double total;

    public Transaksi(String namaProduk, int jumlah, double total) {
        this.namaProduk = namaProduk;
        this.jumlah = jumlah;
        this.total = total;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getTotal() {
        return total;
    }
   
    public void tampilkanInfo(){
        System.out.printf("  %-20s | Jumlah: %-3d | Total: Rp%.2f%n",
                            namaProduk, jumlah, total);
    }
}
