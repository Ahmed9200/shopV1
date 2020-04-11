package Designs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author danml
 */
public class MaterialLogin extends Application {

    public static Stage ps;

    @Override
    public void start(Stage stage) throws Exception {

        ps = new Stage();
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        ps.setScene(scene);
        ps.initStyle(StageStyle.DECORATED);
        ps.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
