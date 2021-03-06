package sample.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Juego.JuegoController;
import sample.Jugador.Jugador;

import java.io.IOException;



public class Controller {
    private Scene scene;
    static public Stage stage;

    @FXML
    private TextField textFieldNombre;

    public void iniciarJuego(ActionEvent event) throws IOException {
        if (!textFieldNombre.getText().isBlank()) {
            updatePlayer();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Juego/juego.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Jugar");
            scene.getStylesheets().add("styles.css");
            stage.setResizable(false);
        }

        else {
            textFieldNombre.setStyle("-fx-text-box-border: #0c0c0c; -fx-focus-color: #0c0c0c;");
        }
    }

    public void salir(ActionEvent event) {
        Platform.exit();
    }


    private void updatePlayer() {
        JuegoController.jugador = new Jugador(textFieldNombre.getText());
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}