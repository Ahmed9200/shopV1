//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.items;

import Database.database;
import Tools.tools;
import entities.items.ItemsHistory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

public class items {

    public static HBox root;
    StackPane main;
    Label tittle = new Label("القطع");
    Label idLabel = new Label("الكود");
    Label barcodeLabel = new Label("الباركود");
    Label nameLabel = new Label("الاسم");
    Label supplierLabel = new Label("المورد");
    Label buyLabel = new Label("سعر الشراء");
    Label sellLabel = new Label("سعر البيع");
    Label notesLabel = new Label("الملاحظات");
    Label quantityLabel = new Label("الكميه");
    Label stockLabel = new Label("المخزن");
    Label catLabel = new Label("الصنف");
    public static ComboBox supplier;
    public static ComboBox stockCombo;
    public static ComboBox category;
    public static TextField id;
    public static TextField barcode;
    public static TextField name;
    public static TextField buy;
    public static TextField sell;
    public static TextField search;
    public static TextField quantity;
    public static TextArea notes;
    public static String q;
    Button add;
    Button update;
    Button delete;
    Button clear;
    Button copy;
    Button c;
    public static TableView<entities.items.items> table;
    TitledPane titlePane;
    TitledPane titlePane2;
    entities.items.items d = new entities.items.items();
    ItemsHistory sd = new ItemsHistory();

