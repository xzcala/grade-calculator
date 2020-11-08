/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradecalculator;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jon
 */
public class gradeCalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //elements
        
        Label label1 = new Label("Category 1 (30%): ");
        Label label2 = new Label("Category 2 (70%): ");
        Label label3 = new Label("My Final Score: ");
        
        TextField textField1 = new TextField();
        textField1.setPrefWidth(400);
        textField1.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observe, String oldInput, String newInput) {
            if (!newInput.matches("\\d*(\\.\\d*)?")) {
                textField1.setText(oldInput);
            }
        }
        });
        //this part is to only allow decimals in the text field, using a listener that is called when
        //something is inserted into the textfield, and checks whether or not it is a number. reference: stackoverflow.com
        
        
        TextField textField2 = new TextField();
        textField2.setPrefWidth(400);
        textField2.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observe, String oldInput, String newInput) {
            if (!newInput.matches("\\d*(\\.\\d*)?")) {
                textField2.setText(oldInput);
            }
        }
        });
        
        TextField textField3 = new TextField();
        textField3.setEditable(false); //makes that field uneditable
        textField3.setPrefWidth(400);
        
        Button maximize = new Button();
        maximize.setText("Maximize");
        maximize.setMaxWidth(Double.MAX_VALUE);
        
        Button calculate = new Button();
        calculate.setText("Calculate");
        calculate.setMaxWidth(Double.MAX_VALUE);
        
        Button clear = new Button();
        clear.setText("Clear");
        clear.setMaxWidth(Double.MAX_VALUE);
        
        Button alert = new Button();
        alert.setText("Alert");
        alert.setMaxWidth(Double.MAX_VALUE);
        
        //layout
        
        VBox buttonVBox = new VBox();
        buttonVBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonVBox.setSpacing(15);
        buttonVBox.setPadding(new Insets(0,0,15,0));
        buttonVBox.getChildren().addAll(maximize,calculate,clear, alert);
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER); 
        root.setHgap(10); 
        root.setVgap(10); 
        root.add(label1,0,0); 
        root.add(textField1,0,1);
        root.add(label2,0,2);
        root.add(textField2,0,3);
        root.add(label3,0,4);
        root.add(textField3,0,5);
        root.add(buttonVBox,0,6);
        
        //scene
        
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Grade Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //functions
        
        maximize.setOnAction((ActionEvent event)->{
            textField1.setText("100");
            textField2.setText("100");
        });
        
        calculate.setOnAction((ActionEvent event)->{
            Double score1 = Double.valueOf(textField1.getText()); //since the textfields are limited to only allow decimal values, 
            //all we have to do is get the value from the textfield itself and use those values to get the result
            Double score2 = Double.valueOf(textField2.getText());
            Double result = (score1*.3)+(score2*.7);
            DecimalFormat format = new DecimalFormat("#.00"); //to show two decimal places
            textField3.setText(format.format(result));
        });
        
        clear.setOnAction((ActionEvent event)->{
            textField1.clear();
            textField2.clear();
            textField3.clear();
        });
        
        alert.setOnAction((ActionEvent event)->{
            Alert box = new Alert(Alert.AlertType.INFORMATION);
            box.setTitle("Alert");
            box.setHeaderText(textField3.getText());
            box.showAndWait();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
