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
   private int idKategori;
   private String namaKategori;

    public Kategori(int idKtegori, String namaKategori) {
        this.idKategori = idKtegori;
        this.namaKategori = namaKategori;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    @Override
    public String toString() {
        return namaKategori;
    }
   
   
}
