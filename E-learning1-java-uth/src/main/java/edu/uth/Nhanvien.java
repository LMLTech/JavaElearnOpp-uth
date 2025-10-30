package edu.uth;

abstract class Nhanvien {
    protected String maso;
    protected String hoten;
    protected Double luongCB;
    protected ITienThuong phuongthucTinhThuong;

    public Nhanvien(String maso, String hoten, Double LuongCB){
        this.maso = maso;
        this.hoten = hoten;
        this.luongCB = LuongCB;
    }
    public Nhanvien(){
    }

    @Override
    public String toString(){
        return "Nhanvien{" +
                "maso='" + maso + '\'' +
                ", hoten='" + hoten + '\'' +
                ", luongCB=" + luongCB +
                '}';
    }
    public double getTienThuong(){
        return luongCB;
    }
    public String getMaso(){
        return maso;
    }
    public String getHoten(){
        return hoten;
    }
    public ITienThuong getPhuongthucITienThuong(){
        return phuongthucTinhThuong;
    }
    public void setMaso(String maso){
        this.maso = maso;
    }
    public void setHoten(String hoten){
        this.hoten = hoten;
    }
    public void setLuongCB(Double luongCB){
        this.luongCB = luongCB;
    }
}
