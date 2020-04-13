/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Designs;

import Designs.MainData.employeeForm;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Karam
 */
public class Home extends Application {

    public BorderPane root;
    public VBox top;
    public HBox topButtons, right;
    public ScrollPane buttonsTopScroll, scrollCenter;
    public MenuBar topMenuBar;
    public Button stock, home, installment, addItem, sellItem, clients, exit, barcode, inventory, outMoney;
    public StackPane sPane;

    public static Label priceNo = new Label("0");
    public static Label userAccount = new Label();

    @Override
    public void start(Stage primaryStage) {

        ImageView back = new ImageView("images/back.jpg");
        Rectangle2D psb = Screen.getPrimary().getVisualBounds();
        back.setFitHeight(psb.getHeight());
        back.setFitWidth(psb.getWidth());

        ImageView installmentImage = new ImageView("images/installment.png");
        ImageView addItemImage = new ImageView("images/addItem.png");
        ImageView sellItemImage = new ImageView("images/sellItem.png");
        ImageView representativeImage = new ImageView("images/representative.png");
        ImageView exitImage = new ImageView("images/exit.png");
        ImageView inventoryImage = new ImageView("images/inventory.png");
        ImageView outMoneyImage = new ImageView("images/outMoney.png");
        ImageView homeImage = new ImageView("images/home.png");
        ImageView stockImage = new ImageView("images/stock.png");

        home = new Button("الرجوع للرئيسيه", homeImage);
        home.setOnAction((event) -> {

            right.getChildren().clear();
            right.getChildren().add(back);
            right.setAlignment(Pos.CENTER);
            scrollCenter.setContent(right);
            HBox c = new HBox(scrollCenter);
            root.setCenter(scrollCenter);

        });

        installment = new Button("متابعه الاقساط", installmentImage);
        installment.setOnAction((event) -> {
            if (hasPrivligis("متابعه الاقساط")) {
                Designs.cashTransaction.installment s = new Designs.cashTransaction.installment();
                right = Designs.cashTransaction.installment.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });

        stock = new Button("المخازن", stockImage);
        stock.setOnAction((event) -> {
            if (hasPrivligis("المخازن")) {
                Designs.MainData.StockForms s = new Designs.MainData.StockForms();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        addItem = new Button("اضافه صنف", addItemImage);
        addItem.setOnAction((event) -> {
            if (hasPrivligis(" اضافه الاصناف")) {
                Designs.items.items s = new Designs.items.items();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        sellItem = new Button("نافذه البيع", sellItemImage);
        sellItem.setOnAction((event) -> {
            if (hasPrivligis("نافذه البيع")) {
                Designs.cashTransaction.bill s = new Designs.cashTransaction.bill();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        clients = new Button("العملاء", representativeImage);
        clients.setOnAction((event) -> {
            if (hasPrivligis("العملاء")) {
                Designs.MainData.customerForm s = new Designs.MainData.customerForm();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        outMoney = new Button("مصروفات", outMoneyImage);
        outMoney.setOnAction((event) -> {
            if (hasPrivligis("تسجيل مصروفات")) {
                Designs.cashTransaction.outMoney s = new Designs.cashTransaction.outMoney();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        inventory = new Button("جرد", inventoryImage);
        inventory.setOnAction((event) -> {
            if (hasPrivligis("الجرد الكلى")) {
                Designs.cashTransaction.inventory s = new Designs.cashTransaction.inventory();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        exit = new Button("خروج", exitImage);
        exit.setOnAction((event) -> {
            primaryStage.close();
        });

        topButtons = new HBox(home, stock, installment, addItem, sellItem, clients, outMoney, inventory, exit);
        topButtons.setId("Buttons");
        buttonsTopScroll = new ScrollPane(topButtons);
        buttonsTopScroll.setMinHeight(100);

        topMenuBar = new MenuBar();
        topMenuBar.setId("bar");

        Menu mainDataMenu = new Menu("البيانات الرئيسية");
        Menu itemsMenu = new Menu("الاصناف");
        Menu cashMenu = new Menu("الحسابات");
        Menu settingsMenu = new Menu("الاعدادات");

        MenuItem workersItem = new MenuItem("العملاء");
        workersItem.setOnAction((event) -> {
            if (hasPrivligis("العملاء")) {
                Designs.MainData.customerForm s = new Designs.MainData.customerForm();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem suppliersItem = new MenuItem("الموردين-الشركات");
        suppliersItem.setOnAction((event) -> {
            if (hasPrivligis("الموردين-الشركات")) {
                Designs.MainData.companiesForm s = new Designs.MainData.companiesForm();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem globalItemsItem = new MenuItem("الاصناف");
        globalItemsItem.setOnAction((event) -> {
            if (hasPrivligis("الاصناف")) {
                Designs.MainData.categoryForms s = new Designs.MainData.categoryForms();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem stockItem = new MenuItem("المخازن");
        stockItem.setOnAction((event) -> {
            if (hasPrivligis("المخازن")) {
                Designs.MainData.StockForms s = new Designs.MainData.StockForms();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem employeesItem = new MenuItem("الموظفين");
        employeesItem.setOnAction((event) -> {
            if (hasPrivligis("الموظفين")) {
                Designs.MainData.employeeForm s = new Designs.MainData.employeeForm();
                right = employeeForm.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });

        //-----------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------
        MenuItem inItemPermissionItem = new MenuItem(" اضافه الاصناف");
        inItemPermissionItem.setOnAction((event) -> {
            if (hasPrivligis(" اضافه الاصناف")) {
                Designs.items.items s = new Designs.items.items();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem outItemPermissionItem = new MenuItem("الاصناف ذات الكميه المنتهيه");
        outItemPermissionItem.setOnAction((event) -> {
            if (hasPrivligis("الاصناف ذات الكميه المنتهيه")) {
                Designs.items.outOfQuantityItems s = new Designs.items.outOfQuantityItems();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem itemInventoryItem = new MenuItem("جرد الاصناف");
        itemInventoryItem.setOnAction((event) -> {
            if (hasPrivligis("جرد الاصناف")) {
                Designs.items.allItemsForm s = new Designs.items.allItemsForm();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        //-----------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------

        MenuItem sellPointItem = new MenuItem("نقطه البيع");
        sellPointItem.setOnAction((event) -> {
            if (hasPrivligis("نافذه البيع")) {
                Designs.cashTransaction.bill s = new Designs.cashTransaction.bill();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem inMoneyItem = new MenuItem("تسجيل واردات");
        inMoneyItem.setOnAction((event) -> {
            if (hasPrivligis("تسجيل واردات")) {
                Designs.cashTransaction.inMoney s = new Designs.cashTransaction.inMoney();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem outMoneyItem = new MenuItem("تسجيل مصروفات");
        outMoneyItem.setOnAction((event) -> {
            if (hasPrivligis("تسجيل مصروفات")) {
                Designs.cashTransaction.outMoney s = new Designs.cashTransaction.outMoney();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem installmentItem = new MenuItem("متابعه الاقساط");
        installmentItem.setOnAction((event) -> {
            if (hasPrivligis("متابعه الاقساط")) {
                Designs.cashTransaction.installment s = new Designs.cashTransaction.installment();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        MenuItem inventoryItem = new MenuItem("الجرد الكلى");
        inventoryItem.setOnAction((event) -> {
            if (hasPrivligis("الجرد الكلى")) {
                Designs.cashTransaction.inventory s = new Designs.cashTransaction.inventory();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });

        MenuItem allBillsItem = new MenuItem("جميع الفواتير");
        allBillsItem.setOnAction((event) -> {
            if (hasPrivligis("جميع الفواتير")) {
                Designs.cashTransaction.allBills s = new Designs.cashTransaction.allBills();
                right = s.root;
                StackPane sp = new StackPane(right);
                sp.setAlignment(Pos.CENTER);
                scrollCenter.setContent(sp);
            } else {
                Tools.tools.ErrorBox("لا يوجد صلاحيه للدخول");
            }
        });
        //-----------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------

        MenuItem backupItem = new MenuItem("نسخه احتياطيه");
        backupItem.setOnAction((event) -> {

            Process p = null;

            try {
                Runtime runtime = Runtime.getRuntime();
                File f = new File("C:\\Program Files (x86)\\MySQL\\MySQL Server 5.0\\bin\\mysqldump.exe");
                System.out.println(f.getAbsolutePath());
                p = runtime.exec(f.getAbsolutePath() + " -uroot -p381998SohailaKaram --add-drop-database -B shop -r" + (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()) + ".sql");
                int processComplete = p.waitFor();
                if (processComplete == 0) {
                    Tools.tools.InformationBox("تمت العمليه بنجاح");
                } else {
                    Tools.tools.WarningBox("هناك خطأ ما حاول مره اخرى");
                }
            } catch (Exception var6) {
            }

        });
        MenuItem logoutItem = new MenuItem("خروج من النظام");
        logoutItem.setOnAction((event) -> {
            primaryStage.close();
        });
        //-----------------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------------------------

        mainDataMenu.getItems().addAll(stockItem, globalItemsItem, workersItem, suppliersItem, employeesItem);
        itemsMenu.getItems().addAll(inItemPermissionItem, outItemPermissionItem, itemInventoryItem);
        cashMenu.getItems().addAll(sellPointItem, allBillsItem, inMoneyItem, outMoneyItem, installmentItem, inventoryItem);
        settingsMenu.getItems().addAll(backupItem, logoutItem);

        topMenuBar.getMenus().addAll(mainDataMenu, itemsMenu, cashMenu, settingsMenu);

        Label billsPrice_lbl = new Label();

        VBox box2 = new VBox(5, billsPrice_lbl, priceNo);
        box2.setAlignment(Pos.CENTER);

        HBox r = new HBox(30, box2);
        HBox userBox = new HBox(userAccount);
        userAccount.setStyle("-fx-font-size:18;-fx-text-fill: red;");
        priceNo.setStyle("-fx-font-size:18;-fx-text-fill: blue;");
        userBox.setAlignment(Pos.CENTER);
        HBox topRoot = new HBox(50, userBox, r);
        topRoot.setAlignment(Pos.CENTER);

        box2.setId("labelBox");

        TitledPane details = new TitledPane("تفاصيل", topRoot);
        details.setExpanded(false);
        details.expandedProperty().addListener((observable) -> {
            try {

                if (details.isExpanded()) {
                    if (hasPrivligis("التفاصيل")) {
                        details.setExpanded(true);

                    } else {
                        details.setExpanded(false);
                        Tools.tools.ErrorBox("ليس له صلاحيه");
                    }
                }

            } catch (Exception e) {
            }
        });
        details.setOnMouseClicked((MouseEvent event) -> {
            billsPrice_lbl.setText("المبلغ من الفواتير حتى الان");
            userAccount.setText(Designs.FXMLDocumentController.userId);
        });

        top = new VBox(10, topMenuBar, new TitledPane("اختصارات", buttonsTopScroll), details);

        right = new HBox();
        right.setAlignment(Pos.CENTER);
        sPane = new StackPane(back, right);
        sPane.setAlignment(Pos.CENTER);
        scrollCenter = new ScrollPane(sPane);
        root = new BorderPane();
        root.setCenter(scrollCenter);

        root.setTop(top);
        root.setId("right");
        Scene scene = new Scene(root, psb.getWidth(), psb.getHeight());
        scene.getStylesheets().add(this.getClass().getResource("/styleSheet/HomeCSS.css").toExternalForm());

        primaryStage.setTitle("الشاشه الرئيسيه");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private boolean hasPrivligis(String pageName) {

        String username = Designs.FXMLDocumentController.userId;
        String id = Database.database.getTableData("select emp_id from employees where emp_username = '" + username + "'").items[0][0].toString();

        for (int i = 0; i < Database.database.getTableData("select privilegesForm from privileges where emp_id =" + id).items.length; i++) {
            if (pageName.equals(Database.database.getTableData("select privilegesForm from privileges where emp_id =" + id).items[i][0])) {
                return true;
            }
        }
        return false;
    }

}
