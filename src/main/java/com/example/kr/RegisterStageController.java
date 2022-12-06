package com.example.kr;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Scanner;

public class RegisterStageController{
    private int lines;
    private int countAlert = 1;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private Button register;
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterStageController.class.getResource("RegisterStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 300);
//        stage.getIcons().add(new Image("C:\\Users\\abram\\Downloads\\registered.png"));
        stage.setTitle("Регистрация");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void registerClient() throws Exception {
        lines = getLineCount("Customers.txt");
        countAlert = 0;
        String log = login.getText();
        System.out.println(login);
        String pass = password.getText();

//        проверка на существующий логин если логин не существует тогда добавь +2 к счётчику строк

        File file = new File("Customers.txt");
        Charset charset = StandardCharsets.US_ASCII;
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)) {
            String line = null;

            for (int i = 0; i < lines; i++){
                line = reader.readLine();
                System.out.println(line);
                if (i == 0 || i % 2 == 0){
                    if (Objects.equals(line, log)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setContentText("Введённый вами логин существует.");
                        alert.showAndWait();
                        countAlert++;

                    }
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        if (Objects.equals(log, "") || Objects.equals(pass, "")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Одно из полей для ввода пустое. Введите логин и пароль.");
            alert.showAndWait();
            countAlert++;
        }

        if (countAlert == 0){
//            запись в файл логина и пароля и вывод сообщения, что вы успешно зарегистрировались

            BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt", true));
            writer.append("\n");
            writer.append(log);
            writer.append("\n");
            writer.append(pass);
            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешная регистрация");
            alert.setContentText("Вы успешно зарегистрировались");
            alert.showAndWait();
            HelloApplication helloApplication = new HelloApplication();
            Stage stage = (Stage) register.getScene().getWindow();
            helloApplication.start(stage);
        }
    }

    public static int getLineCount(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int lines = 0;

        while (scanner.hasNext()) {
            if (Objects.equals(scanner.nextLine(), "")){
                break;
            }
            lines++;
        }

        scanner.close();
        return lines;
    }
}
