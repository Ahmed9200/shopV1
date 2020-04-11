//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package Designs.MainData;

import Database.database;
import Tools.tools;
import Tools.tools.table;
import entities.main.employee;
import entities.main.privileges;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class employeeForm {

    public static HBox root;
    StackPane main;
    Label tittle;
    Label idLabel;
    Label nameLabel;
    Label emailLabel;
    Label phoneLabel;
    Label addressLabel;
    Label notesLabel;
    Label ageLabel;
    Label salaryLabel;
    Label ssidLabel;
    Label dobLabel;
    Label date_of_beginLabel;
    Label date_of_endLabel;
    Label certificationLabel;
    Label showPassword;
    Label usernameLabel;
    Label passwordLabel;
    Label repasswordLabel;
    Label privileges;
    public static DatePicker dob;
    public static DatePicker date_of_begin;
    public static DatePicker date_of_end;
    public static TextField id;
    public static TextField name;
    public static TextField email;
    public static TextField phone;
    public static TextField search;
    public static TextField salary;
    public static TextField certification;
    public static TextField ssid;
    public static TextField age;
    public static TextField username;
    public static PasswordField password;
    public static PasswordField repassword;
    public static TextArea address;
    public static TextArea notes;
    public static Button add;
    public static Button update;
    public static Button delete;
    public static Button clear;
    public static TableView<employee> table;
    public static final ObservableList<String> mainForms = FXCollections.observableArrayList(new String[]{"الموردين-الشركات", "المخازن", "الموظفين", "العملاء", " اضافه الاصناف",
        "الاصناف ذات الكميه المنتهيه", "جرد الاصناف", "الاصناف", "متابعه الاقساط",
        "التفاصيل", "نافذه البيع", "جميع الفواتير", "تسجيل واردات", "تسجيل مصروفات", "المرتجعات", "الجرد الكلى"});
    public static final ObservableList<String> userPrivileges = FXCollections.observableArrayList();
    public static final ListView<String> mainListView;
    public static final ListView<String> userPrivilegesListView;
    employee e = new employee();

    public employeeForm() {
        privileges p = new privileges();
        this.idLabel = new Label("الكود");
        this.nameLabel = new Label("الاسم");
        this.phoneLabel = new Label("رقم الهاتف");
        this.emailLabel = new Label("البريد الالكترونى");
        this.addressLabel = new Label("العنوان");
        this.notesLabel = new Label("ملاحظات");
        this.ageLabel = new Label("السن");
        this.salaryLabel = new Label("المرتب");
        this.ssidLabel = new Label("ادخل الرقم القومى");
        this.dobLabel = new Label("تاريخ الميلاد");
        this.date_of_beginLabel = new Label("تاريخ البدايه");
        this.date_of_endLabel = new Label("تاريخ النهايه");
        this.certificationLabel = new Label("المؤهل الدراسى");
        this.usernameLabel = new Label("اسم المستخدم");
        this.passwordLabel = new Label("كلمه المرور");
        this.repasswordLabel = new Label("تأكيد كلمه المرور");
        this.tittle = new Label("الموظفين");
        this.showPassword = new Label("", new ImageView("images/show-password.png"));
        this.showPassword.setOnMousePressed((event) -> {
            password.setPromptText(password.getText());
            password.setText("");
        });
        this.showPassword.setOnMouseReleased((event) -> {
            password.setText(password.getPromptText());
        });
        this.privileges = new Label("اعطاء الصلاحيات للمستخدم");
        this.privileges.setStyle("-fx-font-size: 34pt;    -fx-text-fill:black ;");
        id = new TextField();
        id.setDisable(true);
        id.setPromptText("ID");
        id.setText((new employee()).getAutoNumber());
        name = new TextField();
        name.setPromptText("اسم الموظف");
        email = new TextField();
        email.setPromptText("البريد الالكترونى");
        phone = new TextField();
        phone.setPromptText("رقم الهاتف");
        search = new TextField();
        search.setPromptText("كلمه البحث او الكود");
        ssid = new TextField();
        ssid.setPromptText("ادخل الرقم القومى");
        salary = new TextField();
        salary.setPromptText("ادخل المرتب");
        age = new TextField();
        age.setPromptText("ادخل السن");
        certification = new TextField();
        certification.setPromptText("ادخل المؤهل الدراسى");
        username = new TextField();
        username.setPromptText("اسم المستخدم ف البرنامج");
        password = new PasswordField();
        password.setPromptText("ادخل كلمه المرور");
        repassword = new PasswordField();
        repassword.setPromptText("تأكيد كلمه المرور");
        address = new TextArea();
        address.setPromptText("ادخل العنوان");
        notes = new TextArea();
        notes.setPromptText("ملاحظــــــــــات");
        add = new Button("اضافه", new ImageView("images/add.png"));
        add.setOnAction((event) -> {
            if (this.checkPassword()) {
                if (this.checkValues()) {
                    this.setValues();
                    this.e.add();
                    table.setItems(database.buildEmployeTable("select emp_id , emp_name from employees"));
                    this.clear();
                } else {
                    tools.ErrorBox("من فضلك ادخل البيانات كامله");
                }
            } else {
                tools.ErrorBox("كلمتا السر غير متطابقتين");
            }

        });
        update = new Button("تعديل", new ImageView("images/refresh.png"));
        update.setOnAction((event) -> {
            if (this.checkPassword()) {
                if (this.checkValues()) {
                    this.setValues();
                    this.e.update();
                    table.setItems(database.buildEmployeTable("select emp_id , emp_name from employees"));
                    this.clear();
                } else {
                    tools.ErrorBox("من فضلك ادخل البيانات كامله");
                }
            } else {
                tools.ErrorBox("كلمتا السر غير متطابقتين");
            }

        });
        update.setDisable(true);
        delete = new Button("مسح", new ImageView("images/delete.png"));
        delete.setOnAction((event) -> {
            this.setValues();
            this.e.delete();
            table.setItems(database.buildEmployeTable("select emp_id , emp_name from employees"));
            this.clear();
        });
        delete.setDisable(true);
        clear = new Button("تنظيف", new ImageView("images/mop.png"));
        clear.setOnAction((event) -> {
            this.clear();
        });
        dob = new DatePicker();
        dob.setPromptText("تاريخ الميلاد");
        date_of_begin = new DatePicker();
        date_of_begin.setPromptText("تاريخ بدايه العمل");
        date_of_end = new DatePicker();
        date_of_end.setPromptText("تاريخ نهايه العمل");
        table = new TableView();
        table.setPrefWidth(800.0D);
        TableColumn<employee, Integer> idColumn = new TableColumn("الكود");
        idColumn.setMinWidth(15.0D);
        idColumn.setCellValueFactory(new PropertyValueFactory("emp_id"));
        TableColumn<employee, String> departmentColumn = new TableColumn(" اسم الموظف ");
        departmentColumn.setMinWidth(30.0D);
        departmentColumn.setCellValueFactory(new PropertyValueFactory("emp_name"));
        table.getColumns().addAll(new TableColumn[]{idColumn, departmentColumn});
        table.setItems(database.buildEmployeTable("select emp_id , emp_name from employees"));
        table.setOnMouseClicked((event) -> {
            try {
                String idt = ((employee) table.getSelectionModel().getSelectedItem()).getEmp_id() + "";
                table t = database.getTableData("select * from employees where emp_id = " + idt + ";");
                id.setText(idt);
                name.setText(t.items[0][1].toString());
                phone.setText(t.items[0][2].toString());
                email.setText(t.items[0][3].toString());
                ssid.setText(t.items[0][4].toString());
                age.setText(t.items[0][5].toString());
                dob.setValue(LOCAL_DATE(t.items[0][6].toString()));
                certification.setText(t.items[0][7].toString());
                date_of_begin.setValue(LOCAL_DATE(t.items[0][8].toString()));
                String s = t.items[0][9].toString();
                if (s.equals("2030-01-01")) {
                    date_of_end.setValue(null);
                } else {
                    date_of_end.setValue(LOCAL_DATE(s));
                }

                salary.setText(t.items[0][10].toString());
                address.setText(t.items[0][11].toString());
                notes.setText(t.items[0][12].toString());
                username.setText(t.items[0][13].toString());
                password.setText(t.items[0][14].toString());
                repassword.setText(t.items[0][14].toString());
                table t2 = database.getTableData("select privilegesForm from privileges where emp_id=" + id.getText());
                int num = Integer.parseInt(database.getTableData("select count(privilegesForm) from privileges where emp_id=" + id.getText()).items[0][0].toString());
                userPrivileges.clear();
                mainForms.clear();
                mainForms.addAll(new String[]{"الموردين-الشركات", "المخازن", "الموظفين", "العملاء", " اضافه الاصناف",
                    "التفاصيل", "الاصناف ذات الكميه المنتهيه", "جرد الاصناف", "الاصناف", "متابعه الاقساط",
                    "نافذه البيع", "جميع الفواتير", "تسجيل واردات", "تسجيل مصروفات", "المرتجعات", "الجرد الكلى"});

                int i;
                for (i = 0; i < num; ++i) {
                    userPrivileges.add(t2.items[i][0].toString());
                }

                for (i = 0; i < mainForms.size(); ++i) {
                    for (int j = 0; j < userPrivileges.size(); ++j) {
                        if (((String) mainForms.get(i)).equals(userPrivileges.get(j))) {
                            mainForms.remove(i);
                        }
                    }
                }

                add.setDisable(true);
                delete.setDisable(false);
                update.setDisable(false);
            } catch (Exception var8) {
                tools.ErrorBox("هنالك خطأ ما ");
            }

        });
        ImageView i = new ImageView("images/back.jpg");
        i.setFitHeight(2200.0D);
        i.setFitWidth(1600.0D);
        HBox h1 = new HBox(10.0D, new Node[]{id, this.idLabel});
        HBox h2 = new HBox(10.0D, new Node[]{name, this.nameLabel});
        HBox h3 = new HBox(10.0D, new Node[]{phone, this.phoneLabel});
        HBox h4 = new HBox(10.0D, new Node[]{email, this.emailLabel});
        HBox h5 = new HBox(10.0D, new Node[]{ssid, this.ssidLabel});
        HBox h6 = new HBox(10.0D, new Node[]{age, this.ageLabel});
        HBox h7 = new HBox(10.0D, new Node[]{dob, this.dobLabel});
        HBox h8 = new HBox(10.0D, new Node[]{certification, this.certificationLabel});
        VBox topRight = new VBox(20.0D, new Node[]{h1, h2, h3, h4, h5, h6, h7, h8});
        HBox h9 = new HBox(10.0D, new Node[]{date_of_begin, this.date_of_beginLabel});
        HBox h10 = new HBox(10.0D, new Node[]{date_of_end, this.date_of_endLabel});
        HBox h11 = new HBox(10.0D, new Node[]{salary, this.salaryLabel});
        HBox h12 = new HBox(10.0D, new Node[]{address, this.addressLabel});
        HBox h13 = new HBox(10.0D, new Node[]{notes, this.notesLabel});
        HBox h14 = new HBox(10.0D, new Node[]{username, this.usernameLabel});
        h14.setAlignment(Pos.CENTER_RIGHT);
        HBox h15 = new HBox(10.0D, new Node[]{this.showPassword, password, this.passwordLabel});
        h15.setAlignment(Pos.CENTER_RIGHT);
        HBox h16 = new HBox(10.0D, new Node[]{repassword, this.repasswordLabel});
        h16.setAlignment(Pos.CENTER_RIGHT);
        VBox loginVB = new VBox(10.0D, new Node[]{h14, h15, h16});
        loginVB.setId("loginRequire");
        HBox login = new HBox(10.0D, new Node[]{loginVB});
        login.setAlignment(Pos.CENTER);
        VBox topLeft = new VBox(20.0D, new Node[]{h9, h10, h11, h12, h13});
        HBox allText_andLabels = new HBox(20.0D, new Node[]{topLeft, topRight});
        allText_andLabels.setId("lbl_txt_box");
        HBox ButtonsRow = new HBox(10.0D, new Node[]{add, update, delete});
        ButtonsRow.setId("three_btn");
        HBox allButtons = new HBox(10.0D, new Node[]{ButtonsRow, clear});
        allButtons.setId("btnbox");
        ImageView sIcon = new ImageView("images/search.png");
        sIcon.setOnMouseClicked((event) -> {
            try {
                int x = Integer.parseInt(search.getText());
                table.setItems(database.buildEmployeTable("select * from employees where emp_id ='" + x + "';"));
            } catch (Exception var4) {
                try {
                    table.setItems(database.buildEmployeTable("select * from employees where emp_name like  '%" + search.getText() + "%';"));
                } catch (Exception var3) {
                    tools.ErrorBox("Error");
                }
            }

        });
        HBox searchRow = new HBox(5.0D, new Node[]{search, sIcon});
        searchRow.setPadding(new Insets(0.0D, 20.0D, 0.0D, 251.0D));
        HBox tableRow = new HBox(new Node[]{table});
        VBox right = new VBox(5.0D, new Node[]{searchRow, tableRow});
        right.setAlignment(Pos.CENTER);
        TitledPane two = new TitledPane("جدول الموظفين", right);
        two.setAlignment(Pos.CENTER);
        BorderPane privilegesForm = new BorderPane();
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5.0D));
        gridpane.setHgap(10.0D);
        gridpane.setVgap(10.0D);
        ColumnConstraints column1 = new ColumnConstraints(150.0D, 150.0D, 1.7976931348623157E308D);
        ColumnConstraints column2 = new ColumnConstraints(50.0D);
        ColumnConstraints column3 = new ColumnConstraints(150.0D, 150.0D, 1.7976931348623157E308D);
        column1.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(new ColumnConstraints[]{column1, column2, column3});
        Label candidatesLbl = new Label("صلاحيات المستخدم");
        GridPane.setHalignment(candidatesLbl, HPos.CENTER);
        gridpane.add(candidatesLbl, 0, 0);
        Label selectedLbl = new Label("الصلاحيات");
        gridpane.add(selectedLbl, 2, 0);
        GridPane.setHalignment(selectedLbl, HPos.CENTER);
        gridpane.add(userPrivilegesListView, 0, 1);
        gridpane.add(mainListView, 2, 1);
        Button sendRightButton = new Button(" > ");
        sendRightButton.setOnAction((event) -> {
            String potential = (String) userPrivilegesListView.getSelectionModel().getSelectedItem();
            if (potential != null) {
                userPrivilegesListView.getSelectionModel().clearSelection();
                userPrivileges.remove(potential);
                mainForms.add(potential);
                p.setP_id(Integer.parseInt(p.getAutoNumber()));
                p.setEmp_id(Integer.parseInt(id.getText()));
                p.setPrivilegesForm(potential);
                p.delete();
            }

        });
        Button sendLeftButton = new Button(" < ");
        sendLeftButton.setOnAction((event) -> {
            String s = (String) mainListView.getSelectionModel().getSelectedItem();
            if (s != null) {
                mainListView.getSelectionModel().clearSelection();
                mainForms.remove(s);
                userPrivileges.add(s);
                p.setP_id(Integer.parseInt(p.getAutoNumber()));
                p.setEmp_id(Integer.parseInt(id.getText()));
                p.setPrivilegesForm(s);
                p.add();
            }

        });
        VBox vbox = new VBox(5.0D);
        vbox.getChildren().addAll(new Node[]{sendRightButton, sendLeftButton});
        gridpane.add(vbox, 1, 1);
        privilegesForm.setCenter(gridpane);
        GridPane.setVgrow(privilegesForm, Priority.ALWAYS);
        TitledPane three = new TitledPane(this.privileges.getText(), privilegesForm);
        three.setAlignment(Pos.CENTER);
        VBox h = new VBox(10.0D, new Node[]{allText_andLabels, login, allButtons});
        h.setAlignment(Pos.CENTER);
        TitledPane one = new TitledPane("اضافه او تعديل بيانات الموظف", h);
        one.setAlignment(Pos.CENTER);
        VBox left = new VBox(20.0D, new Node[]{one, two, three});
        left.setAlignment(Pos.CENTER);
        HBox center = new HBox(30.0D, new Node[]{left});
        this.tittle.setId("tittle");
        center.setAlignment(Pos.CENTER);
        VBox all = new VBox(10.0D, new Node[]{tittle, center});
        this.main = new StackPane(new Node[]{i, all});
        all.setAlignment(Pos.CENTER);
        root = new HBox(new Node[]{all});
        root.setId("root");
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add(this.getClass().getResource("/styleSheet/item.css").toExternalForm());
    }

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    void clear() {
        try {
            id.setText(this.e.getAutoNumber());
            name.setText("");
            phone.setText("");
            email.setText("");
            ssid.setText("");
            age.setText("");
            dob.setValue(null);
            certification.setText("");
            date_of_begin.setValue(null);
            date_of_end.setValue(null);
            salary.setText("");
            address.setText("");
            notes.setText("");
            username.setText("");
            password.setText("");
            password.setPromptText("ادخل كلمه المرور");
            repassword.setText("");
            search.setText("");
            add.setDisable(false);
            update.setDisable(true);
            delete.setDisable(true);
        } catch (Exception var2) {
            tools.ErrorBox("تحذير هنالك خطأ حدث");
        }

    }

    void setValues() {
        try {
            this.e.setEmp_id(Integer.parseInt(id.getText()));
            this.e.setEmp_name(name.getText());
            this.e.setEmp_phone(phone.getText());
            this.e.setEmp_email(email.getText());
            this.e.setEmp_ssid(ssid.getText());
            this.e.setEmp_age(age.getText());
            this.e.setEmp_dob(((LocalDate) dob.getValue()).toString());
            this.e.setEmp_certification(certification.getText());
            this.e.setEmp_soj(((LocalDate) date_of_begin.getValue()).toString());
            this.e.setEmp_eoj(date_of_end.getValue() == null ? "2030-01-01" : ((LocalDate) date_of_end.getValue()).toString());
            this.e.setEmp_salary((double) Integer.parseInt(salary.getText()));
            this.e.setEmp_address(address.getText());
            this.e.setEmp_notes(notes.getText());
            this.e.setEmp_username(username.getText());
            this.e.setEmp_password(password.getText());
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    boolean checkValues() {
        return !name.getText().equals("") && !ssid.getText().equals("") && !salary.getText().equals("") && !username.getText().equals("") && !password.getText().equals("") && date_of_begin.getValue() != null;
    }

    boolean checkPassword() {
        return password.getText().equals(repassword.getText());
    }

    static {
        mainListView = new ListView(mainForms);
        userPrivilegesListView = new ListView(userPrivileges);
    }
}
