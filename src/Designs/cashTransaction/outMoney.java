//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.cashTransaction;

import Database.database;
import Tools.tools;
import entities.cash.outmoney;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

public class outMoney {

    public static HBox root;
    StackPane main;
    Label tittle = new Label("المصروفات");
    Label idLabel = new Label("الكود");
    Label priceLabel = new Label("السعر");
    Label dateLabel = new Label("التاريخ");
    Label notesLabel = new Label("ملاحظات");
    public static TextField id;
    public static TextField price;
    public static TextArea notes;
    public static DatePicker date;
    public static DatePicker search;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<outmoney> table;
    outmoney in = new outmoney();
    public static double number = 0.0D;

    public outMoney() {
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.in.getAutoNumber());
        price = new TextField();
        price.setPromptText("المبلغ");
        search = new DatePicker();
        search.setPromptText("ادخل التاريخ للبحث");
        date = new DatePicker();
        date.setPromptText("ادخل التاريخ");
        notes = new TextArea();
        notes.setPromptText("ادخل الملاحظات");
        this.add = new Button("اضافه", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            if (this.check()) {
                this.setValues();
                this.in.add();
                this.clear();
            } else {
                tools.ErrorBox("من فضلك ادخل البيانات كامله!");
            }

        });
        this.update = new Button("تعديل", new ImageView("images/refresh.png"));
        this.update.setOnAction((event) -> {
            this.setValues();
            this.in.update();
            this.clear();
        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            if (tools.confirmMsg("هل انت متأكد من مسح المبلغ ؟")) {
                this.setValues();
                this.in.delete();
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
        table.setItems(database.buildOutMoneyTable("select * from outmoney"));
        table.setPrefSize(500.0D, 500.0D);
        TableColumn<outmoney, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(20.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("out_id"));
        TableColumn<outmoney, String> priceCoulmn = new TableColumn("السعر");
        priceCoulmn.setMinWidth(30.0D);
        priceCoulmn.setCellValueFactory(new PropertyValueFactory("out_price"));
        TableColumn<outmoney, String> notesCoulmn = new TableColumn("ملاحظات");
        notesCoulmn.setMinWidth(30.0D);
        notesCoulmn.setCellValueFactory(new PropertyValueFactory("out_notes"));
        TableColumn<outmoney, String> dateCoulmn = new TableColumn("التاريخ");
        dateCoulmn.setMinWidth(30.0D);
        dateCoulmn.setCellValueFactory(new PropertyValueFactory("out_date"));
        table.getColumns().addAll(new TableColumn[]{idColumn, priceCoulmn, notesCoulmn, dateCoulmn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((outmoney) table.getSelectionModel().getSelectedItem()).getOut_id() + "");
                notes.setText(((outmoney) table.getSelectionModel().getSelectedItem()).getOut_notes());
                price.setText(((outmoney) table.getSelectionModel().getSelectedItem()).getOut_price());
                date.setValue(LOCAL_DATE(((outmoney) table.getSelectionModel().getSelectedItem()).getOut_date().substring(0, 10)));
                number = Double.parseDouble(((outmoney) table.getSelectionModel().getSelectedItem()).getOut_price());
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
        HBox two = new HBox(10.0D, new Node[]{price, this.priceLabel});
        HBox three = new HBox(10.0D, new Node[]{notes, this.notesLabel});
        HBox four = new HBox(10.0D, new Node[]{date, this.dateLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three, four});
        allText_andLabels.setId("lbl_txt_box");
        HBox ButtonsRow = new HBox(10.0D, new Node[]{this.add, this.update, this.delete});
        ButtonsRow.setId("three_btn");
        VBox allButtons = new VBox(0.0D, new Node[]{ButtonsRow, new HBox(0.0D, new Node[]{this.clear})});
        allButtons.setId("btnbox");
        VBox left = new VBox(20.0D, new Node[]{allText_andLabels, allButtons});
        left.setPadding(new Insets(80.0D, 0.0D, 0.0D, 0.0D));
        ImageView s = new ImageView("images/search.png");
        s.setOnMouseClicked((event) -> {
            table.setItems(database.buildOutMoneyTable("select * from outmoney where date between '" + search.getValue() + " 00:00:00' and '" + search.getValue() + " 23:59:59';"));
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
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date d = new Date();
            this.in.setOut_id(Integer.parseInt(id.getText()));
            this.in.setOut_date(((LocalDate) date.getValue()).toString() + " " + sdf.format(d));
            this.in.setOut_notes(notes.getText());
            this.in.setOut_price(price.getText());
        } catch (Exception var2) {
            tools.InformationBox("حدث خطأ ما اثناء الاضافه ");
        }

    }

    void clear() {
        search.setValue(null);
        id.setText(this.in.getAutoNumber());
        table.setItems(database.buildOutMoneyTable("select * from outmoney"));
        price.setText("");
        notes.setText("");
        date.setValue(null);
        this.update.setDisable(true);
        this.delete.setDisable(true);
        this.add.setDisable(false);
    }

    boolean check() {
        return !id.getText().equals("") && !price.getText().equals("") && date.getValue() != null && !notes.getText().equals("");
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
