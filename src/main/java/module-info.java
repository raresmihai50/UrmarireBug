module com.example.urmarirebugfinal {
    requires javafx.controls;
    requires javafx.fxml;

    //requires com.dlsc.formsfx;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires org.jboss.logging;
    //requires java.persistence;

    opens com.example.urmarirebugfinal to javafx.fxml;
    exports com.example.urmarirebugfinal;


    opens com.example.urmarirebugfinal.controller;
    exports com.example.urmarirebugfinal.controller;

    opens com.example.urmarirebugfinal.service;
    exports com.example.urmarirebugfinal.service;

    opens com.example.urmarirebugfinal.domain;
    exports com.example.urmarirebugfinal.domain;

    opens com.example.urmarirebugfinal.repository;
    exports com.example.urmarirebugfinal.repository;
}