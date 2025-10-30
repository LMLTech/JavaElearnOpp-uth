package edu.uth;

public interface INhanvienFactory {
    public Nhanvien createNhanvien(String loaiNV, String maso, String hoten, double luongCB);
    public Nhanvien createNhanvien(String loaiNV);
} 
