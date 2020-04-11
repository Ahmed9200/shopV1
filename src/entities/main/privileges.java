//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.main;

import Database.database;
import Tools.tools;
import Tools.tools.table;
import entities.mainFunctions;

    public class privileges implements mainFunctions {
    private int p_id;
    private String privilegesForm;
    private int emp_id;

    public privileges() {
    }

    public int getP_id() {
        return this.p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getPrivilegesForm() {
        return this.privilegesForm;
    }

    public void setPrivilegesForm(String privilegesForm) {
        this.privilegesForm = privilegesForm;
    }

    public int getEmp_id() {
        return this.emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public void add() {
        String strInsert = "insert into privileges values(" + this.p_id + " , " + this.emp_id + " , '" + this.privilegesForm + "' )";
        boolean isAdded = database.excuteQuery(strInsert);
        if (!isAdded) {
            tools.ErrorBox("faild successfully");
        }

    }

    public void update() {
        String strUpdate = "update privileges set emp_id = " + this.emp_id + " , privilegesForm = '" + this.privilegesForm + "'  \n where p_id =" + this.p_id;
        boolean isUpdated = database.excuteQuery(strUpdate);
        if (!isUpdated) {
            tools.ErrorBox("faild successfully");
        }

    }

    public void delete() {
        String strDelete = "delete from privileges where emp_id = " + this.emp_id + " and privilegesForm ='" + this.privilegesForm + "';";
        System.out.println(strDelete);
        boolean isDeleted = database.excuteQuery(strDelete);
        if (!isDeleted) {
            tools.ErrorBox("faild successfully");
        }

    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("privileges", "p_id");
        return strAuto;
    }

    public static boolean HasPrivilege(String username, String p) {
        String empId = database.getTableData("select emp_id from employees where emp_username ='" + username + "';").items[0][0].toString();
        String count = database.getTableData("select count(privilegesForm) from privileges where emp_id ='" + empId + "';").items[0][0].toString();
        table t = database.getTableData("select privilegesForm from privileges where emp_id ='" + empId + "';");
        String[] privileges = new String[Integer.parseInt(count)];

        for(int i = 0; i < privileges.length; ++i) {
            privileges[i] = t.items[i][0].toString();
            if (privileges[i].equals(p)) {
                return true;
            }
        }

        return false;
    }
}
