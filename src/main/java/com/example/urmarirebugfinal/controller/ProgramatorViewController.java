package com.example.urmarirebugfinal.controller;

import com.example.urmarirebugfinal.MainUI;
import com.example.urmarirebugfinal.domain.Bug;
import com.example.urmarirebugfinal.domain.Programator;
import com.example.urmarirebugfinal.domain.StareBug;
import com.example.urmarirebugfinal.observer.IObserver;
import com.example.urmarirebugfinal.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProgramatorViewController implements IObserver<Bug> {
    @FXML
    Stage stage;
    Programator prog;
    Service serv;
    List<Programator> programatoriLogati;

    @FXML
    TableView<Bug> tabel_bug;
    @FXML
    TableColumn<Bug, Integer> id_bug;
    @FXML
    TableColumn<Bug, String> denumire_bug;
    @FXML
    TableColumn<Bug, String> descriere_bug;
    @FXML
    TableColumn<Bug, StareBug> stare_bug;

    private final ObservableList<Bug> obs_lst = FXCollections.observableArrayList();

    public void initProgramatorViewController(Programator prog, Service serv, Stage dialogStage, List<Programator> programatoriLogati) {
        this.serv = serv;
        this.stage = dialogStage;
        this.prog = prog;
        this.programatoriLogati = programatoriLogati;
        serv.addObserver(this);
        setBugTable();
        loadBugTable();
    }

    public void setBugTable(){
        id_bug.setCellValueFactory(new PropertyValueFactory<Bug, Integer>("id"));
        denumire_bug.setCellValueFactory(new PropertyValueFactory<Bug, String>("denumire"));
        descriere_bug.setCellValueFactory(new PropertyValueFactory<Bug, String>("descriere"));
        stare_bug.setCellValueFactory(new PropertyValueFactory<Bug, StareBug>("stare"));

        tabel_bug.setItems(obs_lst);
    }

    void loadBugTable(){
        List<Bug> bugs = serv.getAllBugs();
        tabel_bug.getItems().clear();
        for(Bug bug : bugs){
            tabel_bug.getItems().add(bug);
        }
        if(tabel_bug.getItems().size()>0)
            tabel_bug.getSelectionModel().select(0);
    }

    public void handleEliminaBug(){
        Bug bugSelectat = tabel_bug.getSelectionModel().getSelectedItem();
        if(bugSelectat!=null){
            serv.rezolvaBug(bugSelectat.getId());
        }
        else {
            MessageAlert.showErrorMessage(null, "Nu ai selectat niciun Bug din tabel !");
        }
    }

    public void handleActualizeazaBug(){
        try {
            Bug bugSelectat = tabel_bug.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(MainUI.class.getResource("actualizare-bug-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Actualizare Bug: " + bugSelectat.getDenumire());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            ActualizareBugViewController actualizareBugViewController = fxmlLoader.getController();
            actualizareBugViewController.initActualizareBugViewController(bugSelectat, serv, dialogStage);
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExit(){
        programatoriLogati.remove(prog);
        serv.removeObserver(this);
        stage.close();
    }

    @Override
    public void updateBug(Bug bug) {
        loadBugTable();
    }


}
