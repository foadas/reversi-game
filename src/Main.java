import controller.LoginController;
import controller.OthelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(this.getClass().getResource("view/Othello.fxml"));
        loader.load();
        OthelloController othellocontroller= loader.getController();
        LoginController.giveController(othellocontroller);
        othellocontroller.makeButtonsUnSelectable();
        othellocontroller.s(primaryStage);
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(850);
        primaryStage.show();
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("view/Login.fxml"));
        fxmlLoader.load();
        Stage stage=new Stage();
        LoginController logincontroller=fxmlLoader.getController();
        logincontroller.setStage(stage);
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        stage.show();
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> primaryStage.close());
    }
}