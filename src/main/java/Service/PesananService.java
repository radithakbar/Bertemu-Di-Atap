/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Pesanan;
import Model.Produk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class PesananService {

    public ArrayList<Pesanan> daftarPesanan = new ArrayList<>();

    private HashMap<Integer, Integer> rekapJumlahPerProduk = new HashMap<>();

    public void tambahPesanan(Scanner in, ProdukService produkService) {
        produkService.lihatProduk();
        System.out.print("Masukkan ID produk: ");
        int id;
    try {
        id = Integer.parseInt(in.nextLine());
    } catch (NumberFormatException e) {
        System.out.println(" ID produk harus berupa angka.");
        return;
    }

    Produk p = produkService.cariProduk(id);
    if (p == null) { System.out.println("Produk tidak ditemukan."); return; }

    System.out.print("Jumlah: ");
    int jumlah;
    try {
        jumlah = Integer.parseInt(in.nextLine());
    } catch (NumberFormatException e) {
        System.out.println(" Jumlah harus berupa angka.");
        return;
    }

    if (jumlah > p.getStok()) {
        System.out.println("Stok tidak mencukupi! Stok tersedia: " + p.getStok());
        return;
    }

    daftarPesanan.add(new Pesanan(p, jumlah));
    rekapJumlahPerProduk.merge(id, jumlah, Integer::sum);

    System.out.println(" Pesanan ditambahkan: " + p.getNamaProduk() + "  x " + jumlah);
    }
    
    public double hitungTotal(){
        return daftarPesanan.stream().mapToDouble(Pesanan::getSubtotal).sum();
    }
    
    public void lihatPesanan(){
        if(daftarPesanan.isEmpty()){
            System.out.println("Keranjang kosong.");
            return;
        }
        System.out.println("\n--- Keranjang Pesanan ---");
        daftarPesanan.forEach(Pesanan::tampilkanInfo);
        System.out.printf("Total : Rp%.2f%n", hitungTotal());
    }
    
    public void lihatPesananBernomor() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Keranjang kosong.");
            return;
        }
        System.out.println("\n--- Keranjang Pesanan ---");
        for (int i = 0; i < daftarPesanan.size(); i++) {
            Pesanan p = daftarPesanan.get(i);
            System.out.printf("%d. %-20s | Jumlah: %-3d | Subtotal: Rp%.2f%n",
                    i + 1, p.getProduk().getNamaProduk(), p.getJumlah(), p.getSubtotal());
        }
        System.out.printf("Total : Rp%.2f%n", hitungTotal());
    }
    
    public boolean hapusPesanan(int nomorUrut) {
        int index = nomorUrut - 1;
        if (index < 0 || index >= daftarPesanan.size()) {
            return false;
        }

        Pesanan p = daftarPesanan.get(index);
        rekapJumlahPerProduk.merge(p.getProduk().getIdproduk(), -p.getJumlah(), Integer::sum);

        daftarPesanan.remove(index);
        return true;
    }
    
    public String ubahJumlahPesanan(int nomorUrut, int jumlahBaru) {
        int index = nomorUrut - 1;
        if (index < 0 || index >= daftarPesanan.size()) {
            return "Nomor pesanan tidak valid.";
        }
        if (jumlahBaru <= 0) {
            return "Jumlah harus lebih dari 0.";
        }

        Pesanan pesananLama = daftarPesanan.get(index);
        Produk produk = pesananLama.getProduk();

        if (jumlahBaru > produk.getStok()) {
            return "Stok tidak mencukupi! Stok tersedia: " + produk.getStok();
        }

        rekapJumlahPerProduk.merge(produk.getIdproduk(), jumlahBaru - pesananLama.getJumlah(), Integer::sum);
        daftarPesanan.set(index, new Pesanan(produk, jumlahBaru));

        return " Jumlah berhasil diubah menjadi " + jumlahBaru + ".";
    }
    
    public int getJumlahProdukDiKeranjang(int idProduk){
        return rekapJumlahPerProduk.getOrDefault(idProduk, 0);
    }
    
    public void kosongkan(){
        daftarPesanan.clear();
        rekapJumlahPerProduk.clear();
    }
    
    public String tambahPesananGUI(int idProduk, int jumlah, ProdukService produkService) {
        Produk p = produkService.cariProduk(idProduk);
        if (p == null) {
            return "Produk tidak ditemukan.";
        }
        if (jumlah <= 0) {
            return "Jumlah harus lebih dari 0.";
        }
        if (jumlah > p.getStok()) {
            return "Stok tidak mencukupi! Stok tersedia: " + p.getStok();
        }

        daftarPesanan.add(new Pesanan(p, jumlah));
        rekapJumlahPerProduk.merge(idProduk, jumlah, Integer::sum);

        return " " + p.getNamaProduk() + " x" + jumlah + " ditambahkan ke keranjang.";
    }
}
