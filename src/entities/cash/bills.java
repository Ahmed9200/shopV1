//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.cash;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class bills implements mainFunctions {
    private int id;
    private String customer_name;
    private double grandTotal;
    private double discount;
    private double paid;
    private double remainder;

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getRemainder() {
        return remainder;
    }

    public void setRemainder(double remainder) {
        this.remainder = remainder;
    }
    private double totalAfterDiscount;
    private String date;

    public bills() {
    }

    @Override
    public void add() {
        String strInsert = "insert into invoice values(" + 
                this.id + " , " + 
                this.grandTotal + " , " + 
                this.discount + " , " + 
                this.totalAfterDiscount + " , '" + 
                this.date + "', '" + 
                this.customer_name + "' , " + 
                this.paid + " , " + 
                this.remainder + " );";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه الفاتوره فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    @Override
    public void update() {
        String strUpdate = "update invoice set "
                + "customer_name = '" + this.customer_name + "' , "
                + "grandTotal = " + this.grandTotal + " , "
                + "discout = " + this.discount + " , "
                + "paid = " + this.paid + " , "
                + "remainder = " + this.remainder + " , "
                + "totalAfterDiscount = " + this.totalAfterDiscount + " , "
                + "date = '" + this.date + "' \n where id =" + this.id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل الفاتوره فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    @Override
    public void delete() {
        String strDelete = "delete from invoice where id = " + this.id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح الفاتوره فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    @Override
    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("invoice", "id");
        return strAuto;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return this.customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public double getGrandTotal() {
        return this.grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAfterDiscount() {
        return this.totalAfterDiscount;
    }

    public void setTotalAfterDiscount(double totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
