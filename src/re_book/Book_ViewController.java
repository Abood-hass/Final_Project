/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package re_book;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


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
    private TextField TF_Author;
    @FXML
    private TextField TF_Title;
    @FXML
    private TextField TF_R_D;
    @FXML
    private TextField TF_Days_Rent;
    @FXML
    private Button Button_Search;
    @FXML
    private Button Button_Buy;
    @FXML
    private Button Button_Rent;
//    @FXML
//    private ListView<?> List_Ranks;

    
    @FXML
    private ListView<?> List_Ranks;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Column_Title.setCellValueFactory(new PropertyValueFactory("title"));
            Column_Atuher.setCellValueFactory(new PropertyValueFactory("author"));
            Column_R_Date.setCellValueFactory(new PropertyValueFactory("release_date"));
            Column_Ranking.setCellValueFactory(new PropertyValueFactory("ranking"));
            Column_Price.setCellValueFactory(new PropertyValueFactory("price"));
    }    

    @FXML
    private void Search(ActionEvent event) {
        System.out.println("");
    }

    @FXML
    private void Buy(ActionEvent event) {
        System.out.println("");
    }

    @FXML
    private void Rent(ActionEvent event) {
        System.out.println("");
    }
    
    @FXML
    void Check_End_Date(MouseEvent event) {
        System.out.println("");
    }
    
    void show(){
        

    }
    
}
