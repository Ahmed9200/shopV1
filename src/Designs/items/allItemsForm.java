//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.items;

import Database.database;
import Tools.tools;
import entities.items.items;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class allItemsForm {

    public static HBox root;
    public static StackPane main;
    public static TableView<items> DrugsTable;
    public static TextField searchText1;
    public static TextField searchText2;
    public static Label tittle;
    public static Label buyPrice;
    public static Label sellPrice;
    public static Label diffrence;
    public static Button search;
    public static ComboBox chooser1;
    public static ComboBox chooser2;

    public allItemsForm() {
        buyPrice = new Label();
        tittle = new Label("جرد الاصناف");
        sellPrice = new Label();
        diffrence = new Label();
        searchText1 = new TextField();
        searchText1.setPromptText("ادخل كلمه البحث الاولى");
        searchText2 = new TextField();
        searchText2.setPromptText("ادخل كلمه البحث الثانيه");
        chooser1 = new ComboBox(FXCollections.observableArrayList(new String[]{"الباركود", "كود المورد", "الكميه", "كود الصنف"}));
        chooser1.setPromptText("ادخل طريقه البحث الاولى");
        chooser2 = new ComboBox(FXCollections.observableArrayList(new String[]{"الباركود", "كود المورد", "الكميه", "كود الصنف"}));
        chooser2.setPromptText("ادخل طريقه البحث الثانيه");
        VBox search1 = new VBox(15.0D, new Node[]{chooser1, searchText1});
        VBox search2 = new VBox(15.0D, new Node[]{chooser2, searchText2});
        VBox box3 = new VBox(15.0D, new Node[]{buyPrice, sellPrice, diffrence});
        HBox searchRow = new HBox(20.0D, new Node[]{search1, search2});
        DrugsTable = new TableView();
        DrugsTable.setItems(database.buildItemTable("SELECT * FROM items ;"));
        DrugsTable.setPrefSize(900.0D, 700.0D);
        search = new Button("بحث");
        search.setPrefSize(200.0D, 40.0D);
        search.setOnAction((event) -> {
            try {
                String key1 = searchText1.getText();
                String key2 = searchText2.getText();
                if (chooser1.getSelectionModel().getSelectedItem() == null) {
                    if (chooser2.getSelectionModel().getSelectedItem() == null) {
                        tools.ErrorBox("من فصلك قم باختيار طريقه البحث اولا");
                        this.setLabelsData();
                    } else {
                        DrugsTable.setItems(database.buildItemTable("select * from items where " + this.getSearchKey(chooser2.getSelectionModel().getSelectedItem().toString()) + " like '%" + key2 + "%' ;"));
                        this.setLabelsData();
                    }
                }

                if (chooser2.getSelectionModel().getSelectedItem() == null) {
                    if (chooser1.getSelectionModel().getSelectedItem() == null) {
                        tools.ErrorBox("من فصلك قم باختيار طريقه البحث اولا");
                        this.setLabelsData();
                    } else {
                        DrugsTable.setItems(database.buildItemTable("select * from items where " + this.getSearchKey(chooser1.getSelectionModel().getSelectedItem().toString()) + " like '%" + key1 + "%';"));
                        this.setLabelsData();
                    }
                }

                if (chooser1.getSelectionModel().getSelectedItem() != null && chooser2.getSelectionModel().getSelectedItem() != null) {
                    DrugsTable.setItems(database.buildItemTable("select * from items where " + this.getSearchKey(chooser1.getSelectionModel().getSelectedItem().toString()) + " like '%" + key1 + "%' and  " + this.getSearchKey(chooser2.getSelectionModel().getSelectedItem().toString()) + " like '%" + key2 + "%' ;"));
                    this.setLabelsData();
                }
            } catch (Exception var4) {
                this.setLabelsData();
            }

        });
        TableColumn<items, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn<items, String> nameColumn = new TableColumn("الاسم");
        nameColumn.setMinWidth(30.0D);
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<items, String> typeColumn = new TableColumn("كود الصنف");
        typeColumn.setMinWidth(15.0D);
        typeColumn.setCellValueFactory(new PropertyValueFactory("category_id"));

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

        TableColumn<items, String> howToSellCoulmn = new TableColumn("ملاحظات");
        howToSellCoulmn.setMinWidth(20.0D);
        howToSellCoulmn.setCellValueFactory(new PropertyValueFactory("notes"));

        TableColumn<items, Integer> barcodeCoulmn = new TableColumn("الباركود");
        barcodeCoulmn.setMinWidth(25.0D);
        barcodeCoulmn.setCellValueFactory(new PropertyValueFactory("barcode"));

        TableColumn<items, Integer> dateCoulmn = new TableColumn("التاريخ");
        dateCoulmn.setMinWidth(25.0D);
        dateCoulmn.setCellValueFactory(new PropertyValueFactory("date"));
        DrugsTable.getColumns().addAll(new TableColumn[]{idColumn, nameColumn,
            typeColumn, supplierColumn, quantityCoulmn, buyCoulmn, sellCoulmn,
            howToSellCoulmn, barcodeCoulmn, dateCoulmn});
        VBox text = new VBox(15.0D, new Node[]{searchRow, search});
        text.setPadding(new Insets(20.0D, 0.0D, 0.0D, 220.0D));
        HBox tableBox = new HBox(new Node[]{new VBox(15.0D, new Node[]{text, DrugsTable})});
        tableBox.setPadding(new Insets(20.0D, 0.0D, 0.0D, 30.0D));
        TitledPane one = new TitledPane("جميع القطع ", tableBox);
        TitledPane two = new TitledPane("المبالغ الماليه", new HBox(new Node[]{box3}));
        VBox t1 = new VBox(new Node[]{one, two});
        HBox center = new HBox(25.0D, new Node[]{t1});
        HBox tittle_hbox = new HBox(new Node[]{tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        tittle.setId("tittle");
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center});
        main = new StackPane(new Node[]{all});
        this.setLabelsData();
        root = new HBox(new Node[]{all});
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    public void setLabelsData() {
        double buy_price = 0.0D;
        double sell_price = 0.0D;
        double diff = 0.0D;

        for (int i = 0; i < DrugsTable.getItems().size(); ++i) {
            buy_price += ((items) DrugsTable.getItems().get(i)).getBuyPrice() * (double) ((items) DrugsTable.getItems().get(i)).getQuantity();
            sell_price += ((items) DrugsTable.getItems().get(i)).getSellPrice() * (double) ((items) DrugsTable.getItems().get(i)).getQuantity();
        }

        diff = sell_price - buy_price;
        buyPrice.setText("سعر الشراء للقطع الحاليه :" + buy_price);
        sellPrice.setText("سعر البيع للقطع الحاليه : " + sell_price);
        diffrence.setText("المكسب المتوقع منها : " + diff);
    }

    String getSearchKey(String key) {
        if (key.equals("كود الصنف")) {
            return "category_id";
        } else if (key.equals("الكميه")) {
            return "quantity";
        } else if (key.equals("كود المورد")) {
            return "supplier_id";
        } else {
            return key.equals("الباركود") ? "barcode" : "";
        }
    }
}
