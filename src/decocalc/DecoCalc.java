/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decocalc;

import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author creat0r
 */
public class DecoCalc extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Button btn = new Button();
        btn.setText("Hello World");
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);

        btn.setOnMouseClicked(event -> onClick());
        primaryStage.show();
    }

    private void onClick() {
        Dive dive = new Dive(60, 30, 100, 100);
        dive.writeAscendingPlan();
    }
}
