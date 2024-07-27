module id.dojo.pmjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires java.xml.bind;


    opens id.dojo.pmjavafx to javafx.fxml;
    exports id.dojo.pmjavafx;
    exports id.dojo.pmjavafx.controllers;
    opens id.dojo.pmjavafx.controllers to javafx.fxml;
}