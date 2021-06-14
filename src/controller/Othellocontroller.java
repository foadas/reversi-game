package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Othellocontroller implements Initializable {
    public static boolean [][]isok=new boolean[8][8];
    public static boolean [][]shouldOf=new boolean[8][8];
    public static int check=0;
    public static String [][]color=new String[8][8];
    public static Button [][]buttons=new Button[8][8];
    public static ImageView colorimage;
    public static Integer pointP1=0;
    public static Integer pointP2=0;
    public static int range=0;
    @FXML
    private Text point1;

    @FXML
    private Text point2;

    @FXML
    private Label winnerlbl;

    @FXML
    private Text winnertxt;

    @FXML
    private VBox field;
    public static String turn="blue";
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<8;i++){
            HBox hBox=new HBox();
            for (int j = 0; j <8 ; j++) {
                Button button=new Button("");
                color[i][j]="";
                if(i==3&&j==3){
                    Image playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                    color[i][j]="blue";
                    shouldOf[i][j]=true;
                    isok[i][j]=true;
                }
                if(i==3&&j==4){
                    Image playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                    color[i][j]="black";
                    shouldOf[i][j]=true;
                    isok[i][j]=true;
                }
                if(i==4&&j==3){
                    Image playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                    color[i][j]="black";
                    shouldOf[i][j]=true;
                    isok[i][j]=true;
                }
                if(i==4&&j==4){
                    Image playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                    color[i][j]="blue";
                    shouldOf[i][j]=true;
                    isok[i][j]=true;
                }
                button.setPrefHeight(43);
                button.setPrefWidth(43);
                button.setStyle("-fx-background-color: transparent");
                button.setStyle("-fx-background-color: #D2691E ");
                button.getStyleClass().add("button");
                button.getStylesheets().add("BackGround.css");
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
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                int a=i;
                int b=j;
                buttons[i][j].setOnAction(e->{
                    if(isok[a][b]==false) {
                        turnOff();
                        pointP1=0;
                        pointP2=0;
                        check=0;
                        checkup(a, b, turn);
                        checkdown(a, b, turn);
                        checkleft(a, b, turn);
                        checkright(a, b, turn);
                        checkDiameterupright(a, b, turn);
                        checkDiameterupleft(a, b, turn);
                        checkDiameterdownleft(a, b, turn);
                        checkDiameterDownRight(a, b, turn);
                        if (turn.equals("blue") && check > 0) {
                            shouldOf[a][b]=true;
                            turn = "black";
                        } else {
                            if (check > 0) {
                                shouldOf[a][b]=true;
                                turn = "blue";
                            }
                        }
                        checkingRange();
                        check=0;
                        calculatePoints();
                        point1.setText(pointP1.toString());
                        point2.setText(pointP2.toString());
                        if (pointP1+pointP2==64){
                            if (pointP1>pointP2) {
                                winnerlbl.setText("Winner");
                                winnertxt.setText("Player 1");
                            }
                            else if(pointP2>pointP1){
                                winnerlbl.setText("Winner");
                                winnertxt.setText("Player 2");
                            }
                            else{
                                winnerlbl.setText("Draw");
                            }
                        }
                    }
                });
            }
        }
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
                if (shouldOf[i][j]==false) {
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
                    checkDiameterdownleftRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkDiameterupleftRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                    checkDiameteruprightRange(i, j, turn);
                    if (check > 0) {
                        buttons[i][j].setStyle("-fx-background-color: gray");
                        range++;
                    }
                    check = 0;
                }
            }
        }
    }
    public void checkright(int i,int j,String turn){
        for(int k=j+2;k<8;k++){
            if(color[i][j+1].equals("")||color[i][j+1].equals(turn)){
                break;
            }
            if(color[i][k].equals(turn)){
                coloringright(i,j,k);
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
    public void checkleft(int i,int j,String turn){
        for(int k=j-2;k>=0;k--){
            if(color[i][j-1].equals("")||color[i][j-1].equals(turn)){
                break;
            }
            if(color[i][k].equals(turn)){
                coloringleft(i,j,k);
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
            if(color[i][k].equals(turn)){

                check++;
                break;
            }
        }
    }
    public void checkDiameterupright(int i,int j ,String turn){
        int k;
        int l=j+2;
        for(k=i-2;k>=0&&l<8;){
            if (color[i-1][j+1].equals("")||color[i-1][j+1].equals(turn))
                break;
            if(color[k][l].equals(turn)){
                coloringupright(i,j,k,l);
                check++;
                break;
            }
            k--;
            l++;
        }
    }
    public void checkDiameteruprightRange(int i,int j ,String turn) {
        int k;
        int l = j + 2;
        for (k = i - 2; k >= 0 && l < 8; ) {
            if (color[i - 1][j + 1].equals("") || color[i - 1][j + 1].equals(turn))
                break;
            if(color[k][l].equals(turn)){
                check++;
                break;
            }
            k--;
            l++;
        }
    }
    public void checkDiameterdownleft(int i,int j ,String turn){
        int k;
        int l=j-2;
        for(k=i+2;l>=0&&k<8;){
            if (color[i+1][j-1].equals("")||color[i+1][j-1].equals(turn))
                break;
            if(color[k][l].equals(turn)){
                coloringDownLeft(i,j,k,l);
                check++;
                break;
            }
            k++;
            l--;
        }
    }
    public void checkDiameterdownleftRange(int i,int j ,String turn){
        int k;
        int l=j-2;
        for(k=i+2;l>=0&&k<8;){
            if (color[i+1][j-1].equals("")||color[i+1][j-1].equals(turn))
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
            if(color[k][l].equals(turn)){
                coloringDownRight(i,j,k,l);
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
            if(color[k][l].equals(turn)){
                check++;
                break;
            }
            k++;
            l++;
        }
    }
    public void checkDiameterupleft(int i,int j ,String turn){
        int k;
        int l=j-2;
        for(k=i-2;k>=0&&l>=0;){
            if (color[i-1][j-1].equals("")||color[i-1][j-1].equals(turn))
                break;
            if(color[k][l].equals(turn)){
                coloringUpLeft(i,j,k,l);
                check++;
                break;
            }
            k--;
            l--;
        }
    }
    public void checkDiameterupleftRange(int i,int j ,String turn){
        int k;
        int l=j-2;
        for(k=i-2;k>=0&&l>=0;){
            if (color[i-1][j-1].equals("")||color[i-1][j-1].equals(turn))
                break;
            if(color[k][l].equals(turn)){
                check++;
                break;
            }
            k--;
            l--;
        }
    }
    public void coloringupright(int i,int j,int k,int l){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        int h;
        int g=j;
        for (h=i; h>=k&&g<l; ) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorimage);
            isok[h][g]=true;
            h--;
            g++;
        }
    }
    public void coloringUpLeft(int i,int j,int k,int l){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        int h;
        int g=j;
        for (h=i; h>=k&&g>=l; ) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorimage);
            isok[h][g]=true;
            h--;
            g--;
        }
    }
    public void coloringDownLeft(int i,int j,int k,int l){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        int h;
        int g=j;
        for (h=i; h<=k&&g>=l; ) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorimage);
            isok[h][g]=true;
            h++;
            g--;
        }
    }
    public void coloringDownRight(int i,int j,int k,int l){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        int h;
        int g=j;
        for (h=i; h<=k&&g<=l; ) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[h][g]=turn;
            buttons[h][g].setGraphic(colorimage);
            isok[h][g]=true;
            h++;
            g++;
        }
    }
    public void checkdown(int i ,int j, String turn){
        for(int k=i+2;k<8;k++){
            if(color[i+1][j].equals("")||color[i+1][j].equals(turn)){
                break;
            }
            if(color[k][j].equals(turn)){
                coloringdown(i,j,k);
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
            if(color[k][j].equals(turn)){
                coloringup(i,j,k);
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
            if(color[k][j].equals(turn)){
                check++;
                break;
            }
        }
    }
    public void coloringup(int i,int j,int k){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        for (int l = i; l >=k; l--) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[l][j]=turn;
            buttons[l][j].setGraphic(colorimage);
            isok[l][j]=true;
        }
    }
    public void coloringdown(int i,int j,int k){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        for (int l = i; l <=k; l++) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[l][j]=turn;
            buttons[l][j].setGraphic(colorimage);
            isok[l][j]=true;
        }
    }
    public void coloringright(int i,int j,int k){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        for (int l = j; l <=k; l++) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[i][l]=turn;
            buttons[i][l].setGraphic(colorimage);
            isok[i][l]=true;
        }
    }
    public void coloringleft(int i,int j,int k){
        Image playimage=null;
        if(turn.equals("black")) {
            playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        if(turn.equals("blue")) {
            playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
        }
        for (int l = j; l >=k; l--) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            color[i][l]=turn;
            buttons[i][l].setGraphic(colorimage);
            isok[i][l]=true;
        }
    }
}