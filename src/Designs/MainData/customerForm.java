//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.main.customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

public class customerForm {

    public static HBox root;
    StackPane main;
    Label tittle = new Label("العملاء");
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("الاسم");
    Label phoneLabel = new Label("رقم الهاتف");
    Label addressLabel = new Label("العنوان");
    Label notesLabel = new Label("ملاحظات");
    public static TextField id;
    public static TextField name;
    public static TextField phone;
    public static TextField search;
    public static TextArea address;
    public static TextArea notes;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<customer> table;
    customer c = new customer();

    public customerForm() {
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.c.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم العميل");
        phone = new TextField();
        phone.setPromptText("رقم الهاتف");
        search = new TextField();
        search.setPromptText("كلمه البحث");
        address = new TextArea();
        address.setPromptText("ادخل العنوان");
        notes = new TextArea();
        notes.setPromptText("ملاحظــــــــــات");
        this.add = new Button("اضافه", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            if (this.check()) {
                this.setValues();
                this.c.add();
                table.setItems(database.buildCustomersTable("select * from customer"));
                this.clear();
            } else {
                tools.ErrorBox("من فضلك ادخل اسم العميل");
            }

        });
        this.update = new Button("تعديل", new ImageView("images/refresh.png"));
        this.update.setOnAction((event) -> {
            if (this.check()) {
                this.setValues();
                this.c.update();
                table.setItems(database.buildCustomersTable("select * from customer"));
                this.clear();
            } else {
                tools.ErrorBox("من فضلك تأكد من اسم العميل");
            }

        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            if (tools.confirmMsg("سوف يتم مسح العميل بكل شئ اشتراه  من قاعده البيانات هل انت متأكد ؟")) {
                this.setValues();
                this.c.delete();
                table.setItems(database.buildCustomersTable("select * from customer"));
                this.clear();
            }

        });
        this.clear = new Button("تنظيف", new ImageView("images/mop.png"));
        this.clear.setPrefWidth(170.0D);
        this.clear.setOnAction((event) -> {
            this.clear();
        });
        table = new TableView();
        table.setId("table");

        table.setItems(database.buildCustomersTable("select * from customer"));
        TableColumn<customer, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(10.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("customer_id"));
        TableColumn<customer, String> nameColumn = new TableColumn("اسم العميل ");
        nameColumn.setMinWidth(15.0D);
        nameColumn.setCellValueFactory(new PropertyValueFactory("customer_name"));
        TableColumn<customer, String> phoneColumn = new TableColumn("الهاتف");
        phoneColumn.setMinWidth(20.0D);
        phoneColumn.setCellValueFactory(new PropertyValueFactory("customer_phone"));
        TableColumn<customer, Integer> addressCoulmn = new TableColumn("العنوان");
        addressCoulmn.setMinWidth(50.0D);
        addressCoulmn.setCellValueFactory(new PropertyValueFactory("customer_address"));
        TableColumn<customer, Integer> notesCoulmn = new TableColumn("ملاحظات");
        notesCoulmn.setMinWidth(100.0D);
        notesCoulmn.setCellValueFactory(new PropertyValueFactory("customer_notes"));
        table.getColumns().addAll(new TableColumn[]{idColumn, nameColumn, phoneColumn, addressCoulmn, notesCoulmn});
        table.setPrefSize(500.0D, 400.0D);
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((customer) table.getSelectionModel().getSelectedItem()).getCustomer_id() + "");
                name.setText(((customer) table.getSelectionModel().getSelectedItem()).getCustomer_name());
                phone.setText(((customer) table.getSelectionModel().getSelectedItem()).getCustomer_phone());
                address.setText(((customer) table.getSelectionModel().getSelectedItem()).getCustomer_address());
                notes.setText(((customer) table.getSelectionModel().getSelectedItem()).getCustomer_notes());
                this.add.setDisable(true);
                this.update.setDisable(false);
                this.delete.setDisable(false);
            } catch (Exception var3) {
            }

        });
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(1200.0D);
        i.setFitWidth(1600.0D);
        HBox one = new HBox(10.0D, new Node[]{this.id, idLabel});
        HBox two = new HBox(10.0D, new Node[]{this.name, nameLabel});
        HBox three = new HBox(10.0D, new Node[]{this.phone, phoneLabel});
        HBox five = new HBox(10.0D, new Node[]{this.address, addressLabel});
        HBox six = new HBox(10.0D, new Node[]{this.notes, notesLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three, five, six});
        allText_andLabels.setId("lbl_txt_box");
        HBox ButtonsRow = new HBox(10.0D, new Node[]{this.add, this.update, this.delete});
        ButtonsRow.setId("three_btn");
        VBox allButtons = new VBox(0.0D, new Node[]{ButtonsRow, new HBox(10.0D, new Node[]{this.clear})});
        allButtons.setId("btnbox");
        VBox left = new VBox(20.0D, new Node[]{allText_andLabels, allButtons});
        ImageView s = new ImageView("images/search.png");
        s.setOnMouseClicked((event) -> {
            try {
                int x = Integer.parseInt(search.getText());
                table.setItems(database.buildCustomersTable("select * from customer where id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildCustomersTable("select * from customer where name like  '%" + search.getText() + "%';"));
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
        this.tittle.setId("tittle");
        HBox tittleBox = new HBox(tittle);
        tittleBox.setAlignment(Pos.CENTER);
        VBox all = new VBox(10.0D, new Node[]{tittleBox, center});
        this.main = new StackPane(new Node[]{i, all});
        root = new HBox(new Node[]{all});
        this.clear();
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    void setValues() {
        try {
            this.c.setCustomer_id(Integer.parseInt(id.getText()));
            this.c.setCustomer_name(name.getText());
            this.c.setCustomer_phone(phone.getText());
            this.c.setCustomer_address(address.getText());
            this.c.setCustomer_notes(notes.getText());
        } catch (Exception var2) {
        }

    }

    void clear() {
        id.setText(this.c.getAutoNumber());
        table.setItems(database.buildCustomersTable("select * from customer"));
        name.setText("");
        phone.setText("");
        address.setText("");
        notes.setText("");
        this.add.setDisable(false);
        this.update.setDisable(true);
        this.delete.setDisable(true);
    }

    boolean check() {
        return !name.getText().equals("");
    }

}
