/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Produk;
import Model.Kategori;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class ProdukService {
    public static ArrayList<Produk> daftarProduk = new ArrayList<>();
    
    private static HashMap<Integer, Produk> mapProduk = new HashMap<>();
    private static ArrayList<Kategori> daftarKategori = new ArrayList<>();
    private int nextId = 1 ;
    private static boolean sudahDiinisialisasi = false;
    
    public ProdukService(){
        if (sudahDiinisialisasi) return;
        sudahDiinisialisasi = true;
        
        daftarKategori.add(new Kategori(1, "1 Makanan"));
        daftarKategori.add(new Kategori(2, "2 Minuman"));
        daftarKategori.add(new Kategori(3, "3 Cemilan"));
        
        tambahMenu(new Produk(nextId++, "Indomie Goreng", 100 , 20000, cariKategori(1)));
        tambahMenu(new Produk(nextId++, "Nasi Goreng Atap", 50 , 28000, cariKategori(1)));
        tambahMenu(new Produk(nextId++, "Rice Ball Beef", 55 , 28000, cariKategori(1)));
        tambahMenu(new Produk(nextId++, "Pisang Goreng", 30 , 25000, cariKategori(3)));
        tambahMenu(new Produk(nextId++, "ES Kopi Atap", 70  , 24000, cariKategori(2)));
    }
    
    public Kategori cariKategori(int idKategori){
        for (Kategori k : daftarKategori) {
            if (k.getIdKategori() == idKategori) return k;
        }
        return null;
    }
    
    public void tampilkanKategori(){
        System.out.println("\n--- Daftar Kategori ---");
        for (Kategori k : daftarKategori) System.out.println(k);
    }
    
    private void tambahKeDuaStruktur(Produk p) {
        daftarProduk.add(p);
        mapProduk.put(p.getIdproduk(), p);
    }
    
    private void tambahMenu(Produk p){
        daftarProduk.add(p);
        mapProduk.put(p.getIdproduk(), p);
    }
    
    public void tambahProduk(Scanner in){
        System.out.print("Nama Produk    : ");
        String nama = in.nextLine();
        System.out.print("Stok           : ");
        int stok = Integer.parseInt(in.nextLine());
        System.out.print("Harga          : ");
        double harga = Double.parseDouble(in.nextLine());
        
        tampilkanKategori();
        System.out.print("Pilih ID Kategori : ");
        int idKategori = Integer.parseInt(in.nextLine());
        Kategori kategori = cariKategori(idKategori);
        
        Produk produkBaru = new Produk(nextId++, nama, stok, harga, kategori);
        tambahMenu(produkBaru);
        System.out.println("Produk Berhasil Ditambahkan.");
    }
    
    public void lihatProduk(){
        if(daftarProduk.isEmpty()){
            System.out.println("Belum ada produk.");
            return;
        }
        System.out.println("\n--- Daftar Produk ---");
        for (Produk p : daftarProduk) p.tampilkanInfo();
    }
    
    public void ubahProduk(Scanner in){
        lihatProduk();
        System.out.println("Masukkan ID produk yang akan diubah: ");
        int id = Integer.parseInt(in.nextLine());
        
        Produk p = mapProduk.get(id);
        if (p == null){
            System.out.println("Produk tidak ditemukan.");
            return;
        }
        
        System.out.print("Nama baru (kosongkan untuk skip): ");
        String nama = in.nextLine();
        if(!nama.isBlank()) p.setNamaProduk(nama);
        
        System.out.print("Stok baru (-1 untuk skip): ");
        int stok = Integer.parseInt(in.nextLine());
        if(stok >= 0)p.setStok(stok);
        
        tampilkanKategori();
        System.out.print("ID Kategori baru (-1 untuk skip): ");
        int idKategori = Integer.parseInt(in.nextLine());
        if(idKategori >= 0){
            Kategori kategoriBaru = cariKategori(idKategori);
            if(kategoriBaru != null) p.setKategori(kategoriBaru);
        }
        
        System.out.println("Produk berhasil diperbarui");
    }
    
    public void hapusProduk(Scanner in){
        lihatProduk();
        System.out.print("Masukan ID produk yang akan dihapus: ");
        int id = Integer.parseInt(in.nextLine());
        
        Produk p = mapProduk.get(id);
        if(p == null){
            System.out.println("Produk tidak ditemukan.");
            return;
        }
        daftarProduk.remove(p);
        mapProduk.remove(id);
        System.out.println("Produk berhasil dihapus.");
    }
    public Produk cariProduk(int id){
        return mapProduk.get(id);
    }
    
    public void tambahProdukGUI(String nama, int stok, double harga ) {
        Produk produkBaru = new Produk(nextId++, nama, stok, harga, null);
        tambahKeDuaStruktur(produkBaru);
    }

    public boolean ubahProdukGUI(int id, String nama, int stok, double harga) {
        Produk p = mapProduk.get(id);
        if (p == null) return false;

        if (!nama.isBlank()) p.setNamaProduk(nama);
        if (stok >= 0) p.setStok(stok);
        if (harga >= 0) p.setHarga(harga);
        return true;
    }

    public boolean hapusProdukGUI(int id) {
        Produk p = mapProduk.get(id);
        if (p == null) return false;

        daftarProduk.remove(p);
        mapProduk.remove(id);
        return true;
    }
}
