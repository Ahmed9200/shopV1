//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.cashTransaction;

import Database.database;
import Designs.Home;
import Tools.tools;
import Tools.tools.table;
import entities.cash.bills;
import entities.cash.client_history;
import entities.cash.soldItems;
import entities.main.customer;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class bill {

    public static HBox root;
    StackPane main;
    Label tittle;
    Label id;
    Label totalBeforeLabel;
    Label discountLabel;
    Label paidLabel;
    Label remainderLabel;
    Label totalAfterLabel;
    Label numberLabel;
    Label totalBefore;
    static Label totalAfter;
    Label number;
    public static ComboBox customer;
    public static TextField discount;
    public static TextField paid;
    public static TextField remainder;
    public static TextField searchCustomerWithPhone;
    Button customerLabel;
    Button add;
    Button print;
    Button clear;
    Button delete;
    public TableView<bill.billTable> table = new TableView();
    bills b = new bills();
    entities.cash.client_history ch = new client_history();
    soldItems si = new soldItems();
    public static ObservableList<bill.billTable> data = FXCollections.observableArrayList((Data) -> {
        return new Observable[]{Data.barcodeProperty(), Data.nameProperty(), Data.quantityProperty(), Data.discountProperty(), Data.priceADProperty()};
    });
    public static String bar;
    public static DatePicker date;
    public static TimeSpinner time;

    public bill() {
        time = new TimeSpinner(LocalTime.MIN);
        date = new DatePicker(LocalDate.MAX);
        this.tittle = new Label("نقطه البيع");
        this.id = new Label();
        this.id.setText(this.b.getAutoNumber());
        this.id.setStyle("-fx-text-fill:#2F4F4F;");
        this.customerLabel = new Button("+");
        this.customerLabel.setOnAction((event) -> {
            try {
                customer c = new customer();
                String name = tools.inputBox("ادخل اسم العميل").toString();
                String phone = tools.inputBox("ادخل رقم الهاتف").toString();
                String address = tools.inputBox("ادخل عنوان العميل").toString();
                String notes = tools.inputBox("ملاحظات").toString();
                c.setCustomer_id(Integer.parseInt(c.getAutoNumber()));
                c.setCustomer_name(name);
                c.setCustomer_phone(phone);
                c.setCustomer_notes(notes);
                c.setCustomer_address(address);
                c.add();
                database.fillComboBoxData("customer", "name", "", customer);
            } catch (Exception var6) {
            }

        });
        this.totalBeforeLabel = new Label("السعر ق خ");
        this.totalBefore = new Label();
        this.totalBefore.setText("0.0");
        this.totalBefore.setStyle("-fx-text-fill: red;");
        this.discountLabel = new Label("الخصم");
        this.paidLabel = new Label("المدفوع");
        this.remainderLabel = new Label("الباقى");
        this.totalAfterLabel = new Label("السعر ب خ");
        this.totalAfter = new Label();
        this.totalAfter.setText("0.0");
        this.totalAfter.setStyle("-fx-text-fill: blue;");
        this.numberLabel = new Label("العدد");
        this.number = new Label();
        this.number.setStyle("-fx-text-fill: #8B0000;");
        discount = new TextField();
        discount.setPrefSize(20.0D, 30.0D);
        discount.setStyle("-fx-text-inner-color: red;");
        discount.setText("0");
        discount.textProperty().addListener((observable, oldValue, newValue) -> {
            this.setTotalAfterDescount();
            double paid_price = Double.parseDouble(paid.getText());
            double total_price = Double.parseDouble(totalAfter.getText());
            remainder.setText((total_price - paid_price) + "");
        });
        paid = new TextField();
        paid.setPrefSize(20.0D, 30.0D);
        paid.setStyle("-fx-text-inner-color: blue;");
        paid.setText("0");
        paid.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double paid_price = Double.parseDouble(paid.getText());
                double total_price = Double.parseDouble(totalAfter.getText());
                remainder.setText((total_price - paid_price) + "");

            } catch (Exception e) {
            }
        });
        remainder = new TextField();
        remainder.setPrefSize(20.0D, 30.0D);
        remainder.setStyle("-fx-text-inner-color: red;");
        remainder.setText("0");

        searchCustomerWithPhone = new TextField();
        searchCustomerWithPhone.setPromptText("ادخل الهاتف للبحث");
        searchCustomerWithPhone.textProperty().addListener((observable) -> {
            customer.getItems().clear();

            try {
                String search_key = searchCustomerWithPhone.getText();
                database.fillComboBoxData("customer", "name", " where phone like '%" + search_key + "%'", customer);
                customer.getSelectionModel().select(0);
            } catch (Exception var2) {
            }

        });
        customer = new ComboBox();
        customer.setPromptText("ادخل اسم العميل");
        database.fillComboBoxData("customer", "name", "", customer);
        this.add = new Button("حفظ", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            try {

                if (Double.parseDouble(paid.getText()) == 0 && Double.parseDouble(remainder.getText()) != 0) {

                    Tools.tools.ErrorBox("برجاء ادخال المبلغ المدفوع!");

                } else {
                    this.setBillValues();
                    this.b.add();
                    ch.add();
                    this.addSoldItems();
                    this.updateDrugQuantity();
                    this.addBillToCurrentDoctor();
                    this.updateEmp_BillsNumber();
                    if (tools.confirmMsg("هل تريد طباعه الفاتوره")) {
                        this.print();
                    }
                    addMoneyToTop();
                    this.clear();
                    discount.setText("0.0");
                }

            } catch (Exception e) {
            }
        });
        this.print = new Button("طباعه الفاتوره", new ImageView("images/printer.png"));
        this.print.setOnAction((event) -> {
            this.print();
        });
        this.clear = new Button("تنظيف", new ImageView("images/mop.png"));
        this.clear.setOnAction((event) -> {
            this.clear();
        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            this.removeSelectedRows();
            this.setTotalBeforeDiscount();
            this.setTotalAfterDescount();
        });
        data.addAll(new bill.billTable[]{new bill.billTable()});
        this.table.setEditable(true);
        this.table.setPrefSize(900.0D, 300.0D);
        this.table.setItems(data);
        this.table.getColumns().add(this.column("الباركود", bill.billTable::barcodeProperty));
        ((TableColumn) this.table.getColumns().get(0)).setMinWidth(150.0D);
        ((TableColumn) this.table.getColumns().get(0)).setStyle("-fx-text-inner-color: blue;");
        this.table.getColumns().add(this.column("الاسم", bill.billTable::nameProperty));
        ((TableColumn) this.table.getColumns().get(1)).setMinWidth(150.0D);
        ((TableColumn) this.table.getColumns().get(1)).setEditable(false);
        this.table.getColumns().add(this.column("الكميه", bill.billTable::quantityProperty));
        ((TableColumn) this.table.getColumns().get(2)).setMinWidth(100.0D);
        this.table.getColumns().add(this.column("الباقى", bill.billTable::stockProperty));
        ((TableColumn) this.table.getColumns().get(3)).setMinWidth(100.0D);
        ((TableColumn) this.table.getColumns().get(3)).setEditable(false);
        this.table.getColumns().add(this.column("سعر القطعه", bill.billTable::priceProperty));
        ((TableColumn) this.table.getColumns().get(4)).setMinWidth(100.0D);
        this.table.getColumns().add(this.column("الخصم", bill.billTable::discountProperty));
        ((TableColumn) this.table.getColumns().get(5)).setMinWidth(100.0D);
        this.table.getColumns().add(this.column("السعر بعد الخصم", bill.billTable::priceADProperty));
        ((TableColumn) this.table.getColumns().get(6)).setMinWidth(160.0D);
        ((TableColumn) this.table.getColumns().get(6)).setEditable(false);
        this.table.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() != KeyCode.ENTER) {
                if (event.getCode() == KeyCode.F1) {
                    bar = ((bill.billTable) this.table.getSelectionModel().getSelectedItem()).getBarcode();

                    try {
                        (new Item_informations()).start(new Stage());
                    } catch (Exception var3) {
                    }
                }

                if (this.table.getEditingCell() == null && (event.getCode().isLetterKey() || event.getCode().isDigitKey())) {
                    TablePosition focusedCellPosition = this.table.getFocusModel().getFocusedCell();
                    this.table.edit(focusedCellPosition.getRow(), focusedCellPosition.getTableColumn());
                }

            }
        });
        this.table.addEventFilter(KeyEvent.KEY_RELEASED, (event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                TablePosition pos = this.table.getFocusModel().getFocusedCell();
                if (pos.getRow() == -1) {
                    this.table.getSelectionModel().select(0);
                } else if (pos.getRow() == this.table.getItems().size() - 1) {
                    this.addRow();
                } else if (pos.getRow() < this.table.getItems().size() - 1) {
                    this.table.getSelectionModel().clearAndSelect(pos.getRow() + 1, pos.getTableColumn());
                }
            }

        });
        this.table.getSelectionModel().setCellSelectionEnabled(true);
        TableColumn<bill.billTable, bill.billTable> indexCol = new TableColumn("#");
        indexCol.setCellFactory((param) -> {
            return new TableCell<bill.billTable, bill.billTable>() {
                protected void updateItem(bill.billTable item, boolean empty) {
                    super.updateItem(item, empty);
                    if (this.getTableRow() != null) {
                        int index = this.getTableRow().getIndex();
                        if (index < bill.this.table.getItems().size()) {
                            int rowNum = index + 1;
                            this.setText(String.valueOf(rowNum));
                        } else {
                            this.setText("");
                        }
                    } else {
                        this.setText("");
                    }

                }
            };
        });
        this.table.getColumns().add(0, indexCol);
        this.table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedPerson, newSelectedPerson) -> {
            try {
                this.getBillTableData(oldSelectedPerson);
                this.setTotalBeforeDiscount();
                this.setTotalAfterDescount();
                this.setItemPriceAD();
            } catch (Exception var5) {
            }

        });
        ((TableColumn) this.table.getColumns().get(5)).setOnEditCommit((event) -> {
            this.setItemPriceAD();
        });
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(1200.0D);
        i.setFitWidth(1600.0D);
        HBox one = new HBox(10.0D, new Node[]{new HBox(10.0D, new Node[]{searchCustomerWithPhone}), new HBox(10.0D, new Node[]{customer, this.customerLabel})});
        one.setAlignment(Pos.CENTER);
        new HBox(10.0D, new Node[]{date, time});
        HBox two = new HBox(10, this.table);
        HBox three = new HBox(10,
                new HBox(15, this.id, new VBox(10.0D, this.totalAfterLabel, this.totalAfter),
                        new VBox(10, this.discountLabel, discount),
                        new VBox(10, this.paidLabel, this.paid),
                        new VBox(10, this.totalBeforeLabel, this.totalBefore),
                        new VBox(10, this.remainderLabel, this.remainder)));
        three.setAlignment(Pos.CENTER);
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three});
        one.setId("lbl_txt_box");
        three.setId("lbl_txt_box");
        allText_andLabels.setAlignment(Pos.CENTER);
        HBox ButtonsRow = new HBox(10.0D, new Node[]{this.add});
        ButtonsRow.setId("three_btn");
        HBox allButtons = new HBox(10.0D, new Node[]{ButtonsRow, new HBox(10.0D, new Node[]{this.print, this.clear, this.delete})});
        allButtons.setId("btnbox");
        allButtons.setAlignment(Pos.CENTER);
        VBox left = new VBox(20.0D, new Node[]{allText_andLabels, allButtons});
        left.setPadding(new Insets(80.0D, 0.0D, 0.0D, 0.0D));
        HBox center = new HBox(30.0D, new Node[]{left});
        center.setId("billRoot");
        HBox tittle_hbox = new HBox(new Node[]{this.tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        this.tittle.setId("tittle");
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center});
        this.main = new StackPane(new Node[]{i, all});
        root = new HBox(new Node[]{all});
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/cash.css").toExternalForm());
        root.setAlignment(Pos.CENTER);
    }

    private void updateDrugQuantity() {
        try {
            for (int i = 0; i < data.size(); ++i) {
                String barcode = ((bill.billTable) data.get(i)).getBarcode();
                String quantity = ((bill.billTable) data.get(i)).getQuantity();
                database.excuteQuery("UPDATE items SET quantity=quantity-" + quantity + " WHERE barcode='" + barcode + "';");
            }
        } catch (Exception var4) {
        }

    }

    private void updateEmp_BillsNumber() {

    }

    private void getBillTableData(bill.billTable bd) {
        try {
            String code = bd.getBarcode();
            table t = database.getTableData("select * from items where barcode ='" + code + "'");
            bd.setName(t.items[0][1].toString());
            bd.setPrice(t.items[0][6].toString());
            bd.setStock(t.items[0][7].toString());
            if (t.items[0][4].toString().equals("0")) {
                tools.WarningBox("لا يوجد كميه متاحه للبيع!!!!!!");
            }

            bd.setBarcode(code);
            if (bd.getQuantity().equals("") || bd.getQuantity() == null) {
                bd.setQuantity("1");
            }

            if (!this.checkQuantity(Integer.parseInt(bd.getStock()), Integer.parseInt(bd.getQuantity()))) {
                tools.WarningBox("خطأ فى الكميه!!!");
            }

            double p;
            double q;
            double np;
            if (bd.getDiscount().equals("")) {
                bd.setDiscount("0.0");
                p = Double.parseDouble(bd.getPrice());
                q = Double.parseDouble(bd.getQuantity());
                np = p * q;
                bd.setPriceAD(np + "");
            } else {
                p = Double.parseDouble(bd.getPrice());
                q = Double.parseDouble(bd.getQuantity());
                np = Double.parseDouble(bd.getDiscount());
                np = p * q - np;
                bd.setPriceAD(np + "");
            }
        } catch (Exception var12) {
        }

    }

    private boolean checkQuantity(int stockQuantity, int userQuantity) {
        if (userQuantity > stockQuantity) {
            return false;
        } else {
            return userQuantity <= stockQuantity;
        }
    }

    private void setTotalBeforeDiscount() {
        double sum = 0.0D;
        DecimalFormat df = new DecimalFormat("####0.00");

        for (int i = 0; i < data.size(); ++i) {
            try {
                double quantity = Double.parseDouble(((bill.billTable) data.get(i)).getQuantity());
                double price = Double.parseDouble(((bill.billTable) data.get(i)).getPrice());
                sum += quantity * price;
            } catch (Exception var10) {
                double quantity = 0.0D;
                double price = 0.0D;
                sum += quantity * price;
            }
        }

        this.totalBefore.setText(df.format(sum));
    }

    private void setTotalAfterDescount() {
        try {
            DecimalFormat df = new DecimalFormat("####0.00");
            double d = Double.parseDouble(discount.getText());
            double tbd = Double.parseDouble(this.totalBefore.getText());
            this.totalAfter.setText(df.format(tbd - d));
        } catch (Exception var6) {
        }

    }

    private void clear() {
        try {
            this.id.setText(this.b.getAutoNumber());
            customer.getSelectionModel().select((Object) null);
            searchCustomerWithPhone.setText((String) null);
            discount.setText("0.0");
            this.totalAfter.setText("0.0");
            this.number.setText("0");
            this.totalBefore.setText("0.0");
            this.remainder.setText("0.0");
            this.paid.setText("0.0");
            this.table.getItems().remove(1, data.size());
            this.table.getItems().set(0, new bill.billTable());
        } catch (Exception var2) {
        }

    }

    private void setBillValues() {
        try {
            this.b.setId(Integer.parseInt(this.id.getText()));
            this.b.setDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "");
            this.b.setDiscount(Double.parseDouble(discount.getText()));
            this.b.setGrandTotal(Double.parseDouble(this.totalBefore.getText()));
            this.b.setTotalAfterDiscount(Double.parseDouble(this.totalAfter.getText()));
            this.b.setPaid(Double.parseDouble(this.paid.getText()));
            this.b.setRemainder(Double.parseDouble(this.remainder.getText()));
            this.b.setCustomer_name(customer.getSelectionModel().getSelectedItem().toString());

            double r = Double.parseDouble(remainder.getText());

            ch.setId(Integer.parseInt(ch.getAutoNumber()));
            ch.setInvoice_id(Integer.parseInt(id.getText()));
            ch.setPaid(Double.parseDouble(this.paid.getText()));
            ch.setRemainder(Double.parseDouble(this.remainder.getText()));
            ch.setDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "");
            String customer_name = customer.getSelectionModel().getSelectedItem().toString();
            String customer_phone = searchCustomerWithPhone.getText();

            String customer_id = Database.database.getTableData("select id from customer where name='" + customer_name + "' and phone ='" + customer_phone + "'").items[0][0].toString();
            ch.setCustomer_id(Integer.parseInt(customer_id));

        } catch (Exception var3) {
        }

    }

    private void addSoldItems() {
        try {
            for (int i = 0; i < data.size(); ++i) {
                this.si.setId(Integer.parseInt(this.si.getAutoNumber()));
                this.si.setBarcode(((bill.billTable) data.get(i)).getBarcode());
                this.si.setBill_id(Integer.parseInt(this.id.getText()));
                this.si.setSoldQuantity(Integer.parseInt(((bill.billTable) data.get(i)).getQuantity()));
                this.si.setDiscount(((bill.billTable) data.get(i)).getDiscount());
                this.si.setPrice("" + ((bill.billTable) data.get(i)).getPrice());
                this.si.setPriceAD("" + (Double.parseDouble(((bill.billTable) data.get(i)).getPrice()) - Double.parseDouble(((bill.billTable) data.get(i)).getDiscount())));
                this.si.add();
            }
        } catch (Exception var2) {
        }

    }

    private TableColumn<bill.billTable, String> column(String title, Function<bill.billTable, ObservableValue<String>> property) {
        TableColumn<bill.billTable, String> col = new TableColumn(title);
        col.setCellValueFactory((cellData) -> {
            return (ObservableValue) property.apply(cellData.getValue());
        });
        col.setCellFactory(TextFieldTableCell.forTableColumn());
        return col;
    }

    public void addRow() {
        TablePosition pos = this.table.getFocusModel().getFocusedCell();
        this.table.getSelectionModel().clearSelection();
        bill.billTable billdata = new bill.billTable();
        this.table.getItems().add(billdata);
        int row = this.table.getItems().size() - 1;
        this.table.getSelectionModel().select(row, pos.getTableColumn());
        this.table.scrollTo(billdata);
        this.number.setText(data.size() + "");
    }

    public void addBillToCurrentDoctor() {

    }

    public static void addMoneyToTop() {
        try {
            double price = Double.parseDouble(Home.priceNo.getText());

            Home.priceNo.setText((price + Double.parseDouble(totalAfter.getText())) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeSelectedRows() {
        this.table.getItems().removeAll(this.table.getSelectionModel().getSelectedItems());
        this.table.getSelectionModel().clearSelection();
        this.number.setText(data.size() + "");
    }

    void print() {
        try {
            new bill.billTable();
            List<Map<String, ?>> dataSource = new ArrayList();
            Iterator var3 = this.table.getItems().iterator();

            while (var3.hasNext()) {
                bill.billTable it = (bill.billTable) var3.next();
                Map<String, Object> m = new HashMap();
                m.put("name", it.getName());
                m.put("quantity", it.getQuantity());
                m.put("price", it.getPrice() + " LE");
                if (!it.getQuantity().equals("")) {
                    dataSource.add(m);
                }
            }

            HashMap parameter = new HashMap();
            parameter.put("id", "INV00" + this.id.getText());
            parameter.put("totalPaid", this.totalBefore.getText() + " LE");
            parameter.put("discount", discount.getText() + " LE");
            parameter.put("priceAfterDiscount", this.totalAfter.getText() + " LE");
            parameter.put("customerName", searchCustomerWithPhone.getText());
            parameter.put("cashairName", "eslam");
            JRDataSource dataSource1 = new JRBeanCollectionDataSource(dataSource);
            File file = new File("mainDataReports\\invoice.jrxml");
            String absolutePath = file.getAbsolutePath();
            JasperDesign jd = JRXmlLoader.load(absolutePath);
            JasperReport jp = JasperCompileManager.compileReport(jd);
            JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            JasperPrint printt = JasperFillManager.fillReport(jp, parameter, dataSource1);
            JasperPrintManager.printReport(printt, false);
        } catch (JRException var10) {

        }

    }

    private void setItemPriceAD() {
        try {
            double totalDis = 0.0D;

            try {
                for (int i = 0; i < this.table.getItems().size(); ++i) {
                    if (((bill.billTable) this.table.getItems().get(i)).getDiscount().equals("")) {
                        ++i;
                    } else {
                        totalDis += Double.parseDouble(((bill.billTable) this.table.getItems().get(i)).getDiscount());
                    }
                }
            } catch (Exception var4) {
            }

            discount.setText(totalDis + "");
        } catch (Exception var5) {
        }

    }

    public static class billTable {

        private final StringProperty barcode = new SimpleStringProperty();
        private final StringProperty name = new SimpleStringProperty();
        private final StringProperty quantity = new SimpleStringProperty();
        private final StringProperty stock = new SimpleStringProperty();
        private final StringProperty price = new SimpleStringProperty();
        private final StringProperty priceAD = new SimpleStringProperty();
        private final StringProperty discount = new SimpleStringProperty();

        public billTable() {
            this.barcode.set("");
            this.name.set("");
            this.quantity.set("");
            this.stock.set("");
            this.price.set("");
            this.priceAD.set("");
            this.discount.set("");
        }

        public final StringProperty barcodeProperty() {
            return this.barcode;
        }

        public final StringProperty nameProperty() {
            return this.name;
        }

        public final StringProperty quantityProperty() {
            return this.quantity;
        }

        public final StringProperty discountProperty() {
            return this.discount;
        }

        public final String getBarcode() {
            return (String) this.barcodeProperty().get();
        }

        public final void setBarcode(String code) {
            this.barcodeProperty().set(code);
        }

        public final String getName() {
            return (String) this.nameProperty().get();
        }

        public final void setName(String Name) {
            this.nameProperty().set(Name);
        }

        public final String getQuantity() {
            return (String) this.quantityProperty().get();
        }

        public final void setQuantity(String q) {
            this.quantityProperty().set(q);
        }

        public final String getDiscount() {
            return (String) this.discountProperty().get();
        }

        public final void setDiscount(String d) {
            this.discountProperty().set(d);
        }

        public final StringProperty stockProperty() {
            return this.stock;
        }

        public final String getStock() {
            return (String) this.stockProperty().get();
        }

        public final void setStock(String s) {
            this.stockProperty().set(s);
        }

        public final StringProperty priceProperty() {
            return this.price;
        }

        public final StringProperty priceADProperty() {
            return this.priceAD;
        }

        public final String getPrice() {
            return (String) this.priceProperty().get();
        }

        public final void setPrice(String p) {
            this.priceProperty().set(p);
        }

        public final String getPriceAD() {
            return (String) this.priceADProperty().get();
        }

        public final void setPriceAD(String ad) {
            this.priceADProperty().set(ad);
        }

        public String toString() {
            return this.getBarcode() + " " + this.getName() + " (" + this.getQuantity() + ") " + this.getPrice() + " " + this.getStock() + " ";
        }
    }
}
