//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.cashTransaction;

import Database.database;
import Designs.Home;
import Tools.tools;
import entities.cash.bills;
import entities.cash.soldItems;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class allBills {

    public static HBox root;
    public static Label tittle;
    public static StackPane main;
    public static TableView<bills> bTable;
    public static TableView<soldItems> dTable;
    public static TextField user_id;
    public static TextField userQuantity;
    public static TextField billTextSearch1;
    public static TextField billTextSearch2;
    public static TextField drugTextSearch;
    public static Button searchBill;
    public static Button searchDrug;
    Button deleteBill;
    Button deleteItem;
    public static String drug_bar;
    public static ComboBox billSearchChooser;
    public static ComboBox drugSearchChooser;
    public static ComboBox billSearchChooser2;
    public static DatePicker beginDate;
    public static DatePicker endDate;
    public static final ObservableList<String> data = FXCollections.observableArrayList(new String[]{"كود الفاتوره", "السعر ب خ", "الخصم", "اسم العميل", "كود الموظف"});

    public allBills() {
        tittle = new Label("جميع الفواتير");
        billTextSearch1 = new TextField();
        billTextSearch1.setText("");
        billTextSearch1.setPromptText("كلمه البحث");
        billTextSearch2 = new TextField();
        billTextSearch2.setText("");
        billTextSearch2.setPromptText("كلمه البحث");
        drugTextSearch = new TextField();
        drugTextSearch.setPromptText("كلمه البحث");
        billSearchChooser = new ComboBox(data);
        billSearchChooser.setPromptText(" اختار طريقه البحث");
        billSearchChooser2 = new ComboBox(data);
        billSearchChooser2.setPromptText("اختار طريقه البحث");
        drugSearchChooser = new ComboBox();
        drugSearchChooser.setPromptText("اختار طريقه البحث");
        beginDate = new DatePicker();
        beginDate.setUserData((Object) null);
        beginDate.setPromptText("ادخل التاريخ الاول");
        endDate = new DatePicker();
        endDate.setUserData((Object) null);
        endDate.setPromptText("ادخل التاريخ الثانى");
        this.deleteBill = new Button("مسح", new ImageView("images/delete.png"));
        this.deleteBill.setOnAction((event) -> {
            String b_id = ((bills) bTable.getSelectionModel().getSelectedItem()).getId() + "";
            int n = database.buildCustomDrugsTable("select * from solditems_forbills_view where invoiceID=" + b_id).size();

            for (int i = 0; i < n; ++i) {
                try {
                    String barcode = ((soldItems) dTable.getItems().get(0)).getBarcode();
                    String price = ((soldItems) dTable.getItems().get(0)).getPrice();
                    String discount = ((soldItems) dTable.getItems().get(0)).getDiscount();
                    int soldQuantity = ((soldItems) dTable.getItems().get(0)).getSoldQuantity();
                    String dis = ((soldItems) dTable.getItems().get(0)).getDiscount();
                    this.setTotalBeforeDiscount(soldQuantity + "", price, b_id);
                    this.setTotalAfterDiscount(price, soldQuantity + "", soldQuantity + "", discount, "" + b_id);
                    this.deleteItem(dis, barcode, soldQuantity + "", price + "", soldQuantity + "", b_id);
                } catch (Exception var10) {
                }
            }

            bTable.setItems(database.buildBillsTable("select * from invoice"));
        });
        this.deleteItem = new Button("مسح", new ImageView("images/delete.png"));
        this.deleteItem.setOnAction((event) -> {
            try {
                if (Integer.parseInt(userQuantity.getText()) <= 0) {
                    tools.WarningBox("لا يمكنك استرجاع كميه سالبه او صفر من فضلك ادخل عدد صحيح");
                    userQuantity.setText("1");
                } else if (Integer.parseInt(userQuantity.getText()) > Integer.parseInt("" + ((soldItems) dTable.getSelectionModel().getSelectedItem()).getSoldQuantity())) {
                    tools.WarningBox("لا يمكنك استرجاع كميه اكبر من المباعه من فضلك ادخل رقم صحيح ");
                    userQuantity.setText("1");
                } else {
                    try {
                        String barcode = ((soldItems) dTable.getSelectionModel().getSelectedItem()).getBarcode();
                        String quantity = userQuantity.getText();
                        String bid = ((soldItems) dTable.getSelectionModel().getSelectedItem()).getBill_id() + "";
                        String discount = ((soldItems) dTable.getSelectionModel().getSelectedItem()).getDiscount();
                        double price = Double.parseDouble(((soldItems) dTable.getSelectionModel().getSelectedItem()).getPrice());
                        double soldQuantity = Double.parseDouble("" + ((soldItems) dTable.getSelectionModel().getSelectedItem()).getSoldQuantity());
                        if (dTable.getSelectionModel().getSelectedItem() == null) {
                            tools.WarningBox("من فضلك قم باختيار صنف تم بيعه مسبقا");
                        } else {
                            this.setTotalBeforeDiscount(quantity + "", price + "", bid + "");
                            this.setTotalAfterDiscount(price + "", quantity, soldQuantity + "", discount, bid);
                            this.deleteItem(discount, barcode, quantity, price + "", soldQuantity + "", bid + "");
                            bTable.setItems(database.buildBillsTable("select * from invoice"));
                        }
                    } catch (Exception var10) {
                    }
                }
            } catch (Exception var11) {
            }

        });
        bTable = new TableView();
        bTable.setItems(database.buildBillsTable("SELECT * FROM invoice where totalAfterDiscount > 0;"));
        dTable = new TableView();
        bTable.setPrefSize(900.0D, 500.0D);
        dTable.setPrefSize(900.0D, 500.0D);
        dTable.setItems(database.buildCustomDrugsTable("select * from solditems_forbills_view"));
        user_id = new TextField();
        user_id.setPrefSize(200.0D, 40.0D);
        user_id.setPromptText("ادخل الباركود الذى التى تريد البحث عنه ");
        user_id.textProperty().addListener((observable) -> {
            try {
                dTable.setItems(database.buildCustomDrugsTable("select * from solditems_forbills_view where barcode='" + user_id.getText() + "';"));
            } catch (Exception var2) {
            }

        });
        userQuantity = new TextField();
        userQuantity.setPrefSize(200.0D, 40.0D);
        userQuantity.setPromptText("ادخل الكميه المرتجعه ");
        searchBill = new Button("بحث");
        searchBill.setPrefSize(200.0D, 40.0D);
        searchBill.setOnAction((event) -> {
            String searchKey1 = "";
            String searchKey2 = "";
            String searchWord1 = billTextSearch1.getText();
            String searchWord2 = billTextSearch2.getText();
            if (beginDate.getValue() == null && endDate.getValue() == null && billTextSearch1.getText().equals("") && billTextSearch2.getText().equals("")) {
                tools.ErrorBox("من فضلك قم باختيار طريقه بحث اولا ");
            }

            if ((beginDate.getValue() != null || endDate.getValue() != null) && billTextSearch1.getText().equals("") && billTextSearch2.getText().equals("")) {
                bTable.setItems(database.buildBillsTable("select * from invoice where  date between '" + ((LocalDate) beginDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' and '" + ((LocalDate) endDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' ;"));
            }

            if (beginDate.getValue() == null && endDate.getValue() == null && !billTextSearch1.getText().equals("") && billTextSearch2.getText().equals("")) {
                searchKey1 = this.getSearchKey(billSearchChooser.getSelectionModel().getSelectedItem().toString());
                bTable.setItems(database.buildBillsTable("select * from invoice where " + searchKey1 + " like '%" + searchWord1 + "%';"));
            }

            if (beginDate.getValue() == null && endDate.getValue() == null && billTextSearch1.getText().equals("") && !billTextSearch2.getText().equals("")) {
                searchKey2 = this.getSearchKey(billSearchChooser2.getSelectionModel().getSelectedItem().toString());
                bTable.setItems(database.buildBillsTable("select * from invoice where " + searchKey2 + " like '%" + searchWord2 + "%';"));
            }

            if (beginDate.getValue() == null && endDate.getValue() == null && !billTextSearch1.getText().equals("") && !billTextSearch2.getText().equals("")) {
                searchKey1 = this.getSearchKey(billSearchChooser.getSelectionModel().getSelectedItem().toString());
                searchKey2 = this.getSearchKey(billSearchChooser2.getSelectionModel().getSelectedItem().toString());
                bTable.setItems(database.buildBillsTable("select * from invoice where " + searchKey1 + " like '%" + searchWord1 + "%' and " + searchKey2 + " like '%" + searchWord2 + "%';"));
            }

            if ((beginDate.getValue() != null || endDate.getValue() != null) && !billTextSearch1.getText().equals("") && billTextSearch2.getText().equals("")) {
                searchKey1 = this.getSearchKey(billSearchChooser.getSelectionModel().getSelectedItem().toString());
                bTable.setItems(database.buildBillsTable("select * from invoice where  date between '" + ((LocalDate) beginDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' and '" + ((LocalDate) endDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' and " + searchKey1 + " like '%" + searchWord1 + "%';"));
            }

            if ((beginDate.getValue() != null || endDate.getValue() != null) && billTextSearch1.getText().equals("") && !billTextSearch2.getText().equals("")) {
                searchKey2 = this.getSearchKey(billSearchChooser2.getSelectionModel().getSelectedItem().toString());
                bTable.setItems(database.buildBillsTable("select * from invoice where  date between '" + ((LocalDate) beginDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' and '" + ((LocalDate) endDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' and " + searchKey2 + " like '%" + searchWord2 + "%';"));
            }

        });
        searchDrug = new Button("بحث");
        searchDrug.setPrefSize(200.0D, 40.0D);
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

        TableColumn<bills, String> paidCoulmn = new TableColumn("المدفوع");
        paidCoulmn.setMinWidth(20.0D);
        paidCoulmn.setCellValueFactory(new PropertyValueFactory("paid"));

        TableColumn<bills, String> reminderCoulmn = new TableColumn("الباقى");
        reminderCoulmn.setMinWidth(20.0D);
        reminderCoulmn.setCellValueFactory(new PropertyValueFactory("remainder"));

        TableColumn<bills, String> productionDateCoulmn = new TableColumn("التاريخ");
        productionDateCoulmn.setMinWidth(25.0D);
        productionDateCoulmn.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<soldItems, Integer> barcodeCustomCoulmn = new TableColumn("باركود");
        barcodeCustomCoulmn.setMinWidth(15.0D);
        barcodeCustomCoulmn.setCellValueFactory(new PropertyValueFactory("barcode"));
        TableColumn<soldItems, String> soldQuantity = new TableColumn("الكميه");
        soldQuantity.setMinWidth(15.0D);
        soldQuantity.setCellValueFactory(new PropertyValueFactory("soldQuantity"));
        TableColumn<soldItems, String> priceCustomCoulmn = new TableColumn("سعر الوحده");
        priceCustomCoulmn.setMinWidth(20.0D);
        priceCustomCoulmn.setCellValueFactory(new PropertyValueFactory("price"));
        TableColumn<soldItems, String> discountCustomCoulmn = new TableColumn("الخصم");
        discountCustomCoulmn.setMinWidth(20.0D);
        discountCustomCoulmn.setCellValueFactory(new PropertyValueFactory("discount"));
        TableColumn<soldItems, String> dateC = new TableColumn("التاريخ");
        dateC.setMinWidth(40.0D);
        dateC.setCellValueFactory(new PropertyValueFactory("date"));
        TableColumn<soldItems, String> idC = new TableColumn("رقم الفاتوره");
        idC.setMinWidth(20.0D);
        idC.setCellValueFactory(new PropertyValueFactory("bill_id"));
        bTable.getColumns().addAll(new TableColumn[]{idColumn, nameColumn, typeColumn, supplierColumn, quantityCoulmn, buyCoulmn, paidCoulmn, reminderCoulmn, productionDateCoulmn});
        dTable.getColumns().addAll(new TableColumn[]{barcodeCustomCoulmn, soldQuantity, discountCustomCoulmn, dateC, priceCustomCoulmn, idC});
        bTable.setOnMouseClicked((event) -> {
            try {
                String bill_id = ((bills) bTable.getSelectionModel().getSelectedItem()).getId() + "";
                this.setItemsTableData(bill_id);
            } catch (Exception var3) {
            }

        });
        dTable.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.F1) {
                    bill.bar = ((soldItems) allBills.dTable.getSelectionModel().getSelectedItem()).getBarcode();

                    try {
                        (new Item_informations()).start(new Stage());
                    } catch (Exception var3) {
                    }
                }

            }
        });
        dTable.setOnMouseClicked((event) -> {
            try {
                drug_bar = ((soldItems) dTable.getSelectionModel().getSelectedItem()).getBarcode();
                String q = "" + ((soldItems) dTable.getSelectionModel().getSelectedItem()).getSoldQuantity();
                userQuantity.setText(q);
            } catch (Exception var2) {
            }

        });
        VBox searchBillBar1 = new VBox(10.0D, new Node[]{beginDate, endDate});
        VBox searchBillBar2 = new VBox(10.0D, new Node[]{billSearchChooser2, billTextSearch2});
        VBox searchBillBar3 = new VBox(10.0D, new Node[]{billSearchChooser, billTextSearch1});
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(1200.0D);
        i.setFitWidth(1400.0D);
        HBox mainSearchBar = new HBox(10.0D, new Node[]{searchBillBar1, searchBillBar2, searchBillBar3});
        VBox text = new VBox(15.0D, new Node[]{mainSearchBar, new HBox(new Node[]{searchBill})});
        text.setPadding(new Insets(20.0D, 50.0D, 0.0D, 50.0D));
        HBox tableBox = new HBox(new Node[]{new VBox(15.0D, new Node[]{text, bTable, this.deleteBill})});
        HBox alternativeTableBox = new HBox(new Node[]{new VBox(20.0D, new Node[]{user_id, dTable, new HBox(20.0D, new Node[]{this.deleteItem, userQuantity})})});
        tableBox.setPadding(new Insets(20.0D, 0.0D, 0.0D, 30.0D));
        alternativeTableBox.setPadding(new Insets(20.0D, 0.0D, 0.0D, 30.0D));
        TitledPane one = new TitledPane("جميع الفواتير ", tableBox);
        TitledPane two = new TitledPane("القطع المباعه", alternativeTableBox);
        HBox t1 = new HBox(new Node[]{one});
        HBox t2 = new HBox(new Node[]{two});
        VBox center = new VBox(25.0D, new Node[]{t1, t2});
        HBox tittle_hbox = new HBox(new Node[]{tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        tittle.setId("tittle");
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center});
        main = new StackPane(new Node[]{i, all});
        root = new HBox(new Node[]{all});
        clear();
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    String getSearchKey(String key) {
        if (key.equals("كود الفاتوره")) {
            return "id";
        } else if (key.equals("السعر ب خ")) {
            return "totalAfterDiscount";
        } else if (key.equals("الخصم")) {
            return "discout";
        } else if (key.equals("اسم العميل")) {
            return "customer_name";
        } else {
            return key.equals("كود الموظف") ? "emp_id" : "";
        }
    }

    void setItemsTableData(String bill_id) {
        try {
            dTable.setItems(database.buildCustomDrugsTable("select * from solditems_forbills_view where invoiceID=" + bill_id));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    void bigUnit(String barcode, String quantity) {
        database.excuteQuery("UPDATE items SET quantity=quantity+" + quantity + " WHERE barcode='" + barcode + "';");
    }

    public static void addMoneyToTop(String p) {
        try {
            Home.priceNo.setText(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleteItem(String discount, String barcode, String quantity, String price, String soldQuantity, String b_id) {
        this.bigUnit(barcode, quantity);
        double q = Double.parseDouble(quantity);
        double d = 0.0D;
        if (discount.equals("")) {
            d = 0.0D;
        } else {
            d = Double.parseDouble(discount);
        }

        double t_q = Double.parseDouble(soldQuantity);
        double sq = Double.parseDouble(quantity);
        double p = Double.parseDouble(price);
        d /= t_q;
        d *= sq;
        if (quantity.equals(soldQuantity)) {
            database.excuteQuery("DELETE FROM solditems where barcode='" + barcode + "' and invoice_id=" + b_id + ";");
        } else {
            database.excuteQuery("UPDATE solditems set soldQuantity=soldQuantity-" + quantity + " where barcode='" + barcode + "' and invoice_id=" + b_id + ";");
        }

        this.setItemsTableData(b_id);
        double top_price = Double.parseDouble(Home.priceNo.getText());
        addMoneyToTop((top_price - p) + "");
    }

    private double setTotalBeforeDiscount(String q, String p, String b_id) {
        double sum = 0.0D;
        DecimalFormat df = new DecimalFormat("####0.00");
        double quantity = Double.parseDouble(q);
        double price = Double.parseDouble(p);
        sum += quantity * price;
        database.excuteQuery("UPDATE invoice set grandTotal=grandTotal-" + df.format(sum) + " where id=" + b_id + ";");
        return sum;
    }

    private void setTotalAfterDiscount(String price, String quantity, String total_quantity, String discount, String b_id) {
        try {
            DecimalFormat df = new DecimalFormat("####0.00");
            double d = 0.0D;
            if (discount.equals("")) {
                d = 0.0D;
            } else {
                d = Double.parseDouble(discount);
            }

            double t_q = Double.parseDouble(total_quantity);
            double q = Double.parseDouble(quantity);
            double p = Double.parseDouble(price);
            d /= t_q;
            d *= q;
            database.excuteQuery("UPDATE invoice set totalAfterDiscount=totalAfterDiscount-" + df.format(q * p - d) + " where id=" + b_id + ";");
            database.excuteQuery("UPDATE invoice set discout=discout-" + df.format(d) + " where id=" + b_id + ";");

            try {
                double clientRem = Double.parseDouble(database.getTableData("select"
                        + " remainder from client_history where invoice_id=" + b_id
                        + ";").items[0][0].toString());
                double clientPaid = Double.parseDouble(database.getTableData("select"
                        + " paid from client_history where invoice_id=" + b_id
                        + ";").items[0][0].toString());

                double totalRem = clientRem - (q * p - d);
                double totalPaid = clientPaid - (q * p - d);

                int choose = Integer.parseInt(tools.inputBox("اذا كنت تريد خصم هذا المبلغ من المبلغ المدفوع اضغط "
                        + "2 واذا كنت تريد خصمه من المبلغ المتبقى عليه اضغط 1").toString());

                if (choose == 1) {

                    if (totalRem <= 0) {

                        tools.InformationBox("لقد تم الخصم بنجاح ولكن متبقى للعميل مبلغ " + Math.abs(totalRem));
                        tools.InformationBox("هذا المبلغ المتبقى سوف ينخصم من المبلغ المدفوع فى الفاتوره");
                        database.excuteQuery("UPDATE client_history set paid=paid-" + Math.abs(totalRem) + " where invoice_id=" + b_id + ";");
                        database.excuteQuery("UPDATE client_history set remainder=0 where invoice_id=" + b_id + ";");
                        database.excuteQuery("UPDATE invoice set paid=paid-" + Math.abs(totalRem) + " where id=" + b_id + ";");
                        database.excuteQuery("UPDATE invoice set remainder=0 where id=" + b_id + ";");

                    } else {

                        database.excuteQuery("UPDATE client_history set remainder=remainder-" + df.format(q * p - d) + " where invoice_id=" + b_id + ";");
                        database.excuteQuery("UPDATE invoice set remainder=remainder-" + df.format(q * p - d) + " where id=" + b_id + ";");

                        tools.InformationBox("تم تخفيض المبلغ المتبقى بنجاح");
                    }

                } else {

                    if (totalPaid <= 0) {

                        tools.InformationBox("لقد تم الخصم بنجاح ولكن متبقى للعميل مبلغ " + Math.abs(totalPaid));
                        tools.InformationBox("هذا المبلغ المتبقى سوف ينخصم من المبلغ المتبقى عليه فى الفاتوره");
                        database.excuteQuery("UPDATE client_history set remainder=remainder-" + Math.abs(totalPaid) + " where invoice_id=" + b_id + ";");
                        database.excuteQuery("UPDATE client_history set paid=0 where invoice_id=" + b_id + ";");
                        database.excuteQuery("UPDATE invoice set remainder=remainder-" + Math.abs(totalPaid) + " where id=" + b_id + ";");
                        database.excuteQuery("UPDATE invoice set paid=0 where id=" + b_id + ";");

                    } else {

                        database.excuteQuery("UPDATE client_history set paid=paid-" + df.format(q * p - d) + " where invoice_id=" + b_id + ";");
                        database.excuteQuery("UPDATE invoice set paid=paid-" + df.format(q * p - d) + " where id=" + b_id + ";");

                        tools.InformationBox("تم تخفيض المبلغ المتبقى بنجاح");
                    }

                }

            } catch (Exception e) {

                database.excuteQuery("UPDATE invoice set paid=paid-" + df.format(q * p - d) + " where id=" + b_id + ";");

            }

        } catch (Exception var15) {
        }

    }

    public void clear() {
        Database.database.excuteQuery("delete FROM invoice where totalAfterDiscount <= 0;");
        Database.database.excuteQuery("delete FROM invoice where remainder<0;");
        Database.database.excuteQuery("delete FROM invoice where paid <0;");
        Database.database.excuteQuery("delete FROM solditems where soldQuantity <= 0;");
    }
}
