package controller;


import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Player;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OthelloController implements Initializable {
    public boolean [][] isOk =new boolean[8][8];
    public boolean [][]shouldOf=new boolean[8][8];
    public boolean endingprogram=false;
    public int check=0;
    public String [][]color=new String[8][8];
    public Button [][]buttons=new Button[8][8];
    public ImageView colorImage;
    public Integer pointP1=0;
    public Integer pointP2=0;
    public int range=0;
    public String turn="blue";
    public Player player1;
    public Player player2;
    public ArrayList<Player>playerList=new ArrayList<>();
    //private Tablecontroller tablecontroller=new Tablecontroller();
    @FXML
    private Text point1;
    @FXML
    private Text point2;
    @FXML
    private Text winnerTxt;
    @FXML
    private VBox field;
    @FXML
    private VBox scoreBox;
    @FXML
    private Label blueTurn;
    @FXML
    private Label blackTurn;
    @FXML
    private Button  bt;
    @FXML
    private Text player2Lbl;
    @FXML
    private Text player1Lbl;
    @FXML
    private Button scoreBoardbtn;
    @FXML
    private Button newGamebtn;
    Stage theStage;
    Image playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
    Image playImage2 = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blackTurn.setVisible(false);
        bt.setOnAction(event -> resetGame());
        scoreBox.heightProperty().addListener(observable -> {
            DoubleProperty mvh = scoreBox.prefHeightProperty();
            mvh.bind(Bindings.selectDouble(theStage.sceneProperty(),"height"));
            DoubleProperty mv = field.minHeightProperty();
            DoubleProperty m=field.minWidthProperty();
            mv.bind(Bindings.selectDouble(theStage.sceneProperty(),"height"));
            m.bind(Bindings.selectDouble(theStage.sceneProperty(),"width"));
        });
        startGame();
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                int a=i;
                int b=j;
                buttons[i][j].setOnAction(e->{
                    if(!isOk[a][b]) {
                        pointP1=0;
                        pointP2=0;
                        check=0;
                        checkup(a, b, turn);
                        checkDown(a, b, turn);
                        checkLeft(a, b, turn);
                        checkRight(a, b, turn);
                        checkDiameterUpright(a, b, turn);
                        checkDiameterUpLeft(a, b, turn);
                        checkDiameterDownLeft(a, b, turn);
                        checkDiameterDownRight(a, b, turn);
                        if (turn.equals("blue")) {
                            if (check>0) {
                                shouldOf[a][b] = true;
                                turnOff();
                                turn = "black";
                                blackTurn.setVisible(true);
                                blueTurn.setVisible(false);
                                if(endingprogram==true){
                                    endingprogram=false;
                                }
                            }
                        }
                        else if (turn.equals("black")){
                            if (check > 0) {
                                if(endingprogram==true){
                                    endingprogram=false;
                                }
                                shouldOf[a][b]=true;
                                turnOff();
                                turn = "blue";
                                blackTurn.setVisible(false);
                                blueTurn.setVisible(true);
                            }
                        }

                        checkingRange();
//                        check=0;
                        calculatePoints();
                        player1.setPoint(pointP1);
                        player2.setPoint(pointP2);
                        point1.setText(pointP1.toString());
                        point2.setText(pointP2.toString());
                        if(range==0) {
                            if (pointP1 + pointP2 == 64) {
                                if (pointP1 > pointP2) {
                                    winnerTxt.setText(player1Lbl.getText() + " is the winner!");
                                    //tablecontroller.getplayers(player1,player2);
                                    try {
                                        addToFile();
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    } catch (ClassNotFoundException classNotFoundException) {
                                        classNotFoundException.printStackTrace();
                                    }
                                } else if (pointP2 > pointP1) {
                                    winnerTxt.setText(player2Lbl.getText() + " is the winner!");
                                    //tablecontroller.getplayers(player1,player2);
                                    try {
                                        addToFile();
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    } catch (ClassNotFoundException classNotFoundException) {
                                        classNotFoundException.printStackTrace();
                                    }
                                } else {
                                    winnerTxt.setText("Draw!");
                                    //tablecontroller.getplayers(player1,player2);
                                    try {
                                        addToFile();
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    } catch (ClassNotFoundException classNotFoundException) {
                                        classNotFoundException.printStackTrace();
                                    }
                                }
                            }
                            else {
                                if (endingprogram == false) {
                                    endingprogram = true;
                                    if (turn.equals("blue")) {
                                        turn = "black";
                                        checkingRange();

                                    }
                                    else if (turn.equals("black")) {
                                        turn = "blue";
                                        checkingRange();
                                    }
                                }
                                else if(endingprogram==true){
                                    if (pointP1 > pointP2) {
                                        winnerTxt.setText(player1Lbl.getText() + " is the winner!");
                                        //tablecontroller.getplayers(player1,player2);
                                        try {
                                            addToFile();
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        } catch (ClassNotFoundException classNotFoundException) {
                                            classNotFoundException.printStackTrace();
                                        }
                                    } else if (pointP2 > pointP1) {
                                        winnerTxt.setText(player2Lbl.getText() + " is the winner!");
                                        //tablecontroller.getplayers(player1,player2);
                                        try {
                                            addToFile();
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        } catch (ClassNotFoundException classNotFoundException) {
                                            classNotFoundException.printStackTrace();
                                        }
                                    } else {
                                        winnerTxt.setText("Draw!");
                                        //tablecontroller.getplayers(player1,player2);
                                        try {
                                            addToFile();
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        } catch (ClassNotFoundException classNotFoundException) {
                                            classNotFoundException.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                        /*if (range==0||pointP1+pointP2==64){
                            blackTurn.setVisible(false);
                            blueTurn.setVisible(false);
                            if (pointP1>pointP2) {
                                winnerTxt.setText(player1Lbl.getText()+" is the winner!");
                                //tablecontroller.getplayers(player1,player2);
                                try {
                                    addToFile(player1,player2);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }
                            }
                            else if(pointP2>pointP1){
                                winnerTxt.setText(player2Lbl.getText()+" is the winner!");
                                //tablecontroller.getplayers(player1,player2);
                                try {
                                    addToFile(player1,player2);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }
                            }
                            else{
                                winnerTxt.setText("Draw!");
                                //tablecontroller.getplayers(player1,player2);
                                try {
                                    addToFile(player1,player2);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                } catch (ClassNotFoundException classNotFoundException) {
                                    classNotFoundException.printStackTrace();
                                }
                            }
                        }*/
                        range=0;
                    }
                });
            }
        }
        scoreBoardbtn.setOnAction(e->{
            FXMLLoader loader=new FXMLLoader(this.getClass().getResource("../view/Table.fxml"));
            try {
                loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage=new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            Tablecontroller tablecontroller1=loader.getController();
            tablecontroller1.getplayers(this,player1,player2);
            stage.show();
        });
        newGamebtn.setOnAction(e->{
            FXMLLoader loader=new FXMLLoader(this.getClass().getResource("../view/Login.fxml"));
            try {
                loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Stage stage=new Stage();
            Logincontroller logincontroller=loader.getController();
            stage.setScene(new Scene(loader.getRoot()));
            logincontroller.setStage(stage);
            stage.show();
        });
    }
    public void turnOff(){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                buttons[i][j].setStyle("-fx-background-color: transparent");
                buttons[i][j].setStyle("-fx-background-color: #D2691E ");
            }
        }
    }
    public void checkingRange(){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                if (!shouldOf[i][j]) {
                    check = 0;
                    checkLeftRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkRightRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkUpRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkDownRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkDiameterDownRightRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkDiameterDownLeftRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkDiameterUpLeftRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkDiameterUprightRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                }
            }
        }
    }
    public void checkRight(int i, int j, String turn){
        for(int k=j+2;k<8;k++){
            if(color[i][j+1].equals("")||color[i][j+1].equals(turn)){
                break;
            }
            if (color[i][k].equals(""))
                break;
            if(color[i][k].equals(turn)){
                coloringRight(i,j,k);
                shouldOf[i][j] = true;
                check++;
                break;
            }
        }
    }
    public void checkRightRange(int i,int j,String turn) {
        for (int k = j + 2; k < 8; k++) {
            if (color[i][j + 1].equals("") || color[i][j + 1].equals(turn)) {
                break;
            }
            if (color[i][k].equals(""))
                break;
            if(color[i][k].equals(turn)){
                check++;
                break;
            }
        }
    }
    public void calculatePoints(){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                if(color[i][j].equals("black"))
                    pointP2++;
                if (color[i][j].equals("blue"))
                    pointP1++;
            }
        }
    }
    public void checkLeft(int i, int j, String turn){
        for(int k=j-2;k>=0;k--){
            if(color[i][j-1].equals("")||color[i][j-1].equals(turn)){
                break;
            }
            if (color[i][k].equals(""))
                break;
            if(color[i][k].equals(turn)){
                coloringLeft(i,j,k);
                shouldOf[i][j] = true;
                check++;
                break;
            }
        }
    }
    public void checkLeftRange(int i,int j,String turn) {
        for (int k = j - 2; k >= 0; k--) {
            if (color[i][j - 1].equals("") || color[i][j - 1].equals(turn)) {
                break;
            }
            if (color[i][k].equals(""))
                break;
            if(color[i][k].equals(turn)){
                check++;
                break;
            }
        }
    }
    public void checkDiameterUpright(int i, int j , String turn){
        int k;
        int l=j+2;
        for(k=i-2;k>=0&&l<8;){
            if (color[i-1][j+1].equals("")||color[i-1][j+1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                coloringUpright(i,j,k,l);
                shouldOf[i][j] = true;
                check++;
                break;
            }
            k--;
            l++;
        }
    }
    public void checkDiameterUprightRange(int i, int j , String turn) {
        int k;
        int l = j + 2;
        for (k = i - 2; k >= 0 && l < 8; ) {
            if (color[i - 1][j + 1].equals("") || color[i - 1][j + 1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                check++;
                break;
            }
            k--;
            l++;
        }
    }
    public void checkDiameterDownLeft(int i, int j , String turn){
        int k;
        int l=j-2;
        for(k=i+2;l>=0&&k<8;){
            if (color[i+1][j-1].equals("")||color[i+1][j-1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                coloringDownLeft(i,j,k,l);
                shouldOf[i][j] = true;
                check++;
                break;
            }
            k++;
            l--;
        }
    }
    public void checkDiameterDownLeftRange(int i, int j , String turn){
        int k;
        int l=j-2;
        for(k=i+2;l>=0&&k<8;){
            if (color[i+1][j-1].equals("")||color[i+1][j-1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                check++;
                break;
            }
            k++;
            l--;
        }
    }
    public void checkDiameterDownRight(int i,int j ,String turn){
        int k;
        int l=j+2;
        for(k=i+2;l<8&&k<8;){
            if (color[i+1][j+1].equals("")||color[i+1][j+1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                coloringDownRight(i,j,k,l);
                shouldOf[i][j] = true;
                check++;
                break;
            }
            k++;
            l++;
        }
    }
    public void checkDiameterDownRightRange(int i,int j ,String turn){
        int k;
        int l=j+2;
        for(k=i+2;l<8&&k<8;){
            if (color[i+1][j+1].equals("")||color[i+1][j+1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                check++;
                break;
            }
            k++;
            l++;
        }
    }
    public void checkDiameterUpLeft(int i, int j , String turn){
        int k;
        int l=j-2;
        for(k=i-2;k>=0&&l>=0;){
            if (color[i-1][j-1].equals("")||color[i-1][j-1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                coloringUpLeft(i,j,k,l);
                shouldOf[i][j] = true;
                check++;
                break;
            }
            k--;
            l--;
        }
    }
    public void checkDiameterUpLeftRange(int i, int j , String turn){
        int k;
        int l=j-2;
        for(k=i-2;k>=0&&l>=0;){
            if (color[i-1][j-1].equals("")||color[i-1][j-1].equals(turn))
                break;
            if (color[k][l].equals(""))
                break;
            if(color[k][l].equals(turn)){
                check++;
                break;
            }
            k--;
            l--;
        }
    }
    public void coloringUpright(int i, int j, int k, int l){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        int h;
        int g=j;
        for (h=i; h>=k&&g<l; ) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorImage);
            isOk[h][g]=true;
            h--;
            g++;
        }
    }
    public void coloringUpLeft(int i,int j,int k,int l){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        int h;
        int g=j;
        for (h=i; h>=k&&g>=l; ) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorImage);
            isOk[h][g]=true;
            h--;
            g--;
        }
    }
    public void coloringDownLeft(int i,int j,int k,int l){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        int h;
        int g=j;
        for (h=i; h<=k&&g>=l; ) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorImage);
            isOk[h][g]=true;
            h++;
            g--;
        }
    }
    public void coloringDownRight(int i,int j,int k,int l){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        int h;
        int g=j;
        for (h=i; h<=k&&g<=l; ) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorImage);
            isOk[h][g]=true;
            h++;
            g++;
        }
    }
    public void checkDown(int i , int j, String turn){
        for(int k=i+2;k<8;k++){
            if(color[i+1][j].equals("")||color[i+1][j].equals(turn)){
                break;
            }
            if (color[k][j].equals(""))
                break;
            if(color[k][j].equals(turn)){
                coloringDown(i,j,k);
                shouldOf[i][j] = true;
                check++;
                break;
            }
        }
    }
    public void checkDownRange(int i ,int j, String turn){
        for(int k=i+2;k<8;k++){
            if(color[i+1][j].equals("")||color[i+1][j].equals(turn)){
                break;
            }
            if (color[k][j].equals(""))
                break;
            if(color[k][j].equals(turn)){
                check++;
                break;
            }
        }
    }
    public void checkup(int i,int j,String turn){
        for(int k=i-2;k>=0;k--){
            if(color[i-1][j].equals("")||color[i-1][j].equals(turn)){
                break;
            }
            if (color[k][j].equals(""))
                break;
            if(color[k][j].equals(turn)){
                coloringUp(i,j,k);
                shouldOf[i][j] = true;
                check++;
                break;
            }
        }
    }
    public void checkUpRange(int i,int j,String turn){
        for(int k=i-2;k>=0;k--){
            if(color[i-1][j].equals("")||color[i-1][j].equals(turn)){
                break;
            }
            if (color[k][j].equals(""))
                break;
            if(color[k][j].equals(turn)){
                check++;
                break;
            }
        }
    }
    public void coloringUp(int i, int j, int k){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        for (int l = i; l >=k; l--) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            color[l][j]=turn;
            buttons[l][j].setGraphic(colorImage);
            isOk[l][j]=true;
        }
    }
    public void coloringDown(int i, int j, int k){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        for (int l = i; l <=k; l++) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            color[l][j]=turn;
            buttons[l][j].setGraphic(colorImage);
            isOk[l][j]=true;
        }
    }
    public void coloringRight(int i, int j, int k){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        for (int l = j; l <=k; l++) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            color[i][l]=turn;
            buttons[i][l].setGraphic(colorImage);
            isOk[i][l]=true;
        }
    }
    public void coloringLeft(int i, int j, int k){
        Image playImage =null;
        if(turn.equals("black")) {
            playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        if(turn.equals("blue")) {
            playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
        }
        for (int l = j; l >=k; l--) {
            colorImage = new ImageView(playImage);
            colorImage.setFitHeight(45);
            colorImage.setFitWidth(45);
            buttons[i][k].setGraphic(colorImage);
            color[i][l]=turn;
            buttons[i][l].setGraphic(colorImage);
            isOk[i][l]=true;
        }
    }
    public void s(Stage stage){
        this.theStage=stage;
    }
    public void resetGame(){
        turn="blue";
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                shouldOf[i][j]=false;
                isOk[i][j]=false;
                color[i][j]="";
                buttons[i][j].setGraphic(null);
            }
        }
        Image playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
        colorImage = new ImageView(playImage);
        colorImage.setFitHeight(45);
        colorImage.setFitWidth(45);
        buttons[3][3].setGraphic(colorImage);
        color[3][3]="blue";
        shouldOf[3][3]=true;
        isOk[3][3]=true;

        playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
        colorImage = new ImageView(playImage);
        colorImage.setFitHeight(45);
        colorImage.setFitWidth(45);
        buttons[3][4].setGraphic(colorImage);
        color[3][4]="black";
        shouldOf[3][4]=true;
        isOk[3][4]=true;

        playImage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
        colorImage = new ImageView(playImage);
        colorImage.setFitHeight(45);
        colorImage.setFitWidth(45);
        buttons[4][3].setGraphic(colorImage);
        color[4][3]="black";
        shouldOf[4][3]=true;
        isOk[4][3]=true;
        playImage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
        colorImage = new ImageView(playImage);
        colorImage.setFitHeight(45);
        colorImage.setFitWidth(45);
        buttons[4][4].setGraphic(colorImage);
        color[4][4]="blue";
        shouldOf[4][4]=true;
        isOk[4][4]=true;
        turnOff();
        checkingRange();
        blackTurn.setVisible(false);
        blueTurn.setVisible(true);
        turn="blue";
        pointP2=2;
        pointP1=2;
        point1.setText(pointP1.toString());
        point2.setText(pointP2.toString());
        winnerTxt.setText("");

    }
    public void blackStart(Button button){
        colorImage = new ImageView(playImage2);
        colorImage.setFitHeight(45);
        colorImage.setFitWidth(45);
        button.setGraphic(colorImage);
    }
    public void blueStart(Button button){
        colorImage = new ImageView(playImage);
        colorImage.setFitHeight(45);
        colorImage.setFitWidth(45);
        button.setGraphic(colorImage);
    }
    public void startGame(){
        for(int i=0;i<8;i++){
            HBox hBox=new HBox();
            for (int j = 0; j <8 ; j++) {
                Button button=new Button("");
                color[i][j]="";
                if(i==3&&j==3){
                    blueStart(button);
                    color[i][j]="blue";
                    shouldOf[i][j]=true;
                    isOk[i][j]=true;
                }
                if(i==3&&j==4){
                    blackStart(button);
                    color[i][j]="black";
                    shouldOf[i][j]=true;
                    isOk[i][j]=true;
                }
                if(i==4&&j==3){
                    blackStart(button);
                    color[i][j]="black";
                    shouldOf[i][j]=true;
                    isOk[i][j]=true;
                }
                if(i==4&&j==4){
                    blueStart(button);
                    color[i][j]="blue";
                    shouldOf[i][j]=true;
                    isOk[i][j]=true;
                }
                button.setPrefHeight(70);
                button.setPrefWidth(70);
                button.setStyle("-fx-background-color: transparent");
                button.setStyle("-fx-background-color: #D2691E ");
                button.getStyleClass().add("button");
                buttons[i][j]=button;
                hBox.getChildren().add(button);
            }
            hBox.setSpacing(2);
            hBox.setAlignment(Pos.CENTER);
            field.getChildren().add(hBox);
        }
        field.setSpacing(2);
        field.setAlignment(Pos.CENTER);
        checkingRange();
        calculatePoints();
        point1.setText(pointP1.toString());
        point2.setText(pointP2.toString());
    }
    public void makebuttonsunselectable(){
        bt.setDisable(true);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <8 ; j++) {
                buttons[i][j].setDisable(true);
            }
        }
    }
    public void makebuttonsselectable(){
        bt.setDisable(false);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <8 ; j++) {
                buttons[i][j].setDisable(false);
            }
        }
    }
    public void setNames(Player p1, Player p2){
        player1Lbl.setText(p1.getUser()+":");
        player2Lbl.setText(p2.getUser()+":");
        player1=new Player();
        player1=p1;
        player2=new Player();
        player2=p2;

        //tablecontroller.getplayers(p1,p2);
    }
    public void addToFile() throws IOException, ClassNotFoundException {
        File f = new File("MyFile.txt");
        playerList.add(player1);
        playerList.add(player2);
        FileOutputStream fos = null;
        fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(playerList);
        oos.close();
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.close();
    }
}