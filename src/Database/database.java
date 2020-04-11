//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Database;

import Tools.tools;
import Tools.tools.table;
import entities.cash.bills;
import entities.cash.inmoney;
import entities.cash.outmoney;
import entities.cash.returns;
import entities.cash.soldItems;
import entities.items.ItemsHistory;
import entities.items.items;
import entities.main.category;
import entities.main.company;
import entities.main.customer;
import entities.main.employee;
import entities.main.stock;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

public class database {

    public static Connection con;
    private static final String DB_NAME = "shop";
    private static String url;
    private static final String UNICODE = "?autoReconnect=true&amp;useEncoding=true&amp;characterEncoding=UTF-8";
    public static ImageView backGround;

    public database() {
    }

    private static String setURL() {
        url = "jdbc:mysql://localhost:3306/shop?autoReconnect=true&amp;useEncoding=true&amp;characterEncoding=UTF-8";
        return url;
    }

    public static void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(setURL(), "root", "381998SohailaKaram");
        } catch (SQLException var1) {
            tools.ErrorBox(var1.getMessage());
            System.out.println(var1.getMessage());
        } catch (ClassNotFoundException var2) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var2);
        } catch (InstantiationException var3) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var3);
        } catch (IllegalAccessException var4) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var4);
        }

    }

    public static boolean checkUserAndPassword(String userName, String password) {
        try {
            setConnection();
            Statement statement = con.createStatement();
            String strCheck = "select * from employees where emp_username= '" + userName + "' and emp_password= '" + password + "'";
            statement.executeQuery(strCheck);
            if (statement.getResultSet().next()) {
                return true;
            }

            con.close();
        } catch (SQLException var4) {
            tools.ErrorBox(var4.getMessage());
        }

        return false;
    }

    public static boolean excuteQuery(String sqlStatement) {
        try {
            setConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(sqlStatement);
            con.close();
            return true;
        } catch (SQLException var2) {
            return false;
        }
    }

    public static String AutoIncrementCoulmn(String tableName, String coulmnName) {
        try {
            setConnection();
            Statement statement = con.createStatement();
            String strDBcode = "select max(" + coulmnName + ") +1 AS auto from " + tableName;
            statement.executeQuery(strDBcode);

            String num;
            for (num = ""; statement.getResultSet().next(); num = statement.getResultSet().getString("auto")) {
            }

            con.close();
            return num != null && !"".equals(num) ? num : "1";
        } catch (SQLException var5) {
            tools.ErrorBox(var5.getMessage());
            return null;
        }
    }

    public static table getTableData(String statement) {
        tools t = new tools();

        try {
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int coulmnCount = rsmd.getColumnCount();
            table table = new table(coulmnCount);

            while (rs.next()) {
                Object[] row = new Object[coulmnCount];

                for (int i = 0; i < coulmnCount; ++i) {
                    row[i] = rs.getString(i + 1);
                }

                table.addNewRow(row);
            }

            con.close();
            return table;
        } catch (SQLException var9) {
            t.getClass();
            return new table(0);
        }
    }

    public static void fillComboBoxData(String tableName, String coulmnName, String condition, ComboBox combo) {
        try {
            setConnection();
            Statement stmt = con.createStatement();
            String strSelect = "select " + coulmnName + " from " + tableName + condition;
            ResultSet rs = stmt.executeQuery(strSelect);
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            String[] values = new String[rowCount];

            for (int i = 0; rs.next(); ++i) {
                values[i] = rs.getString(1);
            }

            con.close();
            combo.getItems().addAll(values);
        } catch (SQLException var10) {
            tools.ErrorBox(var10.getMessage());
        }

    }

    public static ObservableList<category> buildItemsTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                category cm = new category();
                cm.setId(Integer.parseInt(rs.getString("id")));
                cm.setName(rs.getString("name"));
                data.add(cm);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var11);
            }

            return data;
        }
    }

    public static ObservableList<stock> buildStockTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                stock cm = new stock();
                cm.setId(Integer.parseInt(rs.getString("id")));
                cm.setName(rs.getString("name"));
                data.add(cm);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var11);
            }

            return data;
        }
    }

    public static ObservableList<inmoney> buildInMoneyTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                inmoney in = new inmoney();
                in.setIn_id(Integer.parseInt(rs.getString("id")));
                in.setIn_date(rs.getString("in_date"));
                in.setIn_notes(rs.getString("details"));
                in.setIn_price(rs.getString("price"));
                data.add(in);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var11);
            }

            return data;
        }
    }

    public static ObservableList<entities.cash.client_history> buildClientHistoryTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                entities.cash.client_history in = new entities.cash.client_history();
                in.setId(rs.getInt("id"));
                in.setInvoice_id(rs.getInt("invoice_id"));
                in.setCustomer_id(rs.getInt("customer_id"));
                in.setDate(rs.getString("date"));
                in.setNotes(rs.getString("notes"));
                in.setPaid(rs.getDouble("paid"));
                in.setRemainder(rs.getDouble("remainder"));
                data.add(in);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var11);
            }

            return data;
        }
    }

    public static ObservableList<outmoney> buildOutMoneyTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                outmoney out = new outmoney();
                out.setOut_id(Integer.parseInt(rs.getString("id")));
                out.setOut_date(rs.getString("date"));
                out.setOut_notes(rs.getString("details"));
                out.setOut_price(rs.getString("price"));
                data.add(out);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var11);
            }

            return data;
        }
    }

    public static ObservableList<returns> buildReturnsTable(String SQL) {
        setConnection();
        ObservableList data = FXCollections.observableArrayList();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                returns out = new returns();
                out.setBill_id(rs.getString("bill_id"));
                out.setDate(rs.getString("date"));
                out.setDrug_barcode(rs.getString("drug_barcode"));
                out.setReturn_id(Integer.parseInt(rs.getString("return_id")));
                out.setReturn_quantity(rs.getString("return_quantity"));
                data.add(out);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var11);
            }

            return data;
        }
    }

    public static ObservableList<employee> buildEmployeTable(String SQL) {
        ObservableList<employee> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                employee e = new employee();
                e.setEmp_id(Integer.parseInt(rs.getString("emp_id")));
                e.setEmp_name(rs.getString("emp_name"));
                data.add(e);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            tools.ErrorBox("هنالك خطأ فى اضاف الداتا من قاعده البيانات الى الجدول");
            System.out.println("Error on Building Data");
        } finally {
            try {
                con.close();
            } catch (SQLException var11) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, (String) null, var11);
            }

            return data;
        }
    }

    public static ObservableList<customer> buildCustomersTable(String SQL) {
        ObservableList<customer> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                customer s = new customer();
                s.setCustomer_id(Integer.parseInt(rs.getString("id")));
                s.setCustomer_name(rs.getString("name"));
                s.setCustomer_phone(rs.getString("phone"));
                s.setCustomer_address(rs.getString("address"));
                s.setCustomer_notes(rs.getString("notes"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            tools.WarningBox(var7.getMessage());
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<company> buildCompaniesTable(String SQL) {
        ObservableList<company> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                company s = new company();
                s.setCompany_id(Integer.parseInt(rs.getString("id")));
                s.setCompany_name(rs.getString("name"));
                s.setCompany_phone(rs.getString("phone"));
                s.setCompany_email(rs.getString("mail"));
                s.setCompany_fax(rs.getString("fax"));
                s.setCompany_address(rs.getString("address"));
                s.setCompany_notes(rs.getString("notes"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            tools.WarningBox(var7.getMessage());
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<items> buildItemTable(String SQL) {
        ObservableList<items> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                items s = new items();
                s.setId(Integer.parseInt(rs.getString("id")));
                s.setName(rs.getString("name"));
                s.setCategory_id(Integer.parseInt(rs.getString("category_id")));
                s.setSupplier_id(Integer.parseInt(rs.getString("supplier_id")));
                s.setQuantity(Integer.parseInt(rs.getString("quantity")));
                s.setBuyPrice(Double.parseDouble(rs.getString("purchasingPrice")));
                s.setSellPrice(Double.parseDouble(rs.getString("sellingPrice")));
                s.setNotes(rs.getString("notes"));
                s.setBarcode(rs.getString("barcode"));
                s.setDate(rs.getString("date"));
                s.setSache_no(rs.getString("sache_no"));
                s.setMotor_no(rs.getString("motor_no"));
                s.setSize(rs.getString("size"));
                s.setKind(rs.getString("kind"));
                s.setStock(rs.getString("stock"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<ItemsHistory> buildStaticItemTable(String SQL) {
        ObservableList<ItemsHistory> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                ItemsHistory s = new ItemsHistory();
                s.setId(Integer.parseInt(rs.getString("id")));
                s.setName(rs.getString("name"));
                s.setCategory_id(Integer.parseInt(rs.getString("category_id")));
                s.setSupplier_id(Integer.parseInt(rs.getString("supplier_id")));
                s.setQuantity(Integer.parseInt(rs.getString("quantity")));
                s.setBuyPrice(Double.parseDouble(rs.getString("purchasingPrice")));
                s.setSellPrice(Double.parseDouble(rs.getString("sellingPrice")));
                s.setNotes(rs.getString("notes"));
                s.setBarcode(rs.getString("barcode"));
                s.setDate(rs.getString("date"));
                s.setSache_no(rs.getString("sache_no"));
                s.setMotor_no(rs.getString("motor_no"));
                s.setSize(rs.getString("size"));
                s.setKind(rs.getString("kind"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<bills> buildBillsTable(String SQL) {
        ObservableList<bills> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                bills s = new bills();
                s.setId(Integer.parseInt(rs.getString("id")));
                s.setCustomer_name(rs.getString("customer_name"));
                s.setGrandTotal(Double.parseDouble(rs.getString("grandTotal")));
                s.setDiscount((double) Integer.parseInt(rs.getString("discout")));
                s.setPaid((double) Integer.parseInt(rs.getString("paid")));
                s.setRemainder((double) Integer.parseInt(rs.getString("remainder")));
                s.setTotalAfterDiscount(Double.parseDouble(rs.getString("totalAfterDiscount")));
                s.setDate(rs.getString("date"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            return data;
        } finally {
            ;
        }
    }

    public static ObservableList<soldItems> buildCustomDrugsTable(String SQL) {
        ObservableList<soldItems> data = FXCollections.observableArrayList();
        setConnection();

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()) {
                soldItems s = new soldItems();
                s.setBarcode(rs.getString("barcode"));
                s.setPrice(rs.getString("sellingPrice"));
                s.setSoldQuantity(Integer.parseInt(rs.getString("SoldQuantity")));
                s.setDiscount(rs.getString("discount"));
                s.setBill_id(Integer.parseInt(rs.getString("invoiceID")));
                s.setDate(rs.getString("date"));
                data.add(s);
            }

            con.close();
            return data;
        } catch (Exception var7) {
            return data;
        } finally {
            ;
        }
    }
}
