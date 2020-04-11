//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Tools;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class tools {
    public tools() {
    }

    public static void InformationBox(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void WarningBox(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void ErrorBox(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean confirmMsg(String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static Object inputBox(String tittle) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("نافذه ادخال البيانات");
        dialog.setHeaderText("ادخل البيانات");
        dialog.setContentText(tittle);
        Optional<String> result = dialog.showAndWait();
        return result.get();
    }

    public static class table {
        public int columns;
        public Object[][] items;

        public table(int columns) {
            this.columns = columns;
            this.items = new Object[0][columns];
        }

        public void addNewRow(Object[] row) {
            Object[][] tempItems = this.items;
            this.items = new Object[this.items.length + 1][this.columns];

            for(int x = 0; x < tempItems.length; ++x) {
                this.items[x] = tempItems[x];
            }

            this.items[this.items.length - 1] = row;
        }
    }
}
