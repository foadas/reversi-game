package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Logincontroller implements Initializable {
    private Stage stage;
    private static OthelloController othelloController1;
    public static void Logincontroller(OthelloController othelloController){
        othelloController1=othelloController;
    }
    @FXML
    private AnchorPane loginfield;

    @FXML
    private TextField player1user;

    @FXML
    private TextField player2user;
    @FXML
    private Button playbtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player player1=new Player(player1user.getText(),2);
        Player player2=new Player(player2user.getText(),2);
        playbtn.setOnAction(e->{
            othelloController1.makebuttonsselectable();
            stage.close();
        });
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
}
