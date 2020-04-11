//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.cashTransaction;

import Database.database;
import Tools.tools;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class installment {

    public static HBox root;
    StackPane main;
    Label tittle = new Label("متابعه التقسيطات");
    Label idLabel = new Label("الكود");
    Label client_idLabel = new Label("كود العميل");
    Label client_nameLabel = new Label("اسم العميل");
    Label invoice_idLabel = new Label("كود الفاتوره");
    Label paidLabel = new Label("المدفوع");
    Label remainderLabel = new Label("الباقى");
    Label dateLabel = new Label("التاريخ");
    Label notesLabel = new Label("ملاحظات");
    public static TextField id;
    public static TextField paid;
    public static TextField client_id;
    public static TextField invoice_id;
    public static TextField remainder;
    public static TextField customer_name;
    public static TextArea notes;
    public static DatePicker date;
    public static TextField search;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<entities.cash.client_history> table;
    entities.cash.client_history ch = new entities.cash.client_history();

    public installment() {
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.ch.getAutoNumber());

        client_id = new TextField();
        client_id.setPromptText("كود العميل");
        client_id.textProperty().addListener((observable) -> {

            try {
                String c_name = Database.database.getTableData("select name from customer where id=" + client_id.getText()).items[0][0].toString();
                customer_name.setText(c_name);
            } catch (Exception e) {
            }
        });

        invoice_id = new TextField();
        invoice_id.setPromptText("كود الفاتوره");

        invoice_id.textProperty().addListener((observable) -> {

            try {
                String rem = Database.database.getTableData("select remainder from invoice where id=" + invoice_id.getText()).items[0][0].toString();
                remainder.setText(rem);
            } catch (Exception e) {
            }
        });

        remainder = new TextField();
        remainder.setDisable(true);
        remainder.setPromptText("الباقى");

        customer_name = new TextField();
        customer_name.setDisable(true);
        customer_name.setPromptText("اسم العميل");

        paid = new TextField();
        paid.setPromptText("المبلغ المدفوع");

        search = new TextField();
        search.setPromptText("ادخل كود العميل للبحث");
        date = new DatePicker();
        date.setPromptText("ادخل التاريخ");
        notes = new TextArea();
        notes.setPromptText("ادخل الملاحظات");
        this.add = new Button("اضافه", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            if (this.check()) {
                this.setValues();
                editInvoiceData_add();
                this.ch.add();
                this.clear();
            } else {
                tools.ErrorBox("من فضلك ادخل البيانات كامله!");
            }

        });
        this.update = new Button("تعديل", new ImageView("images/refresh.png"));
        this.update.setOnAction((event) -> {
            this.setValues();
            this.ch.update();
            this.clear();
        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            if (tools.confirmMsg("هل انت متأكد من مسح الايراد ؟")) {
                this.setValues();
                editInvoiceData_delete();
                this.ch.delete();
                this.clear();
            }

        });
        this.update.setDisable(true);
        this.delete.setDisable(true);
        this.add.setDisable(false);
        this.clear = new Button("مسح", new ImageView("images/mop.png"));
        this.clear.setPrefWidth(180.0D);
        this.clear.setOnAction((event) -> {
            this.clear();
        });
        table = new TableView();
        table.setItems(database.buildClientHistoryTable("select * from client_history"));
        table.setPrefSize(700.0D, 700.0D);
        table.setId("table");
        TableColumn<entities.cash.client_history, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(30.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn<entities.cash.client_history, String> priceCoulmn = new TableColumn("السعر المدفوع");
        priceCoulmn.setMinWidth(60.0D);
        priceCoulmn.setCellValueFactory(new PropertyValueFactory("paid"));

        TableColumn<entities.cash.client_history, String> notesCoulmn = new TableColumn("ملاحظات");
        notesCoulmn.setMinWidth(30.0D);
        notesCoulmn.setCellValueFactory(new PropertyValueFactory("notes"));

        TableColumn<entities.cash.client_history, String> dateCoulmn = new TableColumn("التاريخ");
        dateCoulmn.setMinWidth(40.0D);
        dateCoulmn.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<entities.cash.client_history, String> remainderCoulmn = new TableColumn("الباقى");
        remainderCoulmn.setMinWidth(30.0D);
        remainderCoulmn.setCellValueFactory(new PropertyValueFactory("remainder"));

        TableColumn<entities.cash.client_history, String> client_idCoulmn = new TableColumn("كود العميل");
        client_idCoulmn.setMinWidth(60.0D);
        client_idCoulmn.setCellValueFactory(new PropertyValueFactory("customer_id"));

        TableColumn<entities.cash.client_history, String> invoice_idCoulmn = new TableColumn("كود الفاتوره");
        invoice_idCoulmn.setMinWidth(60.0D);
        invoice_idCoulmn.setCellValueFactory(new PropertyValueFactory("invoice_id"));

        table.getColumns().addAll(new TableColumn[]{idColumn, client_idCoulmn, invoice_idCoulmn, priceCoulmn, remainderCoulmn, notesCoulmn, dateCoulmn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText((table.getSelectionModel().getSelectedItem()).getId() + "");
                invoice_id.setText((table.getSelectionModel().getSelectedItem()).getInvoice_id() + "");
                String c_id = table.getSelectionModel().getSelectedItem().getCustomer_id() + "";
                client_id.setText(c_id);
                String c_name = Database.database.getTableData("select name from customer where id=" + c_id).items[0][0].toString();
                customer_name.setText(c_name);
                notes.setText((table.getSelectionModel().getSelectedItem()).getNotes());
                double p = table.getSelectionModel().getSelectedItem().getPaid();
                double r = table.getSelectionModel().getSelectedItem().getRemainder();

                paid.setText(p + "");
                remainder.setText((r) + "");
                String d = table.getSelectionModel().getSelectedItem().getDate();
                d = d.substring(0, 10);
                date.setValue(LOCAL_DATE(d));

                this.update.setDisable(false);
                this.delete.setDisable(false);
                this.add.setDisable(true);
            } catch (Exception var3) {
                tools.ErrorBox("حدث خطأ ما اثناء تكوين البيانات من الجدول الى الصفحه ");
            }

        });
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(1200.0D);
        i.setFitWidth(1600.0D);
        HBox one = new HBox(10.0D, new Node[]{id, this.idLabel});
        HBox two = new HBox(10.0D, new Node[]{client_id, this.client_idLabel});
        HBox three = new HBox(10.0D, new Node[]{customer_name, this.client_nameLabel});
        HBox four = new HBox(10.0D, new Node[]{invoice_id, this.invoice_idLabel});
        HBox five = new HBox(10.0D, new Node[]{paid, this.paidLabel});
        HBox six = new HBox(10.0D, new Node[]{remainder, this.remainderLabel});
        HBox seven = new HBox(10.0D, new Node[]{notes, this.notesLabel});
        HBox eight = new HBox(10.0D, new Node[]{date, this.dateLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three, four, five, six, seven, eight});
        allText_andLabels.setId("lbl_txt_box");
        HBox ButtonsRow = new HBox(10.0D, new Node[]{this.add, this.delete});
        ButtonsRow.setId("three_btn");
        VBox allButtons = new VBox(0.0D, new Node[]{ButtonsRow, new HBox(0.0D, new Node[]{this.clear})});
        allButtons.setId("btnbox");
        VBox left = new VBox(20.0D, new Node[]{allText_andLabels, allButtons});
        left.setPadding(new Insets(80.0D, 0.0D, 0.0D, 0.0D));
        ImageView s = new ImageView("images/search.png");
        s.setOnMouseClicked((event) -> {
            table.setItems(database.buildClientHistoryTable("select * from client_history where customer_id =" + search.getText() + ";"));
        });
        HBox searchRow = new HBox(5.0D, new Node[]{search, s});
        searchRow.setPadding(new Insets(0.0D, 20.0D, 0.0D, 121.0D));
        HBox tableRow = new HBox(new Node[]{table});
        VBox right = new VBox(5.0D, new Node[]{searchRow, tableRow});
        HBox center = new HBox(30.0D, new Node[]{left, right});
        center.setId("root");
        HBox tittle_hbox = new HBox(new Node[]{this.tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        this.tittle.setId("tittle");
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center});
        this.main = new StackPane(new Node[]{i, all});
        root = new HBox(new Node[]{all});
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    void setValues() {
        try {
            this.ch.setId(Integer.parseInt(id.getText()));
            this.ch.setInvoice_id(Integer.parseInt(invoice_id.getText()));
            this.ch.setCustomer_id(Integer.parseInt(client_id.getText()));
            this.ch.setDate(((LocalDate) date.getValue()).toString());
            this.ch.setNotes(notes.getText());
            this.ch.setPaid(Double.parseDouble(paid.getText()));
            this.ch.setRemainder(Double.parseDouble(remainder.getText()));
        } catch (Exception var2) {
            tools.InformationBox("حدث خطأ ما اثناء الاضافه ");
        }

    }

    void editInvoiceData_add() {

        Database.database.excuteQuery("update invoice set paid=paid+" + paid.getText() + " , remainder=remainder-" + paid.getText()
                + " where id=" + invoice_id.getText());

    }

    void editInvoiceData_delete() {

        Database.database.excuteQuery("update invoice set paid=paid-" + paid.getText() + " , remainder=remainder+" + paid.getText()
                + " where id=" + invoice_id.getText());

    }

    void clear() {
        search.setText("");
        id.setText(this.ch.getAutoNumber());
        table.setItems(database.buildClientHistoryTable("select * from client_history"));
        paid.setText("");
        notes.setText("");
        invoice_id.setText("");
        remainder.setText("");
        customer_name.setText("");
        client_id.setText("");
        date.setValue(null);
        this.update.setDisable(true);
        this.delete.setDisable(true);
        this.add.setDisable(false);
    }

    boolean check() {
        return !id.getText().equals("") && !paid.getText().equals("") && date.getValue() != null && !notes.getText().equals("");
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
