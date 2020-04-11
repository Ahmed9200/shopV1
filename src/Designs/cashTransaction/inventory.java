//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.cashTransaction;

import Database.database;
import Tools.tools;
import entities.cash.bills;
import entities.cash.inmoney;
import entities.cash.outmoney;
import entities.items.ItemsHistory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class inventory {

    public static HBox root;
    StackPane main;
    Label tittle;
    Label startLabel;
    Label endLabel;
    Label l2;
    Label totalOfRemainderLabel;
    Label l3;
    Label l4;
    Label l5;
    Label l6;
    Label l7;
    Label l8;
    Label l9;
    Label l10;
    Label l11;
    public static TextField tf2;
    public static TextField totalOfRemainder;
    public static TextField tf3;
    public static TextField tf4;
    public static TextField tf5;
    public static TextField tf6;
    public static TextField tf7;
    public static TextField tf8;
    public static TextField tf9;
    public static TextField tf10;
    public static TextField tf11;
    public static DatePicker start;
    public static DatePicker end;
    public static Button add;
    public static TimeSpinner start_spinner;
    public static TimeSpinner end_spinner;
    public static TableView<ItemsHistory> buyTable;
    public static TableView<bills> billTable;
    public static TableView<inmoney> inmoneyTable;
    public static TableView<outmoney> outmoneyTable;
    public static HBox EmpeRow;

    public inventory() {
        start_spinner = new TimeSpinner();
        end_spinner = new TimeSpinner();
        EmpeRow = new HBox();
        this.l2 = new Label("اجمالى المبلغ من الفواتير المباعه");
        this.totalOfRemainderLabel = new Label("اجمالى المبالغ المتبقيه من التقسيطات ");
        this.l3 = new Label("اجمالى المبلغ من المشتريات");
        this.l4 = new Label("اجمالى المبلغ من الواردات");
        this.l5 = new Label("اجمالى المبلغ من المصروفات");
        this.l6 = new Label("اجمالى الدخل فى هذه الفتره");
        this.l7 = new Label("اجمالى الصرف فى هذه الفتره");
        this.l8 = new Label("اجمالى المبلغ الداخل بعد خصم المصروفات");
        this.l9 = new Label("الربح من البضاعه المباعه");
        this.l10 = new Label("مجموع الخصومات فى هذه الفتره");
        this.l11 = new Label("صافى الربح من البضاعه بعد خصم الخصومات");
        this.tittle = new Label("الجرد");
        tf2 = new TextField("0.0");
        totalOfRemainder = new TextField("0.0");
        tf3 = new TextField("0.0");
        tf4 = new TextField("0.0");
        tf5 = new TextField("0.0");
        tf6 = new TextField("0.0");
        tf7 = new TextField("0.0");
        tf8 = new TextField("0.0");
        tf9 = new TextField("0.0");
        tf10 = new TextField("0.0");
        tf11 = new TextField("0.0");
        HBox v2 = new HBox(20.0D, new Node[]{tf2, this.l2});
        HBox v22 = new HBox(20.0D, new Node[]{totalOfRemainder, this.totalOfRemainderLabel});
        HBox v3 = new HBox(20.0D, new Node[]{tf3, this.l3});
        HBox v4 = new HBox(20.0D, new Node[]{tf4, this.l4});
        HBox v5 = new HBox(20.0D, new Node[]{tf5, this.l5});
        HBox v6 = new HBox(20.0D, new Node[]{tf6, this.l6});
        HBox v7 = new HBox(20.0D, new Node[]{tf7, this.l7});
        HBox v8 = new HBox(20.0D, new Node[]{tf8, this.l8});
        HBox v9 = new HBox(20.0D, new Node[]{tf9, this.l9});
        HBox v10 = new HBox(20.0D, new Node[]{tf10, this.l10});
        VBox mainDetails = new VBox(20.0D, new Node[]{v2, v22, v3, v4, v5, v6, v7, v10, v8, v9});
        mainDetails.setId("inventoryStyle");
        mainDetails.setId("lbl_txt_box");
        this.startLabel = new Label("من");
        this.endLabel = new Label("الى");
        add = new Button("جرد", new ImageView("images/add.png"));
        add.setAlignment(Pos.CENTER);
        VBox h2 = new VBox(10.0D, new Node[]{mainDetails});
        TitledPane one2 = new TitledPane("التفاصيل", h2);
        one2.setPrefWidth(1000.0D);
        one2.setExpanded(false);
        start = new DatePicker();
        start.setPromptText("تاريخ البدايه");
        end = new DatePicker();
        end.setPromptText("تاريخ النهايه ");
        add.setOnAction((event) -> {
            System.out.println(start_spinner.getEditor().getText());

            try {
                if (start.getValue() != null && end.getValue() != null) {
                    this.filterTables(start.getValue() + " " + start_spinner.getEditor().getText(), end.getValue() + " " + end_spinner.getEditor().getText());
                    this.setTf2();
                    this.setTf3();
                    this.setTf4();
                    this.setTf5();
                    this.setTf6();
                    this.setTf7();
                    this.setTf8();
                    this.setTf9(start.getValue() + " " + start_spinner.getEditor().getText(), end.getValue() + " " + end_spinner.getEditor().getText());
                    this.setTf10();
                    one2.setExpanded(true);
                } else {
                    tools.ErrorBox("من فضلك قم بادخال التاريخ");
                }
            } catch (Exception var4) {
            }

        });
        buyTable = new TableView();
        buyTable.setPrefWidth(970.0D);
        billTable = new TableView();
        billTable.setPrefWidth(970.0D);
        inmoneyTable = new TableView();
        inmoneyTable.setPrefWidth(970.0D);
        outmoneyTable = new TableView();
        outmoneyTable.setPrefWidth(970.0D);
        buyTable.setItems(database.buildStaticItemTable("select * from additemtemp;"));
        billTable.setItems(database.buildBillsTable("select * from invoice;"));
        inmoneyTable.setItems(database.buildInMoneyTable("select * from inmoney;"));
        outmoneyTable.setItems(database.buildOutMoneyTable("select * from outmoney;"));

        TableColumn<bills, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<bills, String> nameColumn = new TableColumn("اسم العميل");
        nameColumn.setMinWidth(30.0D);
        nameColumn.setCellValueFactory(new PropertyValueFactory("customer_name"));
        TableColumn<bills, String> typeColumn = new TableColumn("كودالموظف");
        typeColumn.setMinWidth(15.0D);
        typeColumn.setCellValueFactory(new PropertyValueFactory("emp_id"));
        TableColumn<bills, String> supplierColumn = new TableColumn("السعر ق.خ");
        supplierColumn.setMinWidth(15.0D);
        supplierColumn.setCellValueFactory(new PropertyValueFactory("grandTotal"));
        TableColumn<bills, String> quantityCoulmn = new TableColumn("نسبه الخصم");
        quantityCoulmn.setMinWidth(20.0D);
        quantityCoulmn.setCellValueFactory(new PropertyValueFactory("discount"));
        TableColumn<bills, String> buyCoulmn = new TableColumn("السعر ب.خ");
        buyCoulmn.setMinWidth(20.0D);
        buyCoulmn.setCellValueFactory(new PropertyValueFactory("totalAfterDiscount"));
        TableColumn<bills, String> productionDateCoulmn = new TableColumn("التاريخ");
        productionDateCoulmn.setMinWidth(25.0D);
        productionDateCoulmn.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<bills, String> paidCoulmn = new TableColumn("المدفوع");
        paidCoulmn.setMinWidth(25.0D);
        paidCoulmn.setCellValueFactory(new PropertyValueFactory("paid"));

        TableColumn<bills, String> remainderCoulmn = new TableColumn("الباقى");
        remainderCoulmn.setMinWidth(25.0D);
        remainderCoulmn.setCellValueFactory(new PropertyValueFactory("remainder"));

        billTable.getColumns().addAll(new TableColumn[]{idColumn, nameColumn, typeColumn, supplierColumn, quantityCoulmn, buyCoulmn, productionDateCoulmn, paidCoulmn, remainderCoulmn});

        TableColumn<ItemsHistory, Integer> idStaticColumn = new TableColumn("الكود");
        idStaticColumn.setMinWidth(15.0D);
        idStaticColumn.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<ItemsHistory, String> nameStaticColumn = new TableColumn("الاسم");
        nameStaticColumn.setMinWidth(30.0D);
        nameStaticColumn.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<ItemsHistory, String> barcodeStaticColumn = new TableColumn("الباركود");
        barcodeStaticColumn.setMinWidth(15.0D);
        barcodeStaticColumn.setCellValueFactory(new PropertyValueFactory("barcode"));
        TableColumn<ItemsHistory, String> typeStaticColumn = new TableColumn("كود الصنف");
        typeStaticColumn.setMinWidth(15.0D);
        typeStaticColumn.setCellValueFactory(new PropertyValueFactory("category_id"));
        TableColumn<ItemsHistory, String> supplierStaticCoulmn = new TableColumn("كود المورد");
        supplierStaticCoulmn.setMinWidth(20.0D);
        supplierStaticCoulmn.setCellValueFactory(new PropertyValueFactory("supplier_id"));
        TableColumn<ItemsHistory, String> quantityStaticCoulmn = new TableColumn("الكميه");
        quantityStaticCoulmn.setMinWidth(20.0D);
        quantityStaticCoulmn.setCellValueFactory(new PropertyValueFactory("quantity"));
        TableColumn<ItemsHistory, String> buyPriceStaticCoulmn = new TableColumn("سعر الشراء");
        buyPriceStaticCoulmn.setMinWidth(20.0D);
        buyPriceStaticCoulmn.setCellValueFactory(new PropertyValueFactory("buyPrice"));
        TableColumn<ItemsHistory, String> sellPriceStstaticCoulmn = new TableColumn("سعر البيع");
        sellPriceStstaticCoulmn.setMinWidth(25.0D);
        sellPriceStstaticCoulmn.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        TableColumn<ItemsHistory, String> howToSellStstaticCoulmn = new TableColumn("الملاحظات");
        howToSellStstaticCoulmn.setMinWidth(25.0D);
        howToSellStstaticCoulmn.setCellValueFactory(new PropertyValueFactory("notes"));
        TableColumn<ItemsHistory, String> dateComingStstaticCoulmn = new TableColumn("تاريخ القدوم");
        dateComingStstaticCoulmn.setMinWidth(25.0D);
        dateComingStstaticCoulmn.setCellValueFactory(new PropertyValueFactory("date"));
        buyTable.getColumns().addAll(new TableColumn[]{idStaticColumn, nameStaticColumn, barcodeStaticColumn, typeStaticColumn, supplierStaticCoulmn, quantityStaticCoulmn, buyPriceStaticCoulmn, sellPriceStstaticCoulmn, howToSellStstaticCoulmn, dateComingStstaticCoulmn});

        TableColumn<inmoney, Integer> idInMoneyColumn = new TableColumn("الكود");
        idInMoneyColumn.setMinWidth(20.0D);
        idInMoneyColumn.setCellValueFactory(new PropertyValueFactory("in_id"));
        TableColumn<inmoney, String> priceCoulmn = new TableColumn("السعر");
        priceCoulmn.setMinWidth(30.0D);
        priceCoulmn.setCellValueFactory(new PropertyValueFactory("in_price"));
        TableColumn<inmoney, String> notesCoulmn = new TableColumn("ملاحظات");
        notesCoulmn.setMinWidth(30.0D);
        notesCoulmn.setCellValueFactory(new PropertyValueFactory("in_notes"));
        TableColumn<inmoney, String> dateInMoneyCoulmn = new TableColumn("التاريخ");
        dateInMoneyCoulmn.setMinWidth(30.0D);
        dateInMoneyCoulmn.setCellValueFactory(new PropertyValueFactory("in_date"));
        inmoneyTable.getColumns().addAll(new TableColumn[]{idInMoneyColumn, priceCoulmn, notesCoulmn, dateInMoneyCoulmn});
        TableColumn<outmoney, Integer> idOutColumn = new TableColumn("الكود");
        idOutColumn.setMinWidth(20.0D);
        idOutColumn.setCellValueFactory(new PropertyValueFactory("out_id"));
        TableColumn<outmoney, String> priceOutCoulmn = new TableColumn("السعر");
        priceOutCoulmn.setMinWidth(30.0D);
        priceOutCoulmn.setCellValueFactory(new PropertyValueFactory("out_price"));
        TableColumn<outmoney, String> notesOutCoulmn = new TableColumn("ملاحظات");
        notesOutCoulmn.setMinWidth(30.0D);
        notesOutCoulmn.setCellValueFactory(new PropertyValueFactory("out_notes"));
        TableColumn<outmoney, String> dateOutCoulmn = new TableColumn("التاريخ");
        dateOutCoulmn.setMinWidth(30.0D);
        dateOutCoulmn.setCellValueFactory(new PropertyValueFactory("out_date"));
        outmoneyTable.getColumns().addAll(new TableColumn[]{idOutColumn, priceOutCoulmn, notesOutCoulmn, dateOutCoulmn});
        HBox h14 = new HBox(10.0D, new Node[]{start_spinner, start, this.startLabel});
        h14.setAlignment(Pos.CENTER_RIGHT);
        HBox h16 = new HBox(10.0D, new Node[]{end_spinner, end, this.endLabel});
        h16.setAlignment(Pos.CENTER_RIGHT);
        VBox loginVB = new VBox(10.0D, new Node[]{h14, h16});
        loginVB.setId("loginRequire");
        HBox login = new HBox(10.0D, new Node[]{loginVB});
        login.setAlignment(Pos.CENTER);
        HBox ButtonsRow = new HBox(10.0D, new Node[]{add});
        ButtonsRow.setId("three_btn");
        HBox allButtons = new HBox(10.0D, new Node[]{ButtonsRow});
        allButtons.setId("btnbox");
        HBox buyTableRow = new HBox(new Node[]{buyTable});
        HBox billTableRow = new HBox(new Node[]{billTable});
        HBox inmoneyTableRow = new HBox(new Node[]{inmoneyTable});
        HBox outmoneyTableRow = new HBox(new Node[]{outmoneyTable});
        VBox h = new VBox(10.0D, new Node[]{login, allButtons});
        TitledPane one = new TitledPane("اختيار المده", h);
        one.setPrefWidth(1000.0D);
        TitledPane four = new TitledPane("المشتريات  خلال الفتره", buyTableRow);
        four.setPrefWidth(1000.0D);
        four.setExpanded(false);
        TitledPane six = new TitledPane("الفواتير خلال هذه الفتره", billTableRow);
        six.setPrefWidth(1000.0D);
        six.setExpanded(false);
        TitledPane seven = new TitledPane("الايرادات خلال هذه الفتره", inmoneyTableRow);
        seven.setPrefWidth(1000.0D);
        seven.setExpanded(false);
        TitledPane eight = new TitledPane("المصروفات خلال هذه الفتره", outmoneyTableRow);
        eight.setPrefWidth(1000.0D);
        eight.setExpanded(false);

        eight.setPrefWidth(1000.0D);
        eight.setExpanded(false);
        VBox left = new VBox(20.0D, new Node[]{one, one2, four, six, seven, eight});
        HBox center = new HBox(30.0D, new Node[]{left});
        center.setId("root");
        HBox tittle_hbox = new HBox(new Node[]{this.tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        this.tittle.setId("tittle");
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center});
        this.main = new StackPane(new Node[]{all});
        clear();
        root = new HBox(new Node[]{this.main});
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    void setTf2() {
        try {
            double sum = 0.0D;

            for (int i = 0; i < billTable.getItems().size(); ++i) {
                sum += ((bills) billTable.getItems().get(i)).getPaid();
            }

            tf2.setText(sum + "");
        } catch (Exception var4) {
            tf2.setText("0.0");
        }

    }

    void setTotalOfRemainder() {
        try {
            double sum = 0.0D;

            for (int i = 0; i < billTable.getItems().size(); ++i) {
                sum += ((bills) billTable.getItems().get(i)).getRemainder();
            }

            totalOfRemainder.setText(sum + "");
        } catch (Exception var4) {
            totalOfRemainder.setText("0.0");
        }

    }

    void setTf10() {
        try {
            double sum = 0.0D;

            for (int i = 0; i < billTable.getItems().size(); ++i) {
                sum += ((bills) billTable.getItems().get(i)).getDiscount();
            }

            tf10.setText(sum + "");
        } catch (Exception var4) {
            tf10.setText("0.0");
        }

    }

    void setTf3() {
        try {
            double sum = 0.0D;

            for (int i = 0; i < buyTable.getItems().size(); ++i) {
                sum += ((ItemsHistory) buyTable.getItems().get(i)).getBuyPrice() * (double) ((ItemsHistory) buyTable.getItems().get(i)).getQuantity();
            }

            tf3.setText(sum + "");
        } catch (Exception var4) {
            tf3.setText("0.0");
        }

    }

    void setTf4() {
        try {
            double sum = 0.0D;

            for (int i = 0; i < inmoneyTable.getItems().size(); ++i) {
                sum += Double.parseDouble(((inmoney) inmoneyTable.getItems().get(i)).getIn_price());
            }

            tf4.setText(sum + "");
        } catch (Exception var4) {
            tf4.setText("0.0");
        }

    }

    void setTf5() {
        try {
            double sum = 0.0D;

            for (int i = 0; i < outmoneyTable.getItems().size(); ++i) {
                sum += Double.parseDouble(((outmoney) outmoneyTable.getItems().get(i)).getOut_price());
            }

            tf5.setText(sum + "");
        } catch (Exception var4) {
            tf5.setText("0.0");
        }

    }

    void setTf6() {
        try {
            if (tf2.getText().equals("")) {
                tf2.setText("0.0");
            }

            if (tf3.getText().equals("")) {
                tf3.setText("0.0");
            }

            if (tf4.getText().equals("")) {
                tf4.setText("0.0");
            }

            if (tf5.getText().equals("")) {
                tf5.setText("0.0");
            }

            double billPrice = Double.parseDouble(tf2.getText());
            double inmoneyPrice = Double.parseDouble(tf4.getText());
            tf6.setText("" + (billPrice + inmoneyPrice));
        } catch (Exception var5) {
            tf6.setText("0.0");
        }

    }

    void setTf7() {
        try {
            double buyPrice = Double.parseDouble(tf3.getText());
            double outmoneyPrice = Double.parseDouble(tf5.getText());
            tf7.setText("" + (buyPrice + outmoneyPrice));
        } catch (Exception var5) {
            tf7.setText("0.0");
        }

    }

    void setTf8() {
        try {
            double in = Double.parseDouble(tf6.getText());
            double out = Double.parseDouble(tf7.getText());
            tf8.setText("" + (in - out));
        } catch (Exception var5) {
            tf8.setText("0.0");
        }

    }

    void setTf9(String date1, String date2) {
        String total = database.getTableData("select sum(purchasingprice * soldQuantity) from solditems_view where date between '" + date1 + "' and '" + date2 + "';").items[0][0].toString();
        double total_purchasingPrice = Double.parseDouble(total);
        double total_sellPrice = Double.parseDouble(tf2.getText());
        tf9.setText(total_sellPrice - total_purchasingPrice + "");
    }

    void filterTables(String date1, String date2) {
        System.out.print("date1 = " + date1);
        System.out.println("  &&  date2 = " + date2);
        Database.database.excuteQuery("delete FROM invoice where totalAfterDiscount <= 0 and paid <=0 and remainder<=0;");
        buyTable.setItems(database.buildStaticItemTable("select * from additemtemp where date between '" + date1 + "' and '" + date2 + "' ;"));
        billTable.setItems(database.buildBillsTable("select * from invoice where date between '" + date1 + "' and '" + date2 + "';"));
        inmoneyTable.setItems(database.buildInMoneyTable("select * from inmoney where in_date between '" + date1 + "' and '" + date2 + "';"));
        outmoneyTable.setItems(database.buildOutMoneyTable("select * from outmoney where date between '" + date1 + "' and '" + date2 + "';"));
    }

    private void setTf11() {
        try {
            String discount = tf10.getText();
            String total = tf9.getText();
            tf11.setText(Double.parseDouble(total) - Double.parseDouble(discount) + "");
        } catch (Exception var3) {
        }

    }

    private void clear() {

        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");
        tf6.setText("");
        tf7.setText("");
        tf8.setText("");
        tf9.setText("");
        tf10.setText("");
        tf11.setText("");
        totalOfRemainder.setText("");
        billTable.getItems().clear();
        buyTable.getItems().clear();
        inmoneyTable.getItems().clear();
        outmoneyTable.getItems().clear();

    }
}
