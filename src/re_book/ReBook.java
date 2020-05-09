/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package re_book;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author AboodHassKov
 */
public class ReBook extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane Admin_Control_Panel = FXMLLoader.load(getClass().getResource("Admin_Control_Panel.fxml"));
        
        Scene S = new Scene(Admin_Control_Panel);
        
        
        primaryStage.setTitle("Admin Control Panel !");
        primaryStage.setScene(S);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
