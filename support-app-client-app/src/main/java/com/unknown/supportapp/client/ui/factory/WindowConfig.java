package com.unknown.supportapp.client.ui.factory;

public enum WindowConfig {

    LoginWindow(
            "fxml/LoginWindow.fxml",
            "Login Window",
            596,
            281,
            false
    ),
    PrimaryWindow(
            "fxml/LoginWindow.fxml",
            "Support App",
            596,
            281,
            false
    ),
    EditProductWindow(
            "fxml/EditProductWindow.fxml",
            "Edit product",
            596,
            281,
            true
    ),
    NewProductWindow(
            "fxml/NewProductWindow.fxml",
            "New product",
            596,
            281,
            true
    ),
    StartWindow(
            "fxml/StartWindow.fxml",
            "SupportApp",
            1065,
            582,
            false
    ),
    ProductsWindow(
            "fxml/ProductsWindow.fxml",
            "Products",
            1065,
            582,
            false
    ),
    ProfileWindow(
            "fxml/ProfileWindow.fxml",
            "Profile",
            1065,
            582,
            false
    ),
    TickectsWindow(
            "fxml/TicketsWindow.fxml",
            "Tickets",
            1065,
            582,
            false
    ),
    ForgetPasswordWindow(
            "fxml/ForgetPasswordWindow.fxml",
            "Forget Password",
            596,
            281,
            false
    ),
    NewPasswordWindow(
            "fxml/NewPasswordWindow.fxml",
            "New Password",
            596,
            281,
            false
    ),
    WrongEmailFormatWindow(
            "fxml/WrongEmailFormatWindow.fxml",
            "Warning",
            442,
            161,
            true
    ),
    RegistrationWindow(
            "fxml/RegistrationWindow.fxml",
            "Registration",
            596,
            281,
            false
    ),
    WrongAccountFormatWindow(
            "fxml/WrongAccountFormat.fxml",
            "Warning",
            442,
            161,
            true
    ),
    WrongEmailOrPasswordWindow(
            "fxml/WrongEmailOrPassword.fxml",
            "Warning",
            442,
            161,
            true
    ),
    AccountAllreadyExistWindow(
            "fxml/AccountAllreadyExistWindow.fxml",
            "Warning",
            442,
            161,
            true
    ), ConfirmationCodeWindow(
            "fxml/ConfirmationCodeWindow.fxml",
            "Confirm",
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
