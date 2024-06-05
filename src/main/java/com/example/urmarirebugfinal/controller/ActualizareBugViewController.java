package com.example.urmarirebugfinal.controller;

import com.example.urmarirebugfinal.domain.Bug;
import com.example.urmarirebugfinal.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizareBugViewController {

    Bug bug;
    Service serv;
    Stage stage;

    @FXML
    TextField denumire_fld;
    @FXML
    TextArea descriere_fld;

    public void initActualizareBugViewController(Bug bug, Service serv, Stage dialogStage) {
        this.serv = serv;
        this.stage = dialogStage;
        this.bug = bug;
    }

    public void handleActualizeaza(){
        String denumire_noua = denumire_fld.getText();
        String descriere_noua = descriere_fld.getText();
        Bug bug_nou = new Bug(bug.getId(), denumire_noua, descriere_noua, bug.getStare());
        serv.updateBug(bug_nou);
        MessageAlert.showErrorMessage(null, "Bug-ul a fost modificat !");

    }

    public void handleCancel(){
        stage.close();
    }
}
