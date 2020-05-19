/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package re_book;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author AboodHassKov
 */
public class Book_ViewController implements Initializable {

    @FXML
    private TableView<Books> TableView_Book;
    @FXML
    private TableColumn<Books, String> Column_Title;
    @FXML
    private TableColumn<Books, String> Column_Atuher;
    @FXML
    private TableColumn<Books, String> Column_Ranking;
    @FXML
    private TableColumn<Books, Date> Column_R_Date;
    @FXML
    private TableColumn<Books, Short> Column_Price;
    @FXML
    private TextField TF_Title;
    @FXML
    private Button Button_Search;
    @FXML
    private Button Button_Buy;
    @FXML
    private Button Button_Rent;
    @FXML
    private TextField TF_Weeks_Rent;
    @FXML
    private ComboBox<String> CB_Ranking;
    
    Statement statement ;
    
    Alert a = new Alert(AlertType.CONFIRMATION);
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                java.sql.Connection connection =
                        DriverManager
                                .getConnection("jdbc:mysql://127.0.0.1:3306/rebook?serverTimezone=UTC","root","");
                this.statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException ex) {
            } 
        
            Column_Title.setCellValueFactory(new PropertyValueFactory("title"));
            Column_Atuher.setCellValueFactory(new PropertyValueFactory("author"));
            Column_R_Date.setCellValueFactory(new PropertyValueFactory("releaseDate"));
            Column_Ranking.setCellValueFactory(new PropertyValueFactory("ranking"));
            Column_Price.setCellValueFactory(new PropertyValueFactory("price"));
        try {
            show();
        } catch (SQLException ex) {
            Logger.getLogger(Book_ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableView_Book.
                    getSelectionModel().
                    selectedItemProperty().
                    addListener(event-> ShowSelected_Books());
    }    

    @FXML
    private void Search(ActionEvent event) throws SQLException {
        ResultSet RS = statement.executeQuery("Select * From books where title = '"+TF_Title.getText()+"'");
        TableView_Book.getItems().clear();
        while(RS.next()){
            Books book = new Books();
            book.setTitle(RS.getString("title"));
            book.setRanking(RS.getString("ranking"));
            book.setPrice(RS.getInt("price"));
            book.setReleaseDate(RS.getDate("release_date"));
            book.setAuthor(RS.getString("author"));
            TableView_Book.getItems().add(book);
        }
    }

    @FXML
    private void Buy(ActionEvent event) throws SQLException {
        
        if(!TF_Title.getText().isEmpty()){
        int user_id = TheLoginPageController.id;
        int book_id = 0;
        a.setContentText("Are You Sure to Buy This Book !!");
        
        ResultSet RS = statement.executeQuery("Select id From books where title = '"+TF_Title.getText()+"'");
        TableView_Book.getItems().clear();
        while(RS.next()){
            book_id = RS.getInt("id");
        }
        this.statement.executeUpdate("INSERT INTO buy_rent(user_id, book_id, Expiration,Operation_Type) VALUES ('"+user_id+"', '"+book_id+"', '"+0+"', 'Buy')");
        a.show();
        show();
        }else{
            Alert Wrong_Buy = new Alert(AlertType.WARNING);
            Wrong_Buy.setContentText("Insert Title of Book");
            Wrong_Buy.show();
        }
    }

    @FXML
    private void Rent(ActionEvent event) throws SQLException {
        
        if(!TF_Title.getText().isEmpty() && !TF_Weeks_Rent.getText().isEmpty()){
        int user_id = TheLoginPageController.id;
        int book_id = 0;
        int Expiration = Integer.parseInt(TF_Weeks_Rent.getText());
        a.setContentText("Are You Sure to Rent This Book of "+Expiration+" Week !!");
        ResultSet RS = statement.executeQuery("Select id From books where title = '"+TF_Title.getText()+"'");
        TableView_Book.getItems().clear();
        while(RS.next()){
            book_id = RS.getInt("id");
        }
        this.statement.executeUpdate("INSERT INTO buy_rent(user_id, book_id, Expiration,Operation_Type) VALUES ('"+user_id+"', '"+book_id+"', '"+Expiration+"', 'Rent')");
        a.show();
        show();
        }else{
            Alert Wrong_Rent = new Alert(AlertType.WARNING);
            Wrong_Rent.setContentText("Insert Title of Book and Number of Week Expiration");
            Wrong_Rent.show();
        }
    }


    @FXML
    private void Show(ActionEvent event) throws SQLException {
        show();
    }
    
    void show() throws SQLException{
        ResultSet RS = statement.executeQuery("Select * From books");
        TableView_Book.getItems().clear();
        while(RS.next()){
            Books book = new Books();
            book.setTitle(RS.getString("title"));
            book.setRanking(RS.getString("ranking"));
            book.setPrice(RS.getInt("price"));
            book.setReleaseDate(RS.getDate("release_date"));
            book.setAuthor(RS.getString("author"));
            TableView_Book.getItems().add(book);
        }
    }
    
    private void ShowSelected_Books() {
        Books selected_book =  TableView_Book.getSelectionModel().getSelectedItem();
        if(selected_book != null){
        TF_Title.setText(selected_book.getTitle());
        
    }
    
}
}
