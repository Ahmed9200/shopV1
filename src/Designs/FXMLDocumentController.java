package Designs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author danml
 */
public class FXMLDocumentController implements Initializable {

    public static String userId;

    @FXML
    private Label label;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField pass;

    @FXML
    private void Login(ActionEvent event) {

        if (Database.database.checkUserAndPassword(username.getText(), pass.getText())) {
            try {
                userId = username.getText();
                (new Designs.Home()).start(new Stage());
                new MaterialLogin().ps.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {
            Tools.tools.ErrorBox("Login Faild");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
