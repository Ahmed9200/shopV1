//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.main.category;
import entities.main.stock;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StockForms {

    public static HBox root;
    StackPane main;
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("الاسم");
    Label tittle = new Label("المخازن");
    public static TextField id;
    public static TextField name;
    public static TextField search;
    Button add;
    Button update;
    Button delete;
    Button print;
    Button clear;
    public static TableView<stock> table;
    stock item = new stock();

    public StockForms() {
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.item.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم المخزن");
        search = new TextField();
        search.setPromptText("ادخل الاسم او الكود للبحث");
        this.add = new Button("اضافه", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            if (this.check()) {
                this.setValues();
                this.item.add();
                this.clear();
            } else {
                tools.ErrorBox("من فضلك ادخل البيانات كامله!");
            }

        });
        this.update = new Button("تعديل", new ImageView("images/refresh.png"));
        this.update.setOnAction((event) -> {
            this.setValues();
            this.item.update();
            this.clear();
        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            if (tools.confirmMsg("سوف يتم مسح المخزن هل انت متأكد ؟")) {
                this.setValues();
                this.item.delete();
                this.clear();
            }

        });
        this.print = new Button("طباعه الجدول", new ImageView("images/printer.png"));
        this.print.setPrefWidth(200.0D);
        this.update.setDisable(true);
        this.delete.setDisable(true);
        this.add.setDisable(false);
        this.clear = new Button("تنظيف", new ImageView("images/mop.png"));
        this.clear.setPrefWidth(180.0D);
        this.clear.setOnAction((event) -> {
            this.clear();
        });
        table = new TableView();
        table.setItems(database.buildStockTable("select * from stock"));
        table.setId("table");
        TableColumn<category, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(80.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<category, String> departmentColumn = new TableColumn(" اسم المخزن");
        departmentColumn.setMinWidth(350.0D);
        departmentColumn.setCellValueFactory(new PropertyValueFactory("name"));
        table.getColumns().addAll(new TableColumn[]{idColumn, departmentColumn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((stock) table.getSelectionModel().getSelectedItem()).getId() + "");
                name.setText(((stock) table.getSelectionModel().getSelectedItem()).getName());
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
        HBox one = new HBox(10.0D, new Node[]{this.id, idLabel});
        HBox two = new HBox(10.0D, new Node[]{this.name, nameLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two});
        allText_andLabels.setId("lbl_txt_box");
        HBox ButtonsRow = new HBox(10.0D, new Node[]{this.add, this.update, this.delete});
        ButtonsRow.setId("three_btn");
        VBox allButtons = new VBox(0.0D, new Node[]{ButtonsRow, new HBox(0.0D, new Node[]{this.clear})});
        allButtons.setId("btnbox");
        VBox left = new VBox(20.0D, new Node[]{allText_andLabels, allButtons});
        left.setPadding(new Insets(80.0D, 0.0D, 0.0D, 0.0D));
        ImageView s = new ImageView("images/search.png");
        s.setOnMouseClicked((event) -> {
            try {
                int x = Integer.parseInt(search.getText());
                table.setItems(database.buildStockTable("select * from stock where id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildStockTable("select * from stock where name like  '%" + search.getText() + "%';"));
                } catch (Exception var3) {
                    tools.ErrorBox("Error");
                }
            }

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
        root.setId("btnbox");
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    void setValues() {
        try {
            this.item.setId(Integer.parseInt(id.getText()));
            this.item.setName(name.getText());
        } catch (Exception var2) {
            tools.InformationBox("حدث خطأ ما اثناى اضاقه المخزن");
        }

    }

    void clear() {
        search.setText("");
        id.setText(this.item.getAutoNumber());
        table.setItems(database.buildStockTable("select * from stock"));
        name.setText("");
        this.update.setDisable(true);
        this.delete.setDisable(true);
        this.add.setDisable(false);
    }

    boolean check() {
        return !id.getText().equals("") && !name.getText().equals("");
    }

}
