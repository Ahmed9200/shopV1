//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.items;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class items implements mainFunctions {
    private int id;
    private String name;
    private String barcode;
    private int category_id;
    private int supplier_id;
    private double buyPrice;
    private double sellPrice;
    private int quantity;
    private String notes;
    private String date;
    private String sache_no;
    private String motor_no;
    private String kind;
    private String stock;
    private String size;

    public items() {
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void add() {
        String strInsert = "insert into items values(" + 
                this.id + " , '" + 
                this.name + "' , '" + 
                this.barcode + "' , " + 
                this.category_id + " , " + 
                this.supplier_id + " , " + 
                this.buyPrice + " , " + 
                this.sellPrice + " , " + 
                this.quantity + " , '" + 
                this.notes + "', '" + 
                this.date + "', '" + 
                this.sache_no + "', '" + 
                this.motor_no + "', '" + 
                this.kind + "', '" + 
                this.size + "', '" + 
                this.stock + "' );";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه الصنف فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void update() {
        String strUpdate = "update items set "
                + "name = '" + this.name + "' , "
                + "category_id = " + this.category_id + " , "
                + "supplier_id = " + this.supplier_id + " , "
                + "quantity = " + this.quantity + " , "
                + "purchasingPrice = " + this.buyPrice + " , "
                + "sellingPrice = " + this.sellPrice + " , "
                + "notes = '" + this.notes + "' , "
                + "barcode = '" + this.barcode + "' , "
                + "date = '" + this.date + "' , "
                + "sache_no = '" + this.sache_no + "' , "
                + "motor_no = '" + this.motor_no + "' , "
                + "kind = '" + this.kind + "' , "
                + "size = '" + this.size + "' , "
                + "stock = '" + this.stock + "'  \n where id =" + this.id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل الصنف فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void delete() {
        String strDelete = "delete from items where id = " + this.id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح القطعه فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("items", "id");
        return strAuto;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSupplier_id() {
        return this.supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return this.buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return this.sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSache_no() {
        return sache_no;
    }

    public void setSache_no(String sache_no) {
        this.sache_no = sache_no;
    }

    public String getMotor_no() {
        return motor_no;
    }

    public void setMotor_no(String motor_no) {
        this.motor_no = motor_no;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
