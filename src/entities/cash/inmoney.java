//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.cash;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class inmoney implements mainFunctions {
    private int in_id;
    private String in_price;
    private String in_notes;
    private String in_date;

    public inmoney() {
    }

    public String getIn_notes() {
        return this.in_notes;
    }

    public void setIn_notes(String in_notes) {
        this.in_notes = in_notes;
    }

    public String getIn_price() {
        return this.in_price;
    }

    public void setIn_price(String in_price) {
        this.in_price = in_price;
    }

    public void add() {
        String strInsert = "insert into inmoney values(" + this.in_id + " , '" + this.in_price + "' , '" + this.in_notes + "' , '" + this.in_date + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم تسجبل الايراد بمجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى اضافه الصنف");
        }

    }

    public void update() {
        String strUpdate = "update inmoney set price = '" + this.in_price + "' , details = '" + this.in_notes + "' , in_date = '" + this.in_date + "'   \n where in_id =" + this.in_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل الايراد بمجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى التعديل على الصنف");
        }

    }

    public void delete() {
        String strDelete = "delete from inmoney where id = " + this.in_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح الايراد بمجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى مسح الصنف");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("inmoney", "id");
        return strAuto;
    }

    public int getIn_id() {
        return this.in_id;
    }

    public void setIn_id(int in_id) {
        this.in_id = in_id;
    }

    public String getIn_date() {
        return this.in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }
}
