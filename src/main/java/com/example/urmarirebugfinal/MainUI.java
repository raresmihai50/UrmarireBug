package com.example.urmarirebugfinal;

import com.example.urmarirebugfinal.controller.LogInViewController;
import com.example.urmarirebugfinal.domain.Bug;
import com.example.urmarirebugfinal.domain.Programator;
import com.example.urmarirebugfinal.domain.StareBug;
import com.example.urmarirebugfinal.repository.BugORMRepository;
import com.example.urmarirebugfinal.repository.ProgramatorORMRepository;
import com.example.urmarirebugfinal.repository.VerificatorORMRepository;
import com.example.urmarirebugfinal.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class MainUI extends Application {
    private static SessionFactory sessionFactory;

    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    private static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    public void start(Stage stage) throws IOException {

        /*Properties props = new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }*/
        //ProgramatorRepo progRepo = new ProgramatorRepo(props);
        //VerificatorRepo verifRepo = new VerificatorRepo(props);
        VerificatorORMRepository verifRepo = new VerificatorORMRepository(sessionFactory);
        ProgramatorORMRepository progRepo = new ProgramatorORMRepository(sessionFactory);
        BugORMRepository bugRepo = new BugORMRepository(sessionFactory);
        Service serv = new Service(progRepo, verifRepo, bugRepo);

        //Teste
        /*Bug bug_nou = serv.saveBug("Podea", "Se pica prin podea", StareBug.NEREZOLVAT);
        Bug bug_nou = serv.getOneBugById(2);
        Bug bug_nou_update = new Bug(bug_nou.getId(), "Perete", "Tece cu capu prin el", StareBug.NEREZOLVAT);
        bug_nou_update = serv.updateBug(bug_nou_update);
        serv.rezolvaBug(bug_nou_update.getId());
        List<Bug> bugs = serv.getAllBugs();
        System.out.println(bug_nou);
        System.out.println(bug_nou_update);
        System.out.println(bugs.size());
        System.out.println(bugs);*/
        //

        FXMLLoader fxmlLoader = new FXMLLoader(MainUI.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 340);
        LogInViewController ctr = fxmlLoader.getController();
        ctr.initLoginController(serv);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        setUp();
        launch();
    }
}
