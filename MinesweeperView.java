import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;


/**
 * @author caleb
 * @version 1.0
 */
public class MinesweeperView extends Application {
    /** Main Method.
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Step 1: Create Stage, Scene and Label Window.
        Scene myScene = getStartScreen(primaryStage);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    /** Scene for the Start Screen.
     * @param stage Primary Stage for the start screen
     * @return the scene to be added to the stage
     */
    public Scene getStartScreen(Stage stage) {
        ComboBox<String> dropDown = new ComboBox<>();
        dropDown.getItems().addAll("Easy", "Medium", "Hard");
        dropDown.setValue("Difficulty");
        dropDown.setOnAction(e -> {
            dropDown.setValue(dropDown.getValue());
            //System.out.println(dropDown.getValue());
        });

        //Step4: Create Title and StartButton
        Button startButton = new Button("START");
        Text title = new Text("Minesweeper");
        title.setFont(Font.font("Arial", 50));

        //BorderPane paneForName = new BorderPane();
        Text userEntry = new Text();
       // System.out.println(userEntry.getText());
        TextField nameField = new TextField();
        Text message = new Text("Enter Your Name:");
        nameField.setOnKeyPressed(e -> userEntry.setText(nameField.getText()));
        //System.out.println(userEntry.getText());

        VBox startLayout = new VBox(dropDown, title, startButton, message, nameField);
        startLayout.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(title, new Insets(100, 0, 0, 0));
        VBox.setMargin(startButton, new Insets(100, 0, 0, 0));
        VBox.setMargin(message, new Insets(100, 0, 0, 0));
        VBox.setMargin(nameField, new Insets(20, 100, 0, 100));


        startButton.setOnAction(e -> {
            stage.setScene(gameScene(userEntry.getText(), dropDown.getValue(), stage));
        });

        return new Scene(startLayout, 500, 500);
    }

    /**Scene for the Game Screen.
     * @param userEntry data for user typing their name
     * @param diffString string for the passed in difficulty
     * @param stage primary stage
     * @return the scene for either the won screen or game over scene
     */
    public Scene gameScene(String userEntry, String diffString, Stage stage) {
        Difficulty difficulty = null;
        if (diffString.equals("Difficulty") || userEntry.equals("")) {
            //alert code
            Alert myAlert = new Alert(Alert.AlertType.INFORMATION, "Invalid Inputs,"
                   + " PLease select a difficulty and enter your name.");
            myAlert.showAndWait();
        } else {
            switch (diffString) {
            case "Medium":
                difficulty = Difficulty.MEDIUM;
                break;
            case "Hard":
                difficulty = Difficulty.HARD;
                break;
            default:
                difficulty = Difficulty.EASY;
            }

            MinesweeperGame game = new MinesweeperGame(difficulty);
            //game.printBoard();
            //Creates Pane for Button Grid
            Tile[] emptyResult = new Tile[225];
            GridPane board = new GridPane();
            board.setHgap(9);
            board.setVgap(7.5);
            board.setPadding(new Insets(10, 0, 0, 8));
            //initial board
            Button[][] tile = new Button[15][15];
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    tile[i][j] = new Button("X");
                    tile[i][j].setUserData(0);
                    //System.out.println(tile[i][j].getUserData());
                    board.add(tile[i][j], i, j);
                }
            }

            //Scene firstScene = new Scene(board);
            //stage.setScene(firstScene);

            //Allows to track which button is pressed
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    Button myTile = tile[i][j];
                    int y = i;
                    int x = j;
                    myTile.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (myTile.getText().equals("X")) {
                                Tile[] result = game.check(y, x);
                                game.printBoard();
                                game.printBoardVisible();
                                System.out.println(result.length);
                                if (result.length == 1) {
                                    stage.setScene(gameOverScene(userEntry, stage));
                                } else if (game.isWon()) {
                                    stage.setScene(gameWonScene(userEntry, stage));
                                } else {
                                    for (int count = 0; count < result.length; count++) {
                                        int xIndex = result[count].getX();
                                        int yIndex = result[count].getY();
                                        //update actual button objects
                                        tile[xIndex][yIndex] = new
                                                Button(String.valueOf(result[count].getBorderingMines()));
                                        //update button visuals on the pane
                                        board.getChildren().remove(tile[xIndex][yIndex]);
                                        board.add(tile[xIndex][yIndex], xIndex, yIndex);

                                    }
                                }
                            }
                        }
                    });
                }
            }
            return new Scene(board, 500, 500);
        }



        return getStartScreen(stage);
    }

    /**Scene for GameOver Screen.
     * @param name name passed in from start screen
     * @param stage primary stage
     * @return new game scene
     */
    public Scene gameOverScene(String name, Stage stage) {
        VBox vbox = new VBox(10);
        String text = "You Lost, " + name;
        Label label = new Label(text);
        label.setWrapText(true);
        Button newGame = new Button("New Game");
        vbox.getChildren().addAll(label, newGame);
        newGame.setOnAction(new NewGameButton(stage));
        return new Scene(vbox, 100, 50);
    }

    /** Scene for Game Won Screen.
     * @param name name passed in from start screen
     * @param stage primary stage
     * @return new game screen
     */
    public Scene gameWonScene(String name, Stage stage) {
        VBox vbox2 = new VBox(10);
        String text = "Congratulations" + name;
        Label label2 = new Label(text);
        label2.setWrapText(true);
        Button newGame = new Button("New Game");
        vbox2.getChildren().addAll(label2, newGame);
        newGame.setOnAction(new NewGameButton(stage));
        return new Scene(vbox2, 100, 50);
    }

    /**Inner Class for new game Button.
     */
    private class NewGameButton implements EventHandler<ActionEvent> {
        private final Stage mainStage;
        NewGameButton(Stage stage) {
            mainStage = stage;
        }
        @Override
        public void handle(ActionEvent actionEvent) {
            mainStage.setScene(getStartScreen(mainStage));
        }
    }






}

