//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.main;

import Database.database;
import Tools.tools;
import entities.mainFunctions;

public class employee implements mainFunctions {
    private int emp_id;
    private String emp_name;
    private String emp_phone;
    private String emp_email;
    private String emp_ssid;
    private String emp_age;
    private String emp_dob;
    private String emp_certification;
    private String emp_soj;
    private String emp_eoj;
    private double emp_salary;
    private String emp_address;
    private String emp_notes;
    private String emp_username;
    private String emp_password;
    private String emp_bills;

    public employee() {
    }

    public void add() {
        String strInsert = "insert into employees values(" + this.emp_id + " , '" + this.emp_name + "' , '" + this.emp_phone + "' , '" + this.emp_email + "','" + this.emp_ssid + "','" + this.emp_age + "','" + this.emp_dob + "','" + this.emp_certification + "','" + this.emp_soj + "','" + this.emp_eoj + "', " + this.emp_salary + " ,'" + this.emp_address + "','" + this.emp_notes + "','" + this.emp_username + "','" + this.emp_password + "','" + this.emp_bills + "');";
        boolean isAdded = database.excuteQuery(strInsert);
        if (isAdded) {
            tools.InformationBox("تم اضافه الموظف فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void update() {
        String strUpdate = "update employees set emp_name = '" + this.emp_name + "' , emp_phone = '" + this.emp_phone + "' , emp_email = '" + this.emp_email + "' , emp_ssid = '" + this.emp_ssid + "' , emp_age = '" + this.emp_age + "' , emp_dob = '" + this.emp_dob + "' , emp_certification = '" + this.emp_certification + "' , emp_soj = '" + this.emp_soj + "' , emp_eoj = '" + this.emp_eoj + "' , emp_salary = " + this.emp_salary + " , emp_address = '" + this.emp_address + "' , emp_notes = '" + this.emp_notes + "' , emp_username = '" + this.emp_username + "' , emp_password = '" + this.emp_password + "' , emp_bills = '" + this.emp_bills + "'   \n where emp_id =" + this.emp_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (isUpdated) {
            tools.InformationBox("تم تعديل الموظف فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public void delete() {
        String strDelete = "delete from employees where emp_id = " + this.emp_id;
        boolean isDeleted = database.excuteQuery(strDelete);
        if (isDeleted) {
            tools.InformationBox("تم مسح الموظف فى قاعده البيانات بنجاح :] ");
        } else {
            tools.ErrorBox("هنالك خطأ ما");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("employees", "emp_id");
        return strAuto;
    }

    public int getEmp_id() {
        return this.emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return this.emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_phone() {
        return this.emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public String getEmp_email() {
        return this.emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_ssid() {
        return this.emp_ssid;
    }

    public void setEmp_ssid(String emp_ssid) {
        this.emp_ssid = emp_ssid;
    }

    public String getEmp_age() {
        return this.emp_age;
    }

    public void setEmp_age(String emp_age) {
        this.emp_age = emp_age;
    }

    public String getEmp_dob() {
        return this.emp_dob;
    }

    public void setEmp_dob(String emp_dob) {
        this.emp_dob = emp_dob;
    }

    public String getEmp_certification() {
        return this.emp_certification;
    }

    public void setEmp_certification(String emp_certification) {
        this.emp_certification = emp_certification;
    }

    public String getEmp_soj() {
        return this.emp_soj;
    }

    public void setEmp_soj(String emp_soj) {
        this.emp_soj = emp_soj;
    }

    public String getEmp_eoj() {
        return this.emp_eoj;
    }

    public void setEmp_eoj(String emp_eoj) {
        this.emp_eoj = emp_eoj;
    }

    public double getEmp_salary() {
        return this.emp_salary;
    }

    public void setEmp_salary(double emp_salary) {
        this.emp_salary = emp_salary;
    }

    public String getEmp_address() {
        return this.emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public String getEmp_notes() {
        return this.emp_notes;
    }

    public void setEmp_notes(String emp_notes) {
        this.emp_notes = emp_notes;
    }

    public String getEmp_username() {
        return this.emp_username;
    }

    public void setEmp_username(String emp_username) {
        this.emp_username = emp_username;
    }

    public String getEmp_password() {
        return this.emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public String getEmp_bills() {
        return this.emp_bills;
    }

    public void setEmp_bills(String emp_bills) {
        this.emp_bills = emp_bills;
    }
}
