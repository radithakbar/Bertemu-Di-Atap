/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Author;

import Model.Pesanan;
import Model.Transaksi;
import Service.PesananService;
import Service.ProdukService;
import Service.TransaksiService;
import Tagihan.Pembayaran;
import Tagihan.Struk;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Kasir extends User{
    private boolean loggedIn = false;
    private static int nomorStruk = 1;
   
    public Kasir(String username, String password) {
        super(username, password);
    }
    public boolean login(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            loggedIn = true;
            System.out.println("Kasir '" + username + " berhasil login.");
            return true;
        }
        return false;
        
    }
    
    public void logout(){
        loggedIn = false;
        System.out.println("Kasir '" + username + "' telah logout.");
    }
    
    public void lihatProduk(ProdukService produkService){
        produkService.lihatProduk();
    }
    
    public void tambahPesanan(Scanner in, ProdukService produkService, PesananService pesananService){
        System.out.println("\n[Kasir] Tambah Pesanan");
        pesananService.tambahPesanan(in, produkService);
    }
    
    public void lihatKeranjang(Scanner in, PesananService pesananService) {
        pesananService.lihatPesananBernomor();

        if (pesananService.daftarPesanan.isEmpty()) return;

        System.out.println("\n1. Hapus item dari keranjang");
        System.out.println("2. Ubah jumlah item");
        System.out.println("0. Kembali");
        System.out.print("Pilihan: ");

        int pilihan;
        try {
            pilihan = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            return;
        }

        switch (pilihan) {
            case 1 -> hapusItemKeranjang(in, pesananService);
            case 2 -> ubahJumlahKeranjang(in, pesananService);
            default -> { }
        }
    }
    
    private void hapusItemKeranjang(Scanner in, PesananService pesananService) {
        System.out.print("Masukkan nomor item yang akan dihapus: ");
        try {
            int nomor = Integer.parseInt(in.nextLine());
            boolean berhasil = pesananService.hapusPesanan(nomor);
            System.out.println(berhasil ? " Item berhasil dihapus dari keranjang." : " Nomor tidak valid.");
        } catch (NumberFormatException e) {
            System.out.println(" Input harus berupa angka.");
        }
    }
    
    private void ubahJumlahKeranjang(Scanner in, PesananService pesananService) {
        System.out.print("Masukkan nomor item yang akan diubah: ");
        try {
            int nomor = Integer.parseInt(in.nextLine());
            System.out.print("Jumlah baru: ");
            int jumlahBaru = Integer.parseInt(in.nextLine());

            String hasil = pesananService.ubahJumlahPesanan(nomor, jumlahBaru);
            System.out.println(hasil.startsWith(" ") ? hasil : "  " + hasil);
        } catch (NumberFormatException e) {
            System.out.println("  Input harus berupa angka.");
        }
    }
    
    public void checkout(PesananService pesananService, ProdukService produkService,
                         TransaksiService transaksiService, Scanner in){
        if (pesananService.daftarPesanan.isEmpty()){
            System.out.println("Keranjang kosong, tidak bisa checkout.");
            return;
        }
        pesananService.lihatPesanan();
        double total = pesananService.hitungTotal();
        
        String metodeBayar = pilihMetodePembayaran(in);
        if (metodeBayar == null) {
            System.out.println("Checkout dibatalkan.");
            return;
        }

        System.out.printf("Metode Bayar  : %s%n", metodeBayar);
        System.out.printf("Total belanja : Rp%.2f%n", total);
        System.out.println("Masukkan uang bayar: Rp");
        double uangBayar;
        try {
            uangBayar = Double.parseDouble(in.nextLine());
        }catch (NumberFormatException e){
            System.out.println(" Input tidak valid.");
            return;
        }
        Pembayaran pembayaran;
        try{
            pembayaran = new Pembayaran(total, uangBayar);
        } catch(IllegalArgumentException e ){
            System.out.println(" " + e.getMessage());
            return;
        }
        for(Pesanan p : pesananService.daftarPesanan){
            p.getProduk().kurangiStok(p.getJumlah());
            transaksiService.tambahTransaksi(new Transaksi(p.getProduk().getNamaProduk(),
                                                p.getJumlah(),p.getSubtotal()));
        }
        
        Struk struk = new Struk(nomorStruk++, total);
        cetakStrukDenganMetode(struk, pesananService, uangBayar, metodeBayar);
        
        pesananService.kosongkan();
    }
    
    private String pilihMetodePembayaran(Scanner in) {
        System.out.println("\n--- Pilih Metode Pembayaran ---");
        System.out.println("1. Tunai");
        System.out.println("2. Debit");
        System.out.println("3. QRIS");
        System.out.println("0. Batal");
        System.out.print("Pilihan: ");

        String pilihan = in.nextLine();
        switch (pilihan) {
            case "1": return "Tunai";
            case "2": return "Debit";
            case "3": return "QRIS";
            default: return null;
        }
    }
    
    private void cetakStrukDenganMetode(Struk struk, PesananService pesananService,
                                         double uangBayar, String metodeBayar) {
        System.out.println("\n========================================");
        System.out.println("            STRUK PEMBAYARAN            ");
        System.out.println("========================================");
        System.out.println("Metode Bayar : " + metodeBayar);
        System.out.println("----------------------------------------");
        System.out.println("Detail Pesanan:");
        for (Pesanan p : pesananService.daftarPesanan) {
            p.tampilkanInfo();
        }
        System.out.println("----------------------------------------");
        double total = pesananService.hitungTotal();
        System.out.printf("Total Bayar : Rp%.2f%n", total);
        System.out.printf("Uang Bayar  : Rp%.2f%n", uangBayar);
        System.out.printf("Kembalian   : Rp%.2f%n", uangBayar - total);
        System.out.println("========================================");
        System.out.println("        Terima kasih sudah belanja!     ");
        System.out.println("========================================\n");
    }
    
    public void buatTransaksi(){
        System.out.println("(Gunakan checkout untuk membuat transaksi otomatis.)");
    }
    
    public void lihatTransaksi(TransaksiService transaksiService){
        transaksiService.lihatTransaksi();
    }
    
}
