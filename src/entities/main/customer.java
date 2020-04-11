//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.main;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class customer implements mainFunctions {
    private int customer_id;
    private String customer_name;
    private String customer_phone;
    private String customer_address;
    private String customer_notes;

    public customer() {
    }

    public void add() {
        String strInsert = "insert into customer values(" + this.customer_id + " , '" + this.customer_name + "' , '" + this.customer_phone + "' , '" + this.customer_address + "' , '" + this.customer_notes + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه العميل فى قاعده البيانات بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى اضافه العميل");
        }

    }

    public void update() {
        String strUpdate = "update customer set  name = '" + this.customer_name + "', phone = '" + this.customer_phone + "', address = '" + this.customer_address + "', notes = '" + this.customer_notes + "'  \n where id =" + this.customer_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل العميل فى قاعده البيانات بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى التعديل على العميل");
        }

    }

    public void delete() {
        String strDelete = "delete from customer where id = " + this.customer_id;
        System.out.println(strDelete);
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح العميل فى قاعده البيانات بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى مسح العميل");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("customer", "id");
        return strAuto;
    }

    public int getCustomer_id() {
        return this.customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return this.customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return this.customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_address() {
        return this.customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_notes() {
        return this.customer_notes;
    }

    public void setCustomer_notes(String customer_notes) {
        this.customer_notes = customer_notes;
    }
}
