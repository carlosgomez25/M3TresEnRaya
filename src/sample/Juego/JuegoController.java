package sample.Juego;


import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Juego.Juego;
import sample.Jugador.Jugador;
import sample.Controller.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class JuegoController implements Initializable {
    @FXML
    private Label labelJugador;
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private GridPane buttonsPanel;
    @FXML
    private Button startButton;
    @FXML
    private ToggleGroup elegir;

    Juego juego;
    static public List<Jugador> playerList = new ArrayList<>();
    public static Jugador jugador;
    private final String[] textRadioButton = {"vs PC", "PC vs PC", "vs Jugador Nº2"};
    private RadioButton[] radioButtons;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerList.add(jugador);
        labelJugador.setText(labelJugador.getText() + jugador.getNombre());
        textRadioButton[0] = jugador.getNombre() + " " + textRadioButton[0];
        textRadioButton[2] = jugador.getNombre() + " " + textRadioButton[2];
        radioButtons = new RadioButton[] {radioButton1, radioButton2, radioButton3};
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setText(textRadioButton[i]);
        }
    }


    public void empezar(ActionEvent actionEvent) {
        RadioButton selectedRadioButton = (RadioButton) elegir.getSelectedToggle();

        if (selectedRadioButton != null) {
            String id = selectedRadioButton.getId();
            int elegirModo = 0;
            for (int i = 0; i < radioButtons.length; i++) {
                if (id.equals(radioButtons[i].getId())) {
                    elegirModo = i + 1;
                }
            }

            juego = new Juego(elegirModo, this);
            for (Node button : buttonsPanel.getChildren()) {
                juego.buttons.add((Button) button);
            }
            juego.habilitarBotones();
            restartButtons();
            startButton.setVisible(false);
            if (elegirModo == 2) {
                juego.clickButton(null);
            }
        }
    }

    public void clickButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (!juego.comprobarBotonPulsado(button)) {
            juego.clickButton(button);
        }
    }

    private void restartButtons() {
        for (Button button : juego.buttons) {
            button.setText(null);
            button.getStyleClass().removeAll("buttons Ganadores");
        }
    }

    void reiniciarJuego() {
        juego.deshabilitarBotones();
        startButton.setVisible(true);
    }

    public void cambiarUsuario(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Menu/sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Controller.stage.setScene(scene);
        Controller.stage.setTitle("3 en raya");
        Controller.stage.setResizable(false);
    }

    public void exit(ActionEvent actionEvent) {
        new Controller().salir(actionEvent);
    }

}