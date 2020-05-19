/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package re_book;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
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
    public static Scene S;
    public static Pane Book_View ;
    public static Pane Admin_Control_Panel ;
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
        Admin_Control_Panel = FXMLLoader.load(getClass().getResource("Admin_Control_Panel.fxml"));
        Pane Login = FXMLLoader.load(getClass().getResource("The_Login_Page.fxml"));
        Book_View = FXMLLoader.load(getClass().getResource("Book_View.fxml"));
        
        
        
        S = new Scene(Login);
        
        
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
