//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.cash;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class outmoney implements mainFunctions {
    private int out_id;
    private String out_price;
    private String out_notes;
    private String out_date;

    public outmoney() {
    }

    public String getOut_notes() {
        return this.out_notes;
    }

    public void setOut_notes(String out_notes) {
        this.out_notes = out_notes;
    }

    public String getOut_price() {
        return this.out_price;
    }

    public void setOut_price(String out_price) {
        this.out_price = out_price;
    }

    public void add() {
        String strInsert = "insert into outmoney values(" + this.out_id + " , '" + this.out_price + "' , '" + this.out_notes + "' , '" + this.out_date + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم تسجبل المبلغ المصروف بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى اضافه الصنف");
        }

    }

    public void update() {
        String strUpdate = "update outmoney set price = '" + this.out_price + "' , details = '" + this.out_notes + "' , date = '" + this.out_date + "'   \n where id =" + this.out_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل المبلغ المصروف بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى التعديل على المبلغ");
        }

    }

    public void delete() {
        String strDelete = "delete from outmoney where id = " + this.out_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح المبلغ المصروف بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى مسح المبلغ");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("outmoney", "id");
        return strAuto;
    }

    public int getOut_id() {
        return this.out_id;
    }

    public void setOut_id(int out_id) {
        this.out_id = out_id;
    }

    public String getOut_date() {
        return this.out_date;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
    }
}
