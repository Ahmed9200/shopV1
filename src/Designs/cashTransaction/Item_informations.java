//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.cashTransaction;

import Database.database;
import Tools.tools.table;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Item_informations extends Application {

    public Item_informations() {
    }

    public void start(Stage primaryStage) throws Exception {
        table t = database.getTableData("select * from items where barcode='" + bill.bar + "';");
        String name = t.items[0][1].toString();
        String sellPrice = t.items[0][6].toString();
        String buyPrice = t.items[0][5].toString();
        String dates = t.items[0][8].toString();
        String expiredDate = t.items[0][9].toString();

        Label nameLabel = new Label("اسم القطعه :   " + name);
        nameLabel.setAlignment(Pos.CENTER);

        Label buyLabel = new Label("سعر الشراء :    " + buyPrice);
        buyLabel.setAlignment(Pos.CENTER);

        Label sellLabel = new Label(" سعر البيع :   " + sellPrice);
        sellLabel.setAlignment(Pos.CENTER);

        Label datesLabel = new Label("الملاحظات :    " + dates);
        datesLabel.setAlignment(Pos.CENTER);


        Label expiredLabel = new Label("المده (باليوم):   " + this.daysBetween((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).parse(expiredDate), new Date()));
        expiredLabel.setAlignment(Pos.CENTER);

        VBox tab2 = new VBox(20, nameLabel, buyLabel, sellLabel, datesLabel, expiredLabel);
        tab2.setAlignment(Pos.CENTER);
        tab2.setId("itemInfo");

        Tab details = new Tab("تفاصيل");
        ScrollPane scrollPane = new ScrollPane(tab2);
        HBox root = new HBox(scrollPane);
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/cash.css").toExternalForm());
        details.setContent(root);
        TabPane tabpane = new TabPane(new Tab[]{details});
        Scene scene = new Scene(tabpane, 400.0D, 300.0D);
        primaryStage.setTitle("تفاصيل");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / 86400000L);
    }
}
