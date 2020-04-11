//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.main;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class company implements mainFunctions {
    private int company_id;
    private String company_name;
    private String company_phone;
    private String company_email;
    private String company_fax;
    private String company_address;
    private String company_notes;

    public company() {
    }

    public void add() {
        String strInsert = "insert into company values(" + this.company_id + " , '" + this.company_name + "' , '" + this.company_phone + "' , '" + this.company_fax + "' , '" + this.company_email + "' , '" + this.company_address + "' , '" + this.company_notes + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه الشركه فى قاعده البيانات بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى اضافه الشركه");
        }

    }

    public void update() {
        String strUpdate = "update company set  name = '" + this.company_name + "', phone = '" + this.company_phone + "', mail = '" + this.company_email + "', fax = '" + this.company_fax + "', address = '" + this.company_address + "', notes = '" + this.company_notes + "'  \n where id =" + this.company_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل الشركه فى قاعده البيانات بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى التعديل على الشركه");
        }

    }

    public void delete() {
        String strDelete = "delete from company where id = " + this.company_id;
        System.out.println(strDelete);
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح الشركه فى قاعده البيانات بنجاح");
        } else {
            tools.ErrorBox("هنالك خطأ فى مسح الشركه");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("company", "id");
        return strAuto;
    }

    public int getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return this.company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_phone() {
        return this.company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getCompany_email() {
        return this.company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getCompany_fax() {
        return this.company_fax;
    }

    public void setCompany_fax(String company_fax) {
        this.company_fax = company_fax;
    }

    public String getCompany_address() {
        return this.company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_notes() {
        return this.company_notes;
    }

    public void setCompany_notes(String company_notes) {
        this.company_notes = company_notes;
    }
}
