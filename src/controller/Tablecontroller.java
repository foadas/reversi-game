package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class Tablecontroller implements Initializable {

    public void getplayers(Player p1,Player p2){
        table.getItems().add(p1);
        table.getItems().add(p2);
    }
    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player,String> usercolumn;

    @FXML
    private TableColumn<Player,String> pointcolumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usercolumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        pointcolumn.setCellValueFactory(new PropertyValueFactory<>("point"));
    }
}
