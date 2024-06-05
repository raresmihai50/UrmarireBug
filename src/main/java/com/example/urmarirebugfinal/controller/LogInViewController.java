package com.example.urmarirebugfinal.controller;

import com.example.urmarirebugfinal.MainUI;
import com.example.urmarirebugfinal.domain.Programator;
import com.example.urmarirebugfinal.domain.Verificator;
import com.example.urmarirebugfinal.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class LogInViewController {
    @FXML
    TextField txt_fld_username;

    Service serv;

    List<Programator> programatoriLogati = new ArrayList<>();

    List<Verificator> verificatoriLogati = new ArrayList<>();

    public void initLoginController(Service serv) {
        this.serv = serv;
    }

    @FXML
    public void handleExit() {
        exit(0);
    }

    @FXML
    public void handleLogInVerificator() {
        String username = txt_fld_username.getText();
        Verificator verif = serv.getOneVerif(username);
        if (verif != null) {
            if(!verificatoriLogati.contains(verif)){
                verificatoriLogati.add(verif);
                showVerificatorWindow(verif);
            }
            else {
                MessageAlert.showErrorMessage(null, "Acest Verificator este deja logat !");
            }

        } else {
            MessageAlert.showErrorMessage(null, "Nu exista acest Verificator !");
        }
    }

    @FXML
    public void handleLogInProgramator() {
        String username = txt_fld_username.getText();
        Programator prog = serv.getOneProg(username);
        if (prog != null) {
            if(!programatoriLogati.contains(prog)){
                programatoriLogati.add(prog);
                showProgramatorWindow(prog);
            }
            else {
                MessageAlert.showErrorMessage(null, "Acest Programator este deja logat !");
            }

        } else {
            MessageAlert.showErrorMessage(null, "Nu exista acest Programator !");
        }

    }

    @FXML
    public void showVerificatorWindow(Verificator verif) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainUI.class.getResource("verificator-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Verificator " + verif.getUsername());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            VerificatorViewController verificatorViewController = fxmlLoader.getController();
            verificatorViewController.initVerificatorViewController(verif, serv, dialogStage, verificatoriLogati);
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showProgramatorWindow(Programator prog) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainUI.class.getResource("programator-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 890, 400);
            stage.setScene(scene);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Programator " + prog.getUsername());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            ProgramatorViewController programatorViewController = fxmlLoader.getController();
            programatorViewController.initProgramatorViewController(prog, serv, dialogStage, programatoriLogati);
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stergeVerificatorLogat(String username){
        verificatoriLogati.remove(serv.getOneVerif(username));
    }
}
