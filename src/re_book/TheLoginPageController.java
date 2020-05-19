/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package re_book;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author AboodHassKov
 */
public class TheLoginPageController implements Initializable {
    @FXML
    private TextField TextField_ID;
    @FXML
    private Button Button_login;
    @FXML
    private Button Button_clear;
    @FXML
    private PasswordField TextField_pass;
    @FXML
    private Button Button_login_Admin;
    @FXML
    private TextField TextField_Name;
    public static int id = 0;
    Statement statement ;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                java.sql.Connection connection =
                        DriverManager
                                .getConnection("jdbc:mysql://127.0.0.1:3306/rebook?serverTimezone=UTC","root","");
                this.statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException ex) {
            } 
    }    

    

    @FXML
    private void Login_Admin(ActionEvent event) throws SQLException, IOException{
        
        if(!TextField_ID.getText().isEmpty()
                && !TextField_pass.getText().isEmpty()
                && !TextField_Name.getText().isEmpty()){
            
            int TF_ID = Integer.parseInt(TextField_ID.getText());
            String TF_Pass = TextField_pass.getText()+"";
            String TF_Name = TextField_Name.getText()+"";
    
            ResultSet RS = this.statement.executeQuery("Select * from admins");
            while(RS.next()){
                int idA = RS.getInt("id");
                String Pass = RS.getString("pass");
                String Name = RS.getString("name");
                if(idA == TF_ID && Pass.equals(TF_Pass) && Name.equals(TF_Name)){
                    ReBook.LogWriter.println("The Admin "+TF_ID+" Login \n");
//                    ReBook.LogWriter.close();
                    Pane flowPane = FXMLLoader.load(getClass().getResource("Admin_Control_Panel.fxml"));
                    Stage dilog1 = new Stage();
                    Scene man1 = new Scene(flowPane);
                    dilog1.setScene(man1);
                    dilog1.setTitle("Admin Control Panel");
                    dilog1.show();
                }else{
                    
                }
                
                }
        }
        
        Clear();
    }
    
    @FXML
    void Login_User(ActionEvent event) throws SQLException, IOException {
        
    
        if(!TextField_pass.getText().isEmpty() && !TextField_Name.getText().isEmpty()){
            
            
    String TF_Pass = TextField_pass.getText();
    String TF_Name = TextField_Name.getText();
    
            ResultSet RS = this.statement.executeQuery("Select * from users");
            while(RS.next()){
                id = RS.getInt("id");
                String Name = RS.getString("name");
                String Pass = RS.getString("Password");
                if(Name.equals(TF_Name) 
                        && Pass.equals(md5Java(TF_Pass))
                        ){
                    ReBook.LogWriter.println("The User "+TF_Name+" Login \n");
//                    ReBook.LogWriter.close();
                    Pane flowPane = FXMLLoader.load(getClass().getResource("Book_View.fxml"));
                    Stage dilog1 = new Stage();
                    Scene man1 = new Scene(flowPane);
                    dilog1.setScene(man1);
                    dilog1.setTitle("Book Store");
                    dilog1.show();
                    
                }else{
                    System.out.println("Your not User");
                }
                
                }
        }
        
        Clear();

    }

    @FXML
    private void clear_fields(ActionEvent event) {
        Clear();
    }
    public void Clear(){
        TextField_ID.setText(null);
        TextField_pass.setText(null);
        TextField_Name.setText(null);
        }

    @Override
    public String toString() {
        return id+"";
    }
    
    public static String md5Java(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {

        }
        return digest;
    }
}
