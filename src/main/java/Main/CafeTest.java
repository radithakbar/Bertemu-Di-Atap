/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Author.Admin;
import Author.Kasir;
import Menu.TampilkanMenu;
import Service.AuthService;
import Service.PesananService;
import Service.ProdukService;
import Service.TransaksiService;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class CafeTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        AuthService authService = new AuthService();
        ProdukService produkService = new ProdukService();
        PesananService pesananService = new PesananService();
        TransaksiService transaksiService = new TransaksiService();
        TampilkanMenu menu = new TampilkanMenu();

        Admin admin = new Admin("admin", "admin123");
        Kasir kasir = new Kasir("kasir", "kasir123");
        authService.register(admin);
        authService.register(kasir);

        System.out.println("|===========================|");
        System.out.println("|        SISTEM KASIR       |");
        System.out.println("|===========================|");

        int pilihan;
        do {
            System.out.println("\n=== HALAMAN UTAMA ===");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Kasir");
            System.out.println("0. Keluar");
            System.out.print("Pilihan: ");

            try {
                pilihan = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                pilihan = -1;
            }

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Username: ");
                    String u = in.nextLine();
                    System.out.print("Password: ");
                    String p = in.nextLine();
                    if (admin.login(u, p)) {
                        menu.menuAdmin(admin, in, produkService);
                    } else {
                        System.out.println(" Username/password salah.");
                    }
                }
                case 2 -> {
                    System.out.print("Username: ");
                    String u = in.nextLine();
                    System.out.print("Password: ");
                    String p = in.nextLine();
                    if (kasir.login(u, p)) {
                        menu.menuKasir(kasir, in, produkService, pesananService, transaksiService);
                    } else {
                        System.out.println(" Username/password salah.");
                    }
                }
                case 0 -> System.out.println("Sampai jumpa!");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);

        in.close();
    }
 
}
