package notesapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    FlowPane mainContainerFlp;
    @FXML
    TextArea notaTxt;
    @FXML
    ColorPicker fondoClp;
    @FXML
    Button agregarBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.agregarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(notaTxt.getText().isEmpty()) return;
                addNote(notaTxt.getText(), fondoClp.getValue());
            }
        });
    }

    private void addNote(String text, Color color){
        VBox newNote = new VBox();
        newNote.setPrefWidth(250);
        newNote.setPadding(new Insets(6, 6, 6, 6));
        //newNote.setPrefSize(200,180);
        Label noteText = new Label(text);

        noteText.setWrapText(true);
        HBox hBox = new HBox();
        hBox.setSpacing(12);
        hBox.setPadding(new Insets(6, 10, 6, 10));
        hBox.setAlignment(Pos.CENTER_RIGHT);
        ColorPicker colorPicker = new ColorPicker(color);
        colorPicker.setPrefSize(46, 46);
        colorPicker.getStyleClass().add("color-pick");
        colorPicker.setStyle("-fx-background-color: #"+ color.toString().substring(2,8));

        Button borrarBtn = new Button();

        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // ((Node)event.getSource()).getParent().getParent().setStyle("-fx-background-color: #"+ ((ColorPicker) event.getSource()).getValue().toString().substring(2,8));
                newNote.setBackground( new Background(new BackgroundFill( colorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));

                //change colorpicker
                colorPicker.setStyle("-fx-background-color: #"+ colorPicker.getValue().toString().substring(2,8));
                borrarBtn.setStyle("-fx-background-color: #"+ colorPicker.getValue().toString().substring(2,8));

            }
        });

        borrarBtn.setPrefSize(34,34);
        //borrarBtn.getStyleClass().add("btn");
        //borrarBtn.getStyleClass().add("btn-danger");
        borrarBtn.getStyleClass().add("btn-remove");
        borrarBtn.setStyle("-fx-background-color: #"+ colorPicker.getValue().toString().substring(2,8));

        borrarBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*mainContainerFlp.getChildren().remove(
                ((Node)event.getSource()).getParent().getParent()
                );*/
                mainContainerFlp.getChildren().remove(newNote);
            }
        });

        hBox.getChildren().add(colorPicker);
        hBox.getChildren().add(borrarBtn);
        newNote.getChildren().add(noteText);
        newNote.getChildren().add(hBox);
        //newNote.setStyle("-fx-background-color: #"+ color.toString().substring(2,8));
        //System.out.println(color.toString() + " >>> " + color.toString().substring(2,8));
        newNote.setBackground( new Background(new BackgroundFill( color, CornerRadii.EMPTY, Insets.EMPTY)));
        mainContainerFlp.getChildren().add(newNote);
        this.notaTxt.setText("");
        this.notaTxt.requestFocus();

    }


}
