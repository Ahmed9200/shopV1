//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.MainData;

import Database.database;
import Tools.tools;
import entities.main.company;
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

public class companiesForm {

    public static HBox root;
    StackPane main;
    Label tittle = new Label("الشركات");
    Label idLabel = new Label("الكود");
    Label nameLabel = new Label("الاسم");
    Label emailLabel = new Label("البريد الالكترونى");
    Label phoneLabel = new Label("رقم الهاتف");
    Label addressLabel = new Label("العنوان");
    Label notesLabel = new Label("ملاحظات");
    Label faxLabel = new Label(" الفاكس");
    public static TextField id;
    public static TextField name;
    public static TextField email;
    public static TextField fax;
    public static TextField phone;
    public static TextField search;
    public static TextArea address;
    public static TextArea notes;
    Button add;
    Button update;
    Button delete;
    Button clear;
    public static TableView<company> table;
    company c = new company();

    public companiesForm() {
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText(this.c.getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم الشركه");
        email = new TextField();
        email.setPromptText("البريد الالكترونى");
        phone = new TextField();
        phone.setPromptText("رقم الهاتف");
        search = new TextField();
        search.setPromptText("كلمه البحث او الكود");
        fax = new TextField();
        fax.setPromptText("كود الفاكس للشركه");
        address = new TextArea();
        address.setPromptText("ادخل العنوان");
        notes = new TextArea();
        notes.setPromptText("ملاحظــــــــــات");
        this.add = new Button("اضافه", new ImageView("images/add.png"));
        this.add.setOnAction((event) -> {
            this.setValues();
            this.c.add();
            this.clean();
        });
        this.update = new Button("تعديل", new ImageView("images/refresh.png"));
        this.update.setOnAction((event) -> {
            this.setValues();
            this.c.update();
            this.clean();
        });
        this.delete = new Button("مسح", new ImageView("images/delete.png"));
        this.delete.setOnAction((event) -> {
            if (tools.confirmMsg("سوف يتم مسح الشركه بكل الموردين الذين منها  من قاعده البيانات هل انت متأكد ؟")) {
                this.setValues();
                this.c.delete();
                this.clean();
            }

        });

        this.clear = new Button("تنظيف", new ImageView("images/mop.png"));
        this.clear.setPrefWidth(170.0D);
        this.clear.setOnAction((event) -> {
            this.clean();
        });
        table = new TableView();
        table.setId("table");

        table.setItems(database.buildCompaniesTable("select * from company"));
        TableColumn<company, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("company_id"));
        TableColumn<company, String> nameCoulmn = new TableColumn("الاسم");
        nameCoulmn.setMinWidth(20.0D);
        nameCoulmn.setCellValueFactory(new PropertyValueFactory("company_name"));
        TableColumn<company, String> phoneCoulmn = new TableColumn("الهاتف");
        phoneCoulmn.setMinWidth(20.0D);
        phoneCoulmn.setCellValueFactory(new PropertyValueFactory("company_phone"));
        TableColumn<company, Integer> emailCoulmn = new TableColumn("الايميل");
        emailCoulmn.setMinWidth(20.0D);
        emailCoulmn.setCellValueFactory(new PropertyValueFactory("company_email"));
        TableColumn<company, Integer> faxCoulmn = new TableColumn("الفاكس");
        faxCoulmn.setMinWidth(20.0D);
        faxCoulmn.setCellValueFactory(new PropertyValueFactory("company_fax"));
        TableColumn<company, Integer> addressCoulmn = new TableColumn("العنوان");
        addressCoulmn.setMinWidth(20.0D);
        addressCoulmn.setCellValueFactory(new PropertyValueFactory("company_address"));
        TableColumn<company, Integer> notesColumn = new TableColumn("ملاحظات");
        notesColumn.setMinWidth(20.0D);
        notesColumn.setCellValueFactory(new PropertyValueFactory("company_notes"));
        table.getColumns().addAll(new TableColumn[]{idColumn, nameCoulmn, phoneCoulmn, emailCoulmn, faxCoulmn, addressCoulmn, notesColumn});
        table.setOnMouseClicked((event) -> {
            try {
                id.setText(((company) table.getSelectionModel().getSelectedItem()).getCompany_id() + "");
                name.setText(((company) table.getSelectionModel().getSelectedItem()).getCompany_name());
                phone.setText(((company) table.getSelectionModel().getSelectedItem()).getCompany_phone());
                email.setText(((company) table.getSelectionModel().getSelectedItem()).getCompany_email());
                fax.setText(((company) table.getSelectionModel().getSelectedItem()).getCompany_fax());
                address.setText(((company) table.getSelectionModel().getSelectedItem()).getCompany_address());
                notes.setText(((company) table.getSelectionModel().getSelectedItem()).getCompany_notes());
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
        HBox four = new HBox(10.0D, new Node[]{this.email, emailLabel});
        HBox five = new HBox(10.0D, new Node[]{this.fax, faxLabel});
        HBox six = new HBox(10.0D, new Node[]{this.address, addressLabel});
        HBox seven = new HBox(10.0D, new Node[]{this.notes, notesLabel});
        VBox allText_andLabels = new VBox(20.0D, new Node[]{one, two, three, four, five, six, seven});
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
                table.setItems(database.buildCompaniesTable("select * from company where id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildCompaniesTable("select * from company where name like  '%" + search.getText() + "%';"));
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
        this.clean();
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    void setValues() {
        try {
            this.c.setCompany_id(Integer.parseInt(id.getText()));
            this.c.setCompany_name(name.getText());
            this.c.setCompany_phone(phone.getText());
            this.c.setCompany_email(email.getText());
            this.c.setCompany_fax(fax.getText());
            this.c.setCompany_address(address.getText());
            this.c.setCompany_notes(notes.getText());
        } catch (Exception var2) {
        }

    }

    void clean() {
        table.setItems(database.buildCompaniesTable("select * from company"));
        id.setText(this.c.getAutoNumber());
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        notes.setText("");
        fax.setText("");
        this.add.setDisable(false);
        this.update.setDisable(true);
        this.delete.setDisable(true);
    }

}
