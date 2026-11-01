/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Pesanan {
    private Produk produk;
    private int jumlah;
    private double subtotal;

    public Pesanan(Produk produk, int jumlah) {
        this.produk = produk;
        this.jumlah = jumlah;
        this.subtotal = produk.getHarga() * jumlah;
    }

    public Produk getProduk() {
        return produk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }
    
    public void tampilkanInfo(){
        System.out.printf(" %-20s | Jumlah: %-3d | Subtotal: Rp%.2f%n", 
                            produk.getNamaProduk(), jumlah, subtotal);
    }
    
}
