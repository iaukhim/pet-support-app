package com.unknown.supportapp.manager.ui.factory;

public enum WindowConfig {

    LoginWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/loginWindow/LoginWindow.fxml",
            "Login Window",
            596,
            281,
            false
    ),
    PrimaryWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/loginWindow/LoginWindow.fxml",
            "Support App",
            596,
            281,
            false
    ),
    StartWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/startWindow/StartWindow.fxml",
            "SupportApp",
            1065,
            582,
            false
    ),
    TicketsWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/ticketsWindow/TicketsWindow.fxml",
            "Tickets",
            1065,
            582,
            false
    ),
    ManagedTicketsWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/managedTicketsWindow/ManagedTicketsWindow.fxml",
            "Managed tickets",
            1065,
            582,
            false
    ),
    ManagedTicketsDetailsWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/managedTicketDetailsWindow/ManagedTicketDetailsWindow.fxml",
            "Managed ticket details",
            1065,
            582,
            false
    ),
    UnassignedTicketWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/unassignedTicketWindow/UnassignedTicketWindow.fxml",
            "Unassigned ticket",
            1065,
            582,
            false
    ),
    WrongEmailOrPasswordWindow(
            "/com/unknown/supportapp/manager/ui/guiWindows/wrongEmailOrPasswordWindow/WrongEmailOrPassword.fxml",
            "Warning",
            442,
            161,
            true
    );

    private String fxmlPath;

    private String title;

    private double width;

    private double height;

    private boolean modality;

    WindowConfig(String fxmlPath, String title, double width, double height, boolean modality) {
        this.fxmlPath = fxmlPath;
        this.title = title;
        this.width = width;
        this.height = height;
        this.modality = modality;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public String getTitle() {
        return title;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isModality() {
        return modality;
    }
}
