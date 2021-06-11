package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Othellocontroller implements Initializable {
    public static boolean [][]isok=new boolean[8][8];
    public static String [][]color=new String[8][8];
    public static Button [][]buttons=new Button[8][8];
    public static ImageView colorimage;
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
                }
                if(i==3&&j==4){
                    Image playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                    color[i][j]="black";
                }
                if(i==4&&j==3){
                    Image playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                    color[i][j]="black";
                }
                if(i==4&&j==4){
                    Image playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                    color[i][j]="blue";
                }
                button.setPrefHeight(43);
                button.setPrefWidth(43);
                buttons[i][j]=button;
                hBox.getChildren().add(button);
            }
            hBox.setSpacing(2);
            hBox.setAlignment(Pos.CENTER);
            field.getChildren().add(hBox);
        }
        field.setSpacing(2);
        field.setAlignment(Pos.CENTER);
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                int a=i;
                int b=j;
                buttons[i][j].setOnAction(e->{
                    checkup(a,b,turn);
                    checkdown(a,b,turn);
                    if(turn.equals("black")){
                        turn="blue";
                    }else {turn="black";}
                });
            }
        }

    }

    public void checkdown(int i ,int j, String turn){
        for(int k=i+2;k<8;k++){
            if(color[k][j].equals(turn)){
                coloringdown(i,j,k);
                break;
            }
        }
    }
    public void checkup(int i,int j,String turn){
        for(int k=i-2;k>0;k--){
            if(color[k][j].equals(turn)){
                coloringup(i,j,k);
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
        for (int l = i; l >k; l--) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            buttons[l][j].setGraphic(colorimage);
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
        for (int l = i; l <k; l++) {
            colorimage = new ImageView(playimage);
            colorimage.setFitHeight(27);
            colorimage.setFitWidth(27);
            buttons[l][j].setGraphic(colorimage);
        }
    }

}
