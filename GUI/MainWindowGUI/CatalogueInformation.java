package GUI.MainWindowGUI;

import javafx.beans.property.SimpleStringProperty;

public class CatalogueInformation {
    private final SimpleStringProperty name;
    private final SimpleStringProperty location;
    private final SimpleStringProperty extension;
    private final SimpleStringProperty size;

    public CatalogueInformation(String fName, String flocation, String fextension, String fsize) {
        name = new SimpleStringProperty(fName);
        location = new SimpleStringProperty(flocation);
        extension = new SimpleStringProperty(fextension);
        size = new SimpleStringProperty(fsize);
    }

    public String getName(){
        return name.get();
    }

    public String getLocation(){
        return location.get();
    }

    public String getExtension(){
        return extension.get();
    }

    public String getSize(){
        return size.get();
    }
}
