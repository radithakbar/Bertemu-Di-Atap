/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Produk {
  private int idproduk;
  private String namaProduk;
  private int stok;
  private double harga;
  private Kategori kategori;

    public Produk(int idproduk, String namaProduk,  int stok, double harga, Kategori kategori) {
        this.idproduk = idproduk;
        this.namaProduk = namaProduk;
        this.stok = stok;
        this.harga = harga;
        this.kategori = kategori;
    }

    public int getIdproduk() {
        return idproduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public int getStok() {
        return stok;
    }

    public double getHarga() {
        return harga;
    }
    
    public Kategori getKategori(){
        return kategori;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public void setKategori(Kategori kategori){
        this.kategori = kategori;
    }
    
    
    public void kurangiStok(int jumlah){
        if (jumlah > stok) throw new IllegalArgumentException("Stok tidak cukup!");
        this.stok -= jumlah;
    }
    
    public void tampilkanInfo(){
        String namaKategori = (kategori != null) ? kategori.getNamaKategori() : "-"; 
        System.out.printf(" ID: %-4d | %-20s | Kategori: %-10s | Stok: %-4d | Harga: Rp%.2f%n",
                            idproduk, namaProduk, namaKategori , stok, harga);
    }
  
  
}
