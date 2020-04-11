//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package entities.items;

import Database.database;
import entities.mainFunctions;

public class barcode implements mainFunctions {
    private String barcode;

    public barcode() {
    }

    public void add() {
        String strInsert = "insert into barcode values('" + this.barcode + "' );";
        database.excuteQuery(strInsert);
    }

    public void update() {
    }

    public void delete() {
    }

    public String getAutoNumber() {
        String strAuto = database.AutoIncrementCoulmn("barcode", "barcode");
        return strAuto;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
