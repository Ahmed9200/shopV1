//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package entities.cash;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class client_history implements mainFunctions {

    private int id;
    private int customer_id;
    private int invoice_id;
    private double paid;
    private double remainder;
    private String date;
    private String notes;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

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

    public void add() {
        String strInsert = "insert into client_history values("
                + this.id + " , "
                + this.customer_id + " , "
                + this.invoice_id + " , "
                + this.paid + " , "
                + this.remainder + " , '"
                + this.date + "' , '"
                + this.notes + "');";

        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه الفاتوره فى قاعده البيانات بنجاح :] ");
        } else {

        }

    }

    @Override
    public void update() {
        String strUpdate = "update client_history set "
                + "paid = " + this.paid + " , "
                + "invoice_id = " + this.invoice_id + " , "
                + "notes = '" + this.notes + "' , "
                + "remainder = " + this.remainder + " , "
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
        String strDelete = "delete from client_history where id = " + this.id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح الفاتوره فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    @Override
    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("client_history", "id");
        return strAuto;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
