//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs.cashTransaction;

import Database.database;
import Tools.tools;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class returns {
    public static HBox root;
    StackPane main;
    public static TableView<entities.cash.returns> table;
    public static TextField bill_id;
    public static ComboBox chooser;
    public static DatePicker userDate;
    public static Button search;
    public static Label tittle;

    public returns() {
        tittle = new Label("البضاعه المرتجعه للمحل");
        chooser = new ComboBox(FXCollections.observableArrayList(new String[]{"كود الفاتوره", "التاريخ"}));
        chooser.setPromptText("اختر طريقه البحث");
        userDate = new DatePicker();
        userDate.setPromptText("ادخل التاريخ");
        bill_id = new TextField();
        bill_id.setPrefSize(300.0D, 40.0D);
        bill_id.setPromptText("ادخل كود الفاتوره");
        search = new Button("بحث");
        search.setPrefSize(300.0D, 40.0D);
        search.setOnAction((event) -> {
            try {
                if (chooser.getSelectionModel().getSelectedItem().equals("كود الفاتوره")) {
                    if (bill_id.getText().equals("")) {
                        table.setItems(database.buildReturnsTable("select * from returns"));
                    } else {
                        table.setItems(database.buildReturnsTable("select * from returns where bill_id=" + bill_id.getText()));
                    }
                } else if (userDate.getValue() == null) {
                    table.setItems(database.buildReturnsTable("select * from returns"));
                } else {
                    table.setItems(database.buildReturnsTable("select * from returns where date between '" + ((LocalDate)userDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' and '" + (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()) + " 00:00:00';"));
                    System.out.println("select * from returns where date between '" + ((LocalDate)userDate.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00' and '" + (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()) + " 00:00:00';");
                }
            } catch (Exception var2) {
                tools.WarningBox("من فضلك ادخل البيانات");
            }

        });
        table = new TableView();
        table.setPrefSize(1000.0D, 500.0D);
        table.setItems(database.buildReturnsTable("select * from returns"));
        TableColumn<entities.cash.returns, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("drug_id"));
        TableColumn<entities.cash.returns, String> drug_barcodeCoulmn = new TableColumn("الباركود");
        drug_barcodeCoulmn.setMinWidth(30.0D);
        drug_barcodeCoulmn.setCellValueFactory(new PropertyValueFactory("drug_barcode"));
        TableColumn<entities.cash.returns, String> return_quantityCoulmn = new TableColumn("الكميه المرجعه");
        return_quantityCoulmn.setMinWidth(15.0D);
        return_quantityCoulmn.setCellValueFactory(new PropertyValueFactory("return_quantity"));
        TableColumn<entities.cash.returns, Integer> bill_idCoulmn = new TableColumn("كود الفاتوره");
        bill_idCoulmn.setMinWidth(15.0D);
        bill_idCoulmn.setCellValueFactory(new PropertyValueFactory("bill_id"));
        TableColumn<entities.cash.returns, Integer> dateCoulmn = new TableColumn("تاريخ الارجاع");
        dateCoulmn.setMinWidth(20.0D);
        dateCoulmn.setCellValueFactory(new PropertyValueFactory("date"));
        table.getColumns().addAll(new TableColumn[]{idColumn, drug_barcodeCoulmn, return_quantityCoulmn, bill_idCoulmn, dateCoulmn});
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(1200.0D);
        i.setFitWidth(1600.0D);
        HBox text = new HBox(15.0D, new Node[]{bill_id, userDate, chooser});
        text.setPadding(new Insets(20.0D, 0.0D, 0.0D, 220.0D));
        HBox tableBox = new HBox(new Node[]{table});
        tableBox.setPadding(new Insets(20.0D, 0.0D, 0.0D, 30.0D));
        VBox center = new VBox(20.0D, new Node[]{new VBox(20.0D, new Node[]{text, search}), tableBox});
        search.setAlignment(Pos.CENTER);
        HBox tittle_hbox = new HBox(new Node[]{tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        tittle.setFont(new Font("san Serif", 50.0D));
        tittle.setScaleX(1.0D);
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center});
        this.main = new StackPane(new Node[]{i, all});
        root = new HBox(new Node[]{all});
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }
}
