package com.unknown.supportapp.client.ui.factory;

public enum WindowConfig {

    LoginWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/loginWindow/LoginWindow.fxml",
            "Login Window",
            596,
            281,
            false
    ),
    PrimaryWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/loginWindow/LoginWindow.fxml",
            "Support App",
            596,
            281,
            false
    ),
    EditProductWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/editProductWindow/EditProductWindow.fxml",
            "Edit product",
            596,
            281,
            true
    ),
    NewProductWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/newProductWindow/NewProductWindow.fxml",
            "New product",
            596,
            281,
            true
    ),
    StartWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/startWindow/StartWindow.fxml",
            "SupportApp",
            1065,
            582,
            false
    ),
    ProductsWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/productsWindow/ProductsWindow.fxml",
            "Products",
            1065,
            582,
            false
    ),
    ProfileWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/profileWindow/ProfileWindow.fxml",
            "Profile",
            1065,
            582,
            false
    ),
    TickectsWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/ticketsWindow/TicketsWindow.fxml",
            "Tickets",
            1065,
            582,
            false
    ),
    ForgetPasswordWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/forgetPasswordWindow/ForgetPasswordWindow.fxml",
            "Forget Password",
            596,
            281,
            false
    ),
    NewPasswordWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/newPasswordWindow/NewPasswordWindow.fxml",
            "New Password",
            596,
            281,
            false
    ),
    WrongEmailFormatWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/wrongEmailFormatWindow/WrongEmailFormatWindow.fxml",
            "Warning",
            442,
            161,
            true
    ),
    RegistrationWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/RegistrationWindow/RegistrationWindow.fxml",
            "Registration",
            596,
            281,
            false
    ),
    WrongAccountFormatWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/wrongAccountFormatWindow/WrongAccountFormat.fxml",
            "Warning",
            442,
            161,
            true
    ),
    WrongEmailOrPasswordWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/wrongEmailOrPasswordWindow/WrongEmailOrPassword.fxml",
            "Warning",
            442,
            161,
            true
    ),
    AccountAllreadyExistWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/accountAllreadyExistWindow/AccountAllreadyExistWindow.fxml",
            "Warning",
            442,
            161,
            true
    ), ConfirmationCodeWindow(
            "/com/unknown/supportapp/client/ui/guiWindows/confirmationCodeWindow/ConfirmationCodeWindow.fxml",
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
