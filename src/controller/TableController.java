package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Player;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    public void getPlayers(OthelloController othelloController,Player p1,Player p2) throws IOException {
        File f=new File("myFile.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            FileOutputStream b=new FileOutputStream(f);
            ObjectOutputStream outputStream=new ObjectOutputStream(b);
            outputStream.write(0);
        }
        try {
            if(ois!=null){
                othelloController.playerList = (ArrayList<Player>) ois.readObject();
                ois.close();}

        } catch (IOException ignored) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        sortList(othelloController.playerList);
        for (int i = 0; i <othelloController.playerList.size() ; i++) {
            table.getItems().add(othelloController.playerList.get(i));
        }

    }
    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player,String> userColumn;

    @FXML
    private TableColumn<Player,String> pointColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        pointColumn.setCellValueFactory(new PropertyValueFactory<>("point"));
    }
    private void sortList(ArrayList<Player>playerArrayList) {
        Collections.sort(playerArrayList, (player1, player2) -> {
            Long p2 = (long) player2.getPoint();
            Long p1 = (long) player1.getPoint();
            return p2.compareTo(p1);
        });
    }
}