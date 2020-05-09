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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AboodHassKov
 */
public class Admin_Control_PanelController implements Initializable {
    String Ranks[]={"Scientific" ,"Cultural" ,"entertainment" ,"Historical"};
    @FXML
    private TableView<Users> TV_Users;
        
    @FXML
    private TableColumn<Users, Integer> Column_ID_User;

    @FXML
    private TableColumn<Users, String> Column_Name_User;

    @FXML
    private TableColumn<Users, String> Column_Password_User;

    @FXML
    private TableColumn<Users, Integer> Column_Age_User;

    @FXML
    private TableColumn<Users, Integer> Column_Balance_User;

    @FXML
    private TableColumn<Users, Short> Column_BD_User;
    
    
    @FXML
    private TableView<Books> TV_Books;
    
    @FXML
    private TableColumn<Books, Integer> Column_ID_Book;

    @FXML
    private TableColumn<Books, String> Column_Title_Book;

    @FXML
    private TableColumn<Books, String> Column_Author_Book;

    @FXML
    private TableColumn<Books, String> Column_Ranking_Book;

    @FXML
    private TableColumn<Books, DatePicker> Column_RD_Book;

    @FXML
    private TableColumn<Books, Short> Column_Price_Book;
      @FXML
    private TextField TF_ID_Book;

    @FXML
    private TextField TF_Title_Book;

    @FXML
    private ComboBox<String> CM_Rank;
    
    @FXML
    private TextField TF_Author;
    
    @FXML
    private DatePicker DP_Release_Date;

    @FXML
    private TextField TF_Price;

    @FXML
    private Button Add_Book;

    @FXML
    private TextField TF_ID_User;

    @FXML
    private TextField TF_Name_User;

    @FXML
    private PasswordField TF_Password;

    @FXML
    private DatePicker DP_Birth_Date;

    @FXML
    private TextField TF_Balance;

    @FXML
    private Button Edit_Book;

    @FXML
    private Button Add_User;

    @FXML
    private Button Edit_User;

    @FXML
    private TextField TF_Age_User;
    
    Statement statement;
//    EntityManager em = emf.createEntityManager();
    
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
                                .getConnection("jdbc:mysql://127.0.0.1:3306/book_store?serverTimezone=UTC","root","");
                this.statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException ex) {
            }        
        CM_Rank.getItems().setAll(Ranks);
        Column_ID_User.setCellValueFactory(new PropertyValueFactory("id"));
        Column_Name_User.setCellValueFactory(new PropertyValueFactory("name"));
        Column_Balance_User.setCellValueFactory(new PropertyValueFactory("balance"));
        Column_BD_User.setCellValueFactory(new PropertyValueFactory("dateBirth"));
        Column_Age_User.setCellValueFactory(new PropertyValueFactory("age"));
        Column_Password_User.setCellValueFactory(new PropertyValueFactory("password"));
        
         TV_Books.
                    getSelectionModel().
                    selectedItemProperty().
                    addListener(event-> ShowSelected_Books());
         
         
         TV_Users.
                    getSelectionModel().
                    selectedItemProperty().
                    addListener(event-> ShowSelected_Users());
        try {
            ShowBooks();
            ShowUsers();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Control_PanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
     void Add_User(ActionEvent event) throws SQLException {
              int id = Integer.parseInt(TF_ID_User.getText());
         double B = Double.parseDouble(TF_Balance.getText());
         
        this.statement.executeUpdate("Insert into users Values"
                + "("+id+",'"+TF_Name_User.getText()+"','"+TF_Password.getText()+"','"+DP_Birth_Date.getValue()+"', "+TF_Age_User.getText()+","+B+")");
    
         
         try{
        ShowUsers();
        }catch(SQLException ex){
            ex.getMessage();
            
        }
         reset();
    }
    
    @FXML
     void Edit_User(ActionEvent event) {
         reset();
    }
    
    
    @FXML
     void Add_Book(ActionEvent event) throws SQLException {
        
         int id = Integer.parseInt(TF_ID_Book.getText());
         double Price = Double.parseDouble(TF_Price.getText());
         
        this.statement.executeUpdate("Insert into books Values"
                + "("+id+",'"+TF_Title_Book.getText()+"','"+TF_Author.getText()+"', '"+CM_Rank.getValue()+"','"+DP_Release_Date.getValue()+"',"+Price+")");
        reset();
     }
    
    @FXML
     void Edit_Book(ActionEvent event) {
    }
    
    void reset(){
        TF_Age_User.setText(null);
        DP_Birth_Date.setValue(null);
        TF_Password.setText(null);
        TF_Balance.setText(null);
        TF_Name_User.setText(null);
        TF_ID_User.setText(null);
        
        TF_ID_Book.setText(null);
        TF_Title_Book.setText(null);
        TF_Price.setText(null);
        TF_Author.setText(null);
        DP_Release_Date.setValue(null);
        CM_Rank.setValue(null);
    }
    
    void ShowUsers() throws SQLException{
        ResultSet RS;
        RS = statement.executeQuery("Select * From users");
        while(RS.next()){
            Users user = new Users();
            user.setId(RS.getInt("id"));
            user.setName(RS.getString("name"));
            user.setAge(RS.getInt("age"));
            user.setBalance(RS.getDouble("balance"));
            user.setDateBirth(RS.getDate("date_birth"));
            user.setPassword(RS.getString("Password"));
            TV_Users.getItems().add(user);
        }
    }
    void ShowBooks() throws SQLException{
         ResultSet RS;
        RS = statement.executeQuery("Select * From books");
        while(RS.next()){
            Books book = new Books();
            book.setId(RS.getInt("id"));
            book.setTitle(RS.getString("title"));
            book.setAuthor(RS.getString("author"));
            book.setPrice(RS.getDouble("Price"));
            book.setReleaseDate(RS.getDate("release_date"));
            book.setRanking(RS.getString("ranking"));
            TV_Books.getItems().add(book);
        }
    }
    
    
    private void ShowSelected_Users() {
        
        Users selected_user =  TV_Users.getSelectionModel().getSelectedItem();
        reset();
        TF_ID_User.setText(selected_user.getId()+"");
        TF_Name_User.setText(selected_user.getName());
        TF_Age_User.setText(selected_user.getAge()+"");
//        DP_Birth_Date.setValue(selected_user.getDateBirth());
        TF_Password.setText(selected_user.getPassword());
        TF_Balance.setText(selected_user.getBalance()+"");
        
    }
    
    private void ShowSelected_Books() {
        Books selected_book =  TV_Books.getSelectionModel().getSelectedItem();
        reset();
        TF_ID_Book.setText(selected_book.getId()+"");
        TF_Title_Book.setText(selected_book.getTitle());
        CM_Rank.setValue(selected_book.getRanking());
//        DP_Release_Date.setValue(selected_book.getReleaseDate()+"");
        TF_Author.setText(selected_book.getAuthor());
        TF_Price.setText(selected_book.getPrice()+"");
        
    }
    
    
}
