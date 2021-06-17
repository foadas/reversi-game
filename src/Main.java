import controller.Othellocontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        Othellocontroller othellocontroller=(Othellocontroller) loader.getController();
        othellocontroller.s(primaryStage);
        //scene.getStylesheets().add(getClass().getResource("BackGround.css").toExternalForm());
        primaryStage.setScene(new Scene(loader.getRoot()));
        primaryStage.setMinWidth(930);
        primaryStage.setMinHeight(750);
        primaryStage.show();
    }
}