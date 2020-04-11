//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.cash;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class soldItems implements mainFunctions {
    private int id;
    private int soldQuantity;
    private int bill_id;
    private String barcode;
    private String price;
    private String discount;
    private String priceAD;
    private String date;

    public soldItems() {
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPriceAD() {
        return this.priceAD;
    }

    public void setPriceAD(String priceAD) {
        this.priceAD = priceAD;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void add() {
        String strInsert = "insert into solditems values(" + this.id + " , " + this.soldQuantity + " , " + this.bill_id + " , '" + this.barcode + "' , '" + this.price + "' , '" + this.discount + "' , '" + this.priceAD + "' );";
        boolean isAdded = database.excuteQuery(strInsert);
        if (!isAdded) {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void update() {
        String strUpdate = "update solditems set invoice_id = " + this.bill_id + " , soldQuantity = " + this.soldQuantity + " , barcode = '" + this.barcode + "' , price = '" + this.price + "' , discount = '" + this.discount + "' , priceAD = '" + this.priceAD + "' \n where sold_id =" + this.id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (!isUpdated) {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void delete() {
        String strDelete = "delete from solditems where id = " + this.id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (!isDeleted) {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("solditems", "id");
        return strAuto;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_id() {
        return this.bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getSoldQuantity() {
        return this.soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}
