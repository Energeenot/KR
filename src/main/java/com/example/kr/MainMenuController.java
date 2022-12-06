package com.example.kr;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MainMenuController{
    @FXML
    private Button search;
    @FXML
    private TextField category;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void onSearchButtonClick(){
        String categoryStr = category.getText();


        try(FileReader fileReader = new FileReader("Taxi.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()){
                if (categoryStr.equals(bufferedReader.readLine())){
                    System.out.println("Такси с подходящими критериями найден:");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Такси найдено: ");
                    alert.setContentText("Категория: " + categoryStr + ", \n" +
                                            "авто: " + bufferedReader.readLine() + ", \n" +
                                                "стаж вождения: " + bufferedReader.readLine());
                    alert.showAndWait();
                    System.out.println("Категория " + categoryStr);
//                    fio = bufferedReader.readLine();
//                    System.out.println("fio " + fio);
//                    name = bufferedReader.readLine();
//                    System.out.println("name " +  name);
//                    count = bufferedReader.readLine();
//                    System.out.println("Товары в заказе " + count);
                }
                else{
                    System.out.println("Данная категория не найдена");
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
