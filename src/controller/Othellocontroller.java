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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Othellocontroller implements Initializable {
    public static boolean [][]isok=new boolean[8][8];
    public static String [][]color=new String[8][8];
    public static Button [][]buttons=new Button[8][8];
    public static ImageView colorimage;
    @FXML
    private VBox field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<8;i++){
            HBox hBox=new HBox();
            for (int j = 0; j <8 ; j++) {
                Button button=new Button("");

                if(i==3&&j==3){
                    Image playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                }
                if(i==3&&j==4){
                    Image playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                }
                if(i==4&&j==3){
                    Image playimage = new Image(new File("src\\view\\image\\circular-filled-shape.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
                }
                if(i==4&&j==4){
                    Image playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
                    colorimage = new ImageView(playimage);
                    colorimage.setFitHeight(27);
                    colorimage.setFitWidth(27);
                    button.setGraphic(colorimage);
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
    }

    public void checkup(int i,int j,String turn){
        for(int k=i-1;k>=0;k--){
            System.out.println(1);
            if(color[k][j].equals(turn)){
                System.out.println(1);
                coloringup(i,j,k);

                break;
            }
        }
    }
    public void coloringup(int i,int j,int k){
        Image playimage = new Image(new File("src\\view\\image\\circle.png").toURI().toString());
        colorimage = new ImageView(playimage);
        colorimage.setFitHeight(27);
        colorimage.setFitWidth(27);
        for (int l = i; l >k; l--) {
            buttons[l][j].setGraphic(colorimage);
        }
    }
}
