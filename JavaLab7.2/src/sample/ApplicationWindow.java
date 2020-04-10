package sample;

import com.sun.javafx.scene.control.InputField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class ApplicationWindow extends Application {

    public static Label extractedTokens1;
    public static Label extractedTokens2;
    public static Label extractedTokens3;
    public static Label availableTokens;
    public static Label turn;
    public static TextField tokenIndex;
    public static Label result;
    public static Label status;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //generating an array of 20 tokens which will be on the board at first
        ArrayList<Token> tokens = new ArrayList<>();
        var temp = IntStream.rangeClosed(1, 30).mapToObj(i -> new Token(i)).toArray(Token[]::new);
        for (Token t : temp)
            tokens.add(t);
        int progressionSize = 4; //self-explanatory
        int players = 3; //number of players to play the game
        int seconds = 10; //how long should the game last
        Board board = new Board(tokens, progressionSize, players);
        //added timekeeper to the game
        Game game = new Game(players, board, seconds);

//The primaryStage is the top-level container

        primaryStage.setTitle("Concurrent progression game");
//The BorderPane has the same areas laid out as the
//BorderLayout layout manager
        BorderPane componentLayout = new BorderPane();
        componentLayout.setPadding(new Insets(20,0,20,20));
//The FlowPane is a conatiner that uses a flow layout
        final FlowPane choicePane = new FlowPane();
        Label player1 = new Label("Player 1 - Manual ");
        Label player2 = new Label("Player 2 - Smart ");
        Label player3 = new Label("Player 3 - Random");
        choicePane.setPadding(new Insets(20,0,20,20));
        choicePane.setHgap(50);

//Add the players to the flowpane
        choicePane.getChildren().add(player1);
        choicePane.getChildren().add(player2);
        choicePane.getChildren().add(player3);
//put the flowpane in the top area of the BorderPane
        componentLayout.setTop(choicePane);

        final FlowPane listPane = new FlowPane();
        Label listLbl = new Label("Extracted tokens for player 1: ");
        extractedTokens1 = new Label("[ ]");
        Label listLbl2 = new Label("Extracted tokens for player 2: ");
        extractedTokens2 = new Label("[ ]");
        Label listLbl3 = new Label("Extracted tokens for player 3: ");
        extractedTokens3 = new Label("[ ]");
        Label availableTokensLabel = new Label("Available tokens: ");
        availableTokens = new Label("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]");
        turn = new Label("Turn: Player 1");
        listPane.setHgap(420);
        Label manualChoice = new Label("Chosen token index for the manual player: ");
        tokenIndex = new TextField("");
        tokenIndex.setPrefColumnCount(4);
        result = new Label(seconds + " seconds");
        status = new Label("");

        listPane.getChildren().add(listLbl);
        listPane.getChildren().add(extractedTokens1);
        listPane.getChildren().add(listLbl2);
        listPane.getChildren().add(extractedTokens2);
        listPane.getChildren().add(listLbl3);
        listPane.getChildren().add(extractedTokens3);
        listPane.getChildren().add(availableTokensLabel);
        listPane.getChildren().add(availableTokens);
        listPane.getChildren().add(turn);
        listPane.getChildren().add(manualChoice);
        listPane.getChildren().add(tokenIndex);
        listPane.getChildren().add(result);
        listPane.getChildren().add(status);

        componentLayout.setCenter(listPane);

//The button uses an inner class to handle the button click event
        Button vegFruitBut = new Button("Start game");
        vegFruitBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //result.setText("GO!");
                game.start();
            }
        });
        componentLayout.setBottom(vegFruitBut);
//Add the BorderPane to the Scene
        Scene appScene = new Scene(componentLayout,500,500);
//Add the Scene to the Stage
        primaryStage.setScene(appScene);
        primaryStage.show();
    }
}
