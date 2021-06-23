package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Player;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Stage stage;
    private static OthelloController othelloController1;
    public static void setLoginController(OthelloController othelloController){
        othelloController1=othelloController;
    }

    @FXML
    private TextField player1user;

    @FXML
    private TextField player2user;
    @FXML
    private Button playBtn;
    @FXML
    private Label startError;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playBtn.setOnAction(e->{
            if(!(player1user.getText().isEmpty()||player2user.getText().isEmpty())) {
                if(!(player1user.getText().equals(player2user.getText()))) {
                    startError.setText("");
                    startError.setVisible(false);
                    Player player1 = new Player(player1user.getText(), 2);
                    Player player2 = new Player(player2user.getText(), 2);
                    othelloController1.makeButtonsSelectable();
                    othelloController1.setNames(player1, player2);
                    othelloController1.resetGame();
                    stage.close();
                }
                else {startError.setText("player names cant be same");
                    startError.setVisible(true);}
            }
            else{
             {if(player1user.getText().isEmpty()||player2user.getText().isEmpty()){
                startError.setText("set name for both players");
                startError.setVisible(true);
            }}
            }
        });
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
}