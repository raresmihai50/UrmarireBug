package com.example.urmarirebugfinal.controller;

import com.example.urmarirebugfinal.domain.Bug;
import com.example.urmarirebugfinal.domain.Verificator;
import com.example.urmarirebugfinal.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class VerificatorViewController {
    @FXML
    Stage stage;
    Verificator verif;
    Service serv;
    List<Verificator> verifLogati;


    @FXML
    TextField denumire_fld;
    @FXML
    TextArea descriere_fld;


    public void initVerificatorViewController(Verificator verif, Service serv, Stage dialogStage, List<Verificator> verifLogati) {
        this.serv = serv;
        this.stage = dialogStage;
        this.verif = verif;
        this.verifLogati = verifLogati;
    }

    public void handleExit(){
        verifLogati.remove(verif);
        stage.close();
    }

    public void handleInregistreazaBug(){
        String denumire = denumire_fld.getText();
        String descriere = descriere_fld.getText();
        Bug bug_nou = serv.saveBug(denumire, descriere);
        if (bug_nou != null){
            MessageAlert.showErrorMessage(null, "S-a adaugat bugul cu denumire " + bug_nou.getDenumire() + " !");
        }
    }


}
