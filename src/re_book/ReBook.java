/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package re_book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author AboodHassKov
 */
public class ReBook extends Application{
    static File Log = new File("Log.txt");
    static PrintWriter LogWriter;
    static FileOutputStream FOS;
    public static Scene S;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane Login = FXMLLoader.load(getClass().getResource("The_Login_Page.fxml"));
        FOS = new FileOutputStream(Log);
        LogWriter = new PrintWriter(FOS);
        LogWriter.println("The Application Begin\n");
//        LogWriter.close();
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
