/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tagihan;

/**
 *
 * @author Asus
 */
public class Pembayaran {
    private double totalBayar;
    private double uangBayar;
    private double kembalian;
    
    public Pembayaran(double totalBayar, double uangBayar){
        if (uangBayar < totalBayar){
            throw new IllegalArgumentException("Uang bayar kurang dari total belanja!");
        }
        this.totalBayar = totalBayar;
        this.uangBayar = uangBayar;
        this.kembalian = uangBayar - totalBayar;
    }
    
    public double getTotalBayar(){
        return totalBayar;
    }

    public double getUangBayar() {
        return uangBayar;
    }

    public double getKembalian() {
        return kembalian;
    }
    
    public void tampilakanStruk(){
        System.out.println("----------------------------------------");
        System.out.printf("Total Bayar : Rp%.2f%n", totalBayar);
        System.out.printf("Uang Bayar  : Rp%.2f%n", uangBayar);
        System.out.printf("Kembalian   : Rp%.2f%n", kembalian);
        System.out.println("----------------------------------------");
    }
    
}
