/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Kategori {
   private int idKtegori;
   private String namaKategori;

    public Kategori(int idKtegori, String namaKategori) {
        this.idKtegori = idKtegori;
        this.namaKategori = namaKategori;
    }

    public int getIdKtegori() {
        return idKtegori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }
   
   
}
