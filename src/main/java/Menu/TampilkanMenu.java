/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import Author.Admin;
import Author.Kasir;
import Service.PesananService;
import Service.ProdukService;
import Service.TransaksiService;
import java.util.Scanner;


/**
 *
 * @author Asus
 */
public class TampilkanMenu {
   public void menuAdmin(Admin admin, Scanner in, ProdukService produkService) {
        int pilihan;
        do {
            System.out.println("\n========== MENU ADMIN ==========");
            System.out.println("1. Lihat Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Ubah Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("0. Logout");
            System.out.print("Pilihan: ");
            pilihan = Integer.parseInt(in.nextLine());
            switch (pilihan) {
                case 1 -> admin.lihatProduk(produkService);
                case 2 -> admin.tambahProduk(in, produkService);
                case 3 -> admin.ubahProduk(in, produkService);
                case 4 -> admin.hapusProduk(in, produkService);
                case 0 -> admin.logout();
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    public void menuKasir(Kasir kasir, Scanner in, ProdukService produkService,
                          PesananService pesananService, TransaksiService transaksiService) {
        int pilihan;
        do {
            
            System.out.println("\n========== MENU KASIR ==========");
            kasir.lihatProduk(produkService);

            System.out.println("\n1. Tambah Pesanan");
            System.out.println("2. Lihat / Kelola Keranjang");
            System.out.println("3. Transaksi");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("0. Logout");
            System.out.print("Pilihan: ");
            pilihan = Integer.parseInt(in.nextLine());
            switch (pilihan) {
                case 1 -> kasir.tambahPesanan(in, produkService, pesananService);
                case 2 -> kasir.lihatKeranjang(in, pesananService);
                case 3 -> kasir.checkout(pesananService, produkService, transaksiService, in);
                case 4 -> kasir.lihatTransaksi(transaksiService);
                case 0 -> kasir.logout();
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }
}
