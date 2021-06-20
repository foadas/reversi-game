package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Player;
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
        /*FXMLLoader loader=new FXMLLoader(this.getClass().getResource("../view/Othello.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        playbtn.setOnAction(e->{
            if(!(player1user.getText().isEmpty()&&player2user.getText().isEmpty())) {
                Player player1 = new Player(player1user.getText(), 2);
                Player player2 = new Player(player2user.getText(), 2);
                othelloController1.makebuttonsselectable();
                othelloController1.setNames(player1, player2);
                stage.close();
            }
        });
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
}