    public items() {
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        barcode = new TextField();
        barcode.setDisable(true);
        barcode.setPromptText("ادخل الباركود");
        barcode.setId("barcode");
        name = new TextField();
        name.setPromptText("اسم ");

        quantity = new TextField();
        quantity.setPromptText("الكميه");
        search = new TextField();
        search.setPromptText("كلمه البحث او الباركود");
        buy = new TextField();
        buy.setPromptText("ادخل سعر الشراء");
        sell = new TextField();
        sell.setPromptText("ادخل سعر البيع");
        notes = new TextArea();
        notes.setPromptText("ادخل الملاحظات");
        supplier = new ComboBox();
        supplier.setPromptText("اسم المورد");
        stockCombo = new ComboBox();
        stockCombo.setPromptText("المخزن");
        category = new ComboBox();
        category.setPromptText("اسم الصنف");
        table = new TableView();
        TableColumn<entities.items.items, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn<entities.items.items, String> nameColumn = new TableColumn("الاسم");
        nameColumn.setMinWidth(30.0D);
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<entities.items.items, Integer> typeColumn = new TableColumn("كود الصنف");
        typeColumn.setMinWidth(15.0D);
        typeColumn.setCellValueFactory(new PropertyValueFactory("category_id"));
        TableColumn<entities.items.items, Integer> supplierColumn = new TableColumn("كود المورد");
        supplierColumn.setMinWidth(15.0D);
        supplierColumn.setCellValueFactory(new PropertyValueFactory("supplier_id"));
        TableColumn<entities.items.items, String> stockColumn = new TableColumn("المخزن");
        stockColumn.setMinWidth(15.0D);
        stockColumn.setCellValueFactory(new PropertyValueFactory("stock"));
        TableColumn<entities.items.items, Integer> quantityCoulmn = new TableColumn("الكميه");
        quantityCoulmn.setMinWidth(20.0D);
        quantityCoulmn.setCellValueFactory(new PropertyValueFactory("quantity"));
        TableColumn<entities.items.items, Double> buyCoulmn = new TableColumn("سعرالشراء");
        buyCoulmn.setMinWidth(20.0D);
        buyCoulmn.setCellValueFactory(new PropertyValueFactory("buyPrice"));
        TableColumn<entities.items.items, Double> sellCoulmn = new TableColumn("سعرالبيع");
        sellCoulmn.setMinWidth(20.0D);
        sellCoulmn.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        TableColumn<entities.items.items, Integer> howManyStripsCoulmn = new TableColumn("الملاحظات");
        howManyStripsCoulmn.setMinWidth(25.0D);
        howManyStripsCoulmn.setCellValueFactory(new PropertyValueFactory("notes"));
        TableColumn<entities.items.items, String> barcodeCoulmn = new TableColumn("الباركود");
        barcodeCoulmn.setMinWidth(25.0D);
        barcodeCoulmn.setCellValueFactory(new PropertyValueFactory("barcode"));
        TableColumn<entities.items.items, String> datesCoulmn = new TableColumn("التاريخ");
        datesCoulmn.setMinWidth(25.0D);
        datesCoulmn.setCellValueFactory(new PropertyValueFactory("date"));

        table.getColumns().addAll(new TableColumn[]{idColumn, nameColumn,
            typeColumn, supplierColumn, stockColumn, quantityCoulmn, buyCoulmn, sellCoulmn,
            howManyStripsCoulmn, barcodeCoulmn, datesCoulmn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((entities.items.items) table.getSelectionModel().getSelectedItem()).getId() + "");
                name.setText(((entities.items.items) table.getSelectionModel().getSelectedItem()).getName());
                String typeid = ((entities.items.items) table.getSelectionModel().getSelectedItem()).getCategory_id() + "";
                String typename = database.getTableData("select name from category where id=" + typeid).items[0][0].toString();
                String supplierid = ((entities.items.items) table.getSelectionModel().getSelectedItem()).getSupplier_id() + "";
                String stock = ((entities.items.items) table.getSelectionModel().getSelectedItem()).getStock() + "";
                String suppliername = database.getTableData("select name from company where id=" + supplierid).items[0][0].toString();
                supplier.getSelectionModel().select(suppliername);
                category.getSelectionModel().select(typename);
                stockCombo.getSelectionModel().select(stock);
                barcode.setText(((entities.items.items) table.getSelectionModel().getSelectedItem()).getBarcode());
                buy.setText(((entities.items.items) table.getSelectionModel().getSelectedItem()).getBuyPrice() + "");
                sell.setText(((entities.items.items) table.getSelectionModel().getSelectedItem()).getSellPrice() + "");
                notes.setText(((entities.items.items) table.getSelectionModel().getSelectedItem()).getNotes() + "");
                quantity.setText(((entities.items.items) table.getSelectionModel().getSelectedItem()).getQuantity() + "");
                q = ((entities.items.items) table.getSelectionModel().getSelectedItem()).getQuantity() + "";
            } catch (Exception var5) {
//                var5.printStackTrace();
            }

        });
        this.add = new Button("اضافه", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            if (this.check()) {
                this.setDValues();
                this.d.add();
                this.sd.add();
                this.clear();
            } else {
                tools.ErrorBox("من فضلك ادخل البيانات بطريقه صحيحه و كامله ");
            }

        });
        this.update = new Button("تعديل", new ImageView("images/refresh.png"));
        this.update.setOnAction((event) -> {
            if (this.check()) {
                int x = Integer.parseInt(q);
                int y = Integer.parseInt(quantity.getText());
                this.setSDValues((y - x) + "");
                this.d.update();
                this.sd.add();
                this.clear();
            } else {
                tools.ErrorBox("من فضلك ادخل البيانات بطريقه صحيحه و كامله ");
            }

        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            if (tools.confirmMsg("هل انت متأكد من انك تريد مسح هذه القطعه نهائيا من قاعده البيانات ؟")) {
                this.setDValues();
                this.d.delete();
                this.sd.delete();
                this.clear();
            }

        });
        this.copy = new Button("نسخ", new ImageView("images/copy.png"));
        this.copy.setOnAction((event) -> {
            id.setText(this.d.getAutoNumber());
            barcode.setText(Integer.parseInt(this.d.getAutoNumber()) + 1000 + "");
        });
        this.clear = new Button("تنظيف", new ImageView("images/mop.png"));
        this.clear.setOnAction((event) -> {
            this.clear();
        });
        this.c = new Button("طباعه باركود", new ImageView("images/barcode2.png"));
        this.c.setOnAction((event) -> {
            for (int i = 0; i < Integer.parseInt(quantity.getText()); ++i) {
                this.printBarcode();
            }

        });
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(1200.0D);
        i.setFitWidth(1600.0D);
        HBox h1 = new HBox(10.0D, new Node[]{id, this.idLabel});
        HBox h2 = new HBox(10.0D, new Node[]{name, this.nameLabel});
        HBox h3 = new HBox(10.0D, new Node[]{barcode, this.barcodeLabel});
        HBox h4 = new HBox(10.0D, new Node[]{quantity, this.quantityLabel});
        HBox h5 = new HBox(10.0D, new Node[]{supplier, this.supplierLabel});
        HBox h6 = new HBox(10.0D, new Node[]{buy, this.buyLabel});
        HBox h7 = new HBox(10.0D, new Node[]{sell, this.sellLabel});
        HBox h8 = new HBox(10.0D, new Node[]{category, this.catLabel});
        HBox h9 = new HBox(10.0D, new Node[]{notes, this.notesLabel});

        HBox data1 = new HBox(30, h1, h2);
        data1.setAlignment(Pos.CENTER);
        HBox data2 = new HBox(30, h3, h4);
        data2.setAlignment(Pos.CENTER);
        HBox data3 = new HBox(30, h5, h6);
        data3.setAlignment(Pos.CENTER);
        HBox data4 = new HBox(30, h8, h7);
        data4.setAlignment(Pos.CENTER);
        HBox data5 = new HBox(30, h9);
        data5.setAlignment(Pos.CENTER);
        HBox data6 = new HBox(30);
        data6.setAlignment(Pos.CENTER);
        HBox data7 = new HBox(30);
        data7.setAlignment(Pos.CENTER);

        VBox allText_andLabels = new VBox(15,
                data1,
                data2,
                data3,
                data4,
                data5,
                data6,
                data7);
        allText_andLabels.setId("lbl_txt_box");
        allText_andLabels.setAlignment(Pos.CENTER);
        HBox ButtonsRow = new HBox(10.0D, new Node[]{this.add, this.copy, this.update, this.delete});
        ButtonsRow.setId("three_btn");
        HBox allButtons = new HBox(10.0D, new Node[]{ButtonsRow, this.clear, this.c});
        allButtons.setAlignment(Pos.CENTER);
        allButtons.setId("btnbox");
        VBox left = new VBox(20.0D, new Node[]{allText_andLabels, allButtons});
        ImageView searchIcon = new ImageView("images/search.png");
        searchIcon.setOnMouseClicked((event) -> {
            try {
                table.setItems(database.buildItemTable("select * from items where barcode =" + search.getText()));
                if (table.getItems().isEmpty()) {
                    table.setItems(database.buildItemTable("select * from items where name ='" + search.getText() + "';"));
                }
            } catch (Exception var2) {
            }

        });
        HBox searchRow = new HBox(5.0D, new Node[]{search, searchIcon});
        searchRow.setPadding(new Insets(0.0D, 20.0D, 0.0D, 130.0D));
        HBox tableRow = new HBox(new Node[]{table});
        VBox right = new VBox(10.0D, new Node[]{searchRow, tableRow});
        VBox center = new VBox(30.0D, new Node[]{left, right});
        center.setOpacity(0.8D);
        this.titlePane = new TitledPane("ادخال البيانات ", left);
        this.titlePane.setPrefWidth(900.0D);
        this.titlePane2 = new TitledPane("الجدول", right);
        this.titlePane2.setPrefWidth(900.0D);
        VBox titlePanes = new VBox(10.0D, new Node[]{this.titlePane, this.titlePane2});
        HBox center2 = new HBox(new Node[]{titlePanes});
        center2.setId("root");
        HBox tittle_hbox = new HBox(new Node[]{this.tittle});
        tittle_hbox.setAlignment(Pos.CENTER);
        this.tittle.setId("tittle");
        VBox all = new VBox(10.0D, new Node[]{tittle_hbox, center2});
        this.main = new StackPane(new Node[]{i, all});
        this.clear();
        root = new HBox(new Node[]{all});
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    void clear() {
        table.setItems(database.buildItemTable("select * from items"));
        supplier.getItems().clear();
        database.fillComboBoxData("company", "name", "", supplier);
        database.fillComboBoxData("category", "name", "", category);
        database.fillComboBoxData("stock", "name", "", stockCombo);
        supplier.getSelectionModel().select((Object) null);
        stockCombo.getSelectionModel().select((Object) null);
        category.getSelectionModel().select((Object) null);
        id.setText(this.d.getAutoNumber());
        name.setText("");
        quantity.setText("");
        buy.setText("");
        sell.setText("");
        search.setText("");
        notes.setText("");
        barcode.setText(Integer.parseInt(this.d.getAutoNumber()) + 1000 + "");
    }

    void setDValues() {
        try {
            this.d.setId(Integer.parseInt(id.getText()));
            this.d.setName(name.getText());
            this.d.setBarcode(barcode.getText());
            this.d.setDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "");
            String supplierName = supplier.getSelectionModel().getSelectedItem().toString();
            String stock = stockCombo.getSelectionModel().getSelectedItem().toString();
            String supplierId = database.getTableData("select id from company where name ='" + supplierName + "';").items[0][0].toString();
            this.d.setSupplier_id(Integer.parseInt(supplierId));
            this.d.setStock(stock);
            String CategoryName = category.getSelectionModel().getSelectedItem().toString();
            String catId = database.getTableData("select id from category where name ='" + CategoryName + "';").items[0][0].toString();
            this.d.setCategory_id(Integer.parseInt(catId));
            this.d.setNotes(notes.getText());
            this.d.setBuyPrice(Double.parseDouble(buy.getText()));
            this.d.setSellPrice(Double.parseDouble(sell.getText()));
            this.d.setQuantity(Integer.parseInt(quantity.getText()));

            this.sd.setId(Integer.parseInt(this.sd.getAutoNumber()));
            this.sd.setName(name.getText());
            this.sd.setSupplier_id(Integer.parseInt(supplierId));
            this.sd.setStock(stock);
            this.sd.setCategory_id(Integer.parseInt(catId));
            this.sd.setBuyPrice(Double.parseDouble(buy.getText()));
            this.sd.setSellPrice(Double.parseDouble(sell.getText()));
            this.sd.setBarcode(barcode.getText());
            this.sd.setQuantity(Integer.parseInt(quantity.getText()));
            this.sd.setNotes(notes.getText());
            this.sd.setDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "");
        } catch (NumberFormatException var5) {
            tools.ErrorBox("SomeThing went Wrong in setValues() method");
        }

    }

    void setSDValues(String q) {
        try {
            this.d.setId(Integer.parseInt(id.getText()));
            this.d.setName(name.getText());
            this.d.setBarcode(barcode.getText());
            this.d.setDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "");
            String supplierName = supplier.getSelectionModel().getSelectedItem().toString();
            String stock = supplier.getSelectionModel().getSelectedItem().toString();
            String supplierId = database.getTableData("select id from company where name ='" + supplierName + "';").items[0][0].toString();
            this.d.setSupplier_id(Integer.parseInt(supplierId));
            String CategoryName = category.getSelectionModel().getSelectedItem().toString();
            String catId = database.getTableData("select id from category where name ='" + CategoryName + "';").items[0][0].toString();
            this.d.setCategory_id(Integer.parseInt(catId));
            this.d.setNotes(notes.getText());
            this.d.setStock(stock);
            this.d.setBuyPrice(Double.parseDouble(buy.getText()));
            this.d.setSellPrice(Double.parseDouble(sell.getText()));
            this.d.setQuantity(Integer.parseInt(quantity.getText()));

            this.sd.setId(Integer.parseInt(this.sd.getAutoNumber()));
            this.sd.setName(name.getText());
            this.sd.setStock(stock);
            this.sd.setSupplier_id(Integer.parseInt(supplierId));
            this.sd.setCategory_id(Integer.parseInt(catId));
            this.sd.setBuyPrice(Double.parseDouble(buy.getText()));
            this.sd.setSellPrice(Double.parseDouble(sell.getText()));
            this.sd.setBarcode(barcode.getText());
            this.sd.setQuantity(Integer.parseInt(q));
            this.sd.setNotes(notes.getText());
            this.sd.setDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + "");
        } catch (NumberFormatException var5) {
            tools.ErrorBox("SomeThing went Wrong in setValues() method");
        }

    }

    boolean check() {
        return !buy.getText().equals("") && !sell.getText().equals("") && !barcode.getText().equals("");
    }

    void printBarcode() {
        try {
            List<Map<String, ?>> dataSource = new ArrayList();
            Map<String, Object> m = new HashMap();
            m.put("barcode", barcode.getText());
            dataSource.add(m);
            HashMap para = new HashMap();
            para.put("name", name.getText());
            para.put("bar", barcode.getText());
            para.put("price", sell.getText() + " LE");
            JRDataSource dataSource1 = new JRBeanCollectionDataSource(dataSource);
            File file = new File("mainDataReports\\barcode2.jrxml");
            String absolutePath = file.getAbsolutePath();
            System.out.println(absolutePath);
            JasperDesign jd = JRXmlLoader.load(absolutePath);
            JasperReport jp = JasperCompileManager.compileReport(jd);
            JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            JasperPrint printt = JasperFillManager.fillReport(jp, para, dataSource1);
            JasperPrintManager.printReport(printt, false);
        } catch (JRException var10) {
            var10.printStackTrace();
        }

    }
}
