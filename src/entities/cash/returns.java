    //
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.cash;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class returns implements mainFunctions {
    private int return_id;
    private String drug_barcode;
    private String return_quantity;
    private String bill_id;
    private String date;

    public returns() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReturn_id() {
        return this.return_id;
    }

    public void setReturn_id(int return_id) {
        this.return_id = return_id;
    }

    public String getDrug_barcode() {
        return this.drug_barcode;
    }

    public void setDrug_barcode(String drug_barcode) {
        this.drug_barcode = drug_barcode;
    }

    public String getReturn_quantity() {
        return this.return_quantity;
    }

    public void setReturn_quantity(String return_quantity) {
        this.return_quantity = return_quantity;
    }

    public String getBill_id() {
        return this.bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public void add() {
        String strInsert = "insert into returns values(" + this.return_id + " , '" + this.drug_barcode + "' , '" + this.return_quantity + "' , '" + this.bill_id + "' , '" + this.date + "' );";
        System.out.println(strInsert);
        boolean isAdded = database.excuteQuery(strInsert);
        if (!isAdded) {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void update() {
        String strUpdate = "update returns set drug_barcode = '" + this.drug_barcode + "' , return_quantity = '" + this.return_quantity + "' , bill_id = '" + this.bill_id + "' , date = '" + this.date + "'  \n where id =" + this.return_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (!isUpdated) {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void delete() {
        String strDelete = "delete from returns where id = " + this.return_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (!isDeleted) {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("returns", "return_id");
        return strAuto;
    }
}
