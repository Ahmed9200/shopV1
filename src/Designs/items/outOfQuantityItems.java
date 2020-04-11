//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.items;

import Database.database;
import entities.items.items;
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

public class outOfQuantityItems {

    public static HBox root;
    StackPane main;
    public static TableView<items> table;
    public static TextField user_quantity;
    public static Button search;
    public static Label tittle;

    public outOfQuantityItems() {
        tittle = new Label("الكميات المنتهيه");
        user_quantity = new TextField();
        user_quantity.setPrefSize(300.0D, 40.0D);
        user_quantity.setPromptText("ادخل الكميه الذى التى تريد البحث عنه او قبل منه");
        search = new Button("بحث");
        search.setPrefSize(300.0D, 40.0D);
        search.setOnAction((event) -> {
            table.setItems(database.buildItemTable("select * from items where quantity <=" + user_quantity.getText()));
        });
        table = new TableView();
        table.setPrefSize(1000.0D, 500.0D);
        table.setItems(database.buildItemTable("select * from items where quantity=0"));
        TableColumn<items, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<items, String> nameColumn = new TableColumn("الاسم");
        nameColumn.setMinWidth(30.0D);
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<items, String> category_idColumn = new TableColumn("كود الصنف");
        category_idColumn.setMinWidth(15.0D);
        category_idColumn.setCellValueFactory(new PropertyValueFactory("category_id"));
        TableColumn<items, Integer> supplierColumn = new TableColumn("كود المورد");
        supplierColumn.setMinWidth(15.0D);
        supplierColumn.setCellValueFactory(new PropertyValueFactory("supplier_id"));
        TableColumn<items, Integer> quantityCoulmn = new TableColumn("الكميه");
        quantityCoulmn.setMinWidth(20.0D);
        quantityCoulmn.setCellValueFactory(new PropertyValueFactory("quantity"));
        TableColumn<items, Integer> buyCoulmn = new TableColumn("سعرالشراء");
        buyCoulmn.setMinWidth(20.0D);
        buyCoulmn.setCellValueFactory(new PropertyValueFactory("buyPrice"));
        TableColumn<items, Integer> sellCoulmn = new TableColumn("سعرالبيع");
        sellCoulmn.setMinWidth(20.0D);
        sellCoulmn.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        TableColumn<items, Integer> notesCoulmn = new TableColumn("الملاحظات");
        notesCoulmn.setMinWidth(20.0D);
        notesCoulmn.setCellValueFactory(new PropertyValueFactory("notes"));
        TableColumn<items, Integer> dateCoulmn = new TableColumn("التاريخ");
        dateCoulmn.setMinWidth(25.0D);
        dateCoulmn.setCellValueFactory(new PropertyValueFactory("date"));
        TableColumn<items, Integer> barcodeCoulmn = new TableColumn("الباركود");
        barcodeCoulmn.setMinWidth(25.0D);
        table.getColumns().addAll(new TableColumn[]{idColumn, nameColumn,
            category_idColumn, supplierColumn, quantityCoulmn, buyCoulmn,
            sellCoulmn, notesCoulmn, dateCoulmn, barcodeCoulmn});
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(1200.0D);
        i.setFitWidth(1600.0D);
        HBox text = new HBox(15.0D, new Node[]{user_quantity, search});
        text.setPadding(new Insets(20.0D, 0.0D, 0.0D, 220.0D));
        HBox tableBox = new HBox(new Node[]{table});
        tableBox.setPadding(new Insets(20.0D, 0.0D, 0.0D, 30.0D));
        VBox center = new VBox(20.0D, new Node[]{text, tableBox});
        HBox tittle_hbox = new HBox(new Node[]{tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        tittle.setId("tittle");
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center});
        this.main = new StackPane(new Node[]{i, all});
        root = new HBox(new Node[]{all});
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }
}
