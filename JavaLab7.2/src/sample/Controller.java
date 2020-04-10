package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Controller {

    @FXML
    private Text availableTokens;

    @FXML
    private Text extractedTokens3;

    @FXML
    private Text extractedTokens1;

    @FXML
    private Text extractedTokens2;

    @FXML
    private Text gameOverMessage;

    @FXML
    public Button startButton;

    @FXML
    private TextField tokenIndex;

    public Text getAvailableTokens() {
        return availableTokens;
    }

    public void setAvailableTokens(Text availableTokens) {
        this.availableTokens = availableTokens;
    }

    public Text getExtractedTokens3() {
        return extractedTokens3;
    }

    public void setExtractedTokens3(Text extractedTokens3) {
        this.extractedTokens3 = extractedTokens3;
    }

    public Text getExtractedTokens1() {
        return extractedTokens1;
    }

    public void setExtractedTokens1(Text extractedTokens1) {
        this.extractedTokens1 = extractedTokens1;
    }

    public Text getExtractedTokens2() {
        return extractedTokens2;
    }

    public void setExtractedTokens2(Text extractedTokens2) {
        this.extractedTokens2 = extractedTokens2;
    }

    public Text getGameOverMessage() {
        return gameOverMessage;
    }

    public void setGameOverMessage(Text gameOverMessage) {
        this.gameOverMessage = gameOverMessage;
    }

    public TextField getTokenIndex() {
        return tokenIndex;
    }

    public void setTokenIndex(TextField tokenIndex) {
        this.tokenIndex = tokenIndex;
    }

}
