package org.allansaz.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.allansaz.system.service.UserService;
import org.allansaz.system.service.UserStatus;
import org.allansaz.system.utils.AlertInformation;
import org.allansaz.system.utils.Validations;
import org.allansaz.system.utils.ViewFactory;


public class RegisterController implements Initializable {

//Atributos    
    private UserService userService = new UserService();
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    @FXML private TextField txtUser;
    @FXML private TextField txtName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private PasswordField pwdPassword;
    @FXML private PasswordField pwdConfirmPassword;
    @FXML private Button btnCancel;
    @FXML private Button btnCreateUser;
    
//Metodos    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void onCancelRegister (MouseEvent evento) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
    
    @FXML
    public void onRegisterUser (MouseEvent evento) {
      String email = txtEmail.getText().trim();
      boolean validEmail = validate.validateEmail(email);  
        if(validEmail == false) {
            alertInfo.viewAlert("Error de Email", "Error de Campo Email", "Ingresaste un Email Incorrecto","Error");
        }
        
        String user, name, lastName, password, confirmPassword;
        
        user = txtUser.getText().trim();
        name = txtName.getText().trim();
        lastName = txtLastName.getText().trim();
        password = pwdPassword.getText().trim();
        confirmPassword = pwdConfirmPassword.getText().trim();
        
        if(validate.validateTextEmpty(user) == true || 
           validate.validateTextEmpty(name) == true || 
           validate.validateTextEmpty(lastName) == true || 
           validate.validateTextEmpty(email) == true || 
           validate.validateTextEmpty(password) == true || 
           validate.validateTextEmpty(confirmPassword) == true) {
       
            alertInfo.viewAlert("Error de Campo", "Error de Campos Vacios", "No llenaste todos los Campos","Error");
            return;
        }

        
        String msgField = "";
        if(validate.validateTextLenght(user, 25) == false)
            msgField = "El campo Usuario es Mayor a 25 letras";
        
        if(validate.validateTextLenght(name, 50) == false)
            msgField = "El campo Nombres es Mayor a 50 letras";
        
        if(validate.validateTextLenght(lastName, 50) == false)
            msgField = "El campo Apellidos es Mayor a 50 letras";
        
        if(validate.validateTextLenght(email, 50) == false)
            msgField = "El campo Correo es Mayor a 50 letras";
        
        if(validate.validateTextLenght(password, 35) == false)
            msgField = "El campo Contraseña es Mayor a 35 letras";
        
        if(validate.validateTextLenght(confirmPassword, 35) == false)
            msgField = "El campo Confirmar Contraseña es Mayor a 35 letras";
     
        if(msgField.isEmpty() == false) {
            alertInfo.viewAlert("Error de Campo", "Error de Longitud de Campo", msgField,"Error");
        }
        
        if(validate.equalsTexr(password, confirmPassword) == false) {
            alertInfo.viewAlert("Error de Contraseña", "Error al confirmar Contraseña", "Las Contraseñas no Coinciden","Error");         
        }
        
        UserStatus status = userService.createUser(user, name, lastName, email, password);
        
        switch(status) {
            case USER_CREATED-> 
                System.out.println("Se creo el Usuario");
                case ERROR_USER_CREATE->
                    System.out.println("No se pudo crear el Usuario");
                    default ->
                        System.out.println("");
        }        
    }
    
    

}
