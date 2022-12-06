package com.example.kr;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Scanner;

public class HelloController {
    @FXML
    private TextField login;
    @FXML
    private PasswordField  password;
    @FXML
    private Button enter;
    @FXML
    private Button newClient;
    private String currentLogin;
    private String currentPassword;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        String log = login.getText();
        System.out.println(log);
        String pass = password.getText();
        System.out.println(pass);

        if (Objects.equals(log, "admin") && Objects.equals(pass, "123")){
            MainMenuController mainMenuController = new MainMenuController();
            Stage stage = (Stage) enter.getScene().getWindow();
            mainMenuController.start(stage);

        }else{
            int lines = RegisterStageController.getLineCount("Customers.txt");
            File file = new File("Customers.txt");
            Charset charset = StandardCharsets.US_ASCII;
            try (BufferedReader reader = Files.newBufferedReader(file.toPath(), charset)) {
                String line = null;
                for (int i = 0; i < lines; i++){
                    line = reader.readLine();
                    if (i == 0 || i % 2 ==0){
                        currentLogin = line;
                    }
                    if (i % 2 != 0){
                        currentPassword = line;
                    }
                    if (!Objects.equals(currentLogin, log) & !Objects.equals(currentPassword, pass)) {
                        System.out.println("fck");
                    } else if (Objects.equals(currentLogin, log) & Objects.equals(currentPassword, pass)){
                        System.out.println("kayf");
                        MainMenuController mainMenuController = new MainMenuController();
                        Stage stage = (Stage) enter.getScene().getWindow();
                        mainMenuController.start(stage);
                        break;
                    }
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
    }

    @FXML
    private void onRegisterButtonClick() throws Exception {
        RegisterStageController registerStage = new RegisterStageController();
        Stage stage = (Stage) newClient.getScene().getWindow();
        registerStage.start(stage);
    }


}