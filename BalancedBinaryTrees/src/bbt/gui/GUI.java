package bbt.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button runButton = new Button("Run Test");
        TextArea testDescription = new TextArea();
        testDescription.setEditable(false);
        
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        
        VBox leftBox = new VBox();
        leftBox.setSpacing(10);
        leftBox.getChildren().add(runButton);
        leftBox.getChildren().add(testDescription);
        
        VBox rightBox = new VBox();
        rightBox.setSpacing(10);
        rightBox.getChildren().add(new Label("Test Results:"));
        rightBox.getChildren().add(outputArea);
        
        
        BorderPane components = new BorderPane();
        components.setLeft(leftBox);
        components.setRight(rightBox);
        
        Scene scene = new Scene(components);
        
        stage.setTitle("BalancedBinaryTrees");
        stage.setScene(scene);
        stage.show();
    }
    
    public GUI () {
        
    }
    
    
    public void open() {
        launch(GUI.class);
    }
}
