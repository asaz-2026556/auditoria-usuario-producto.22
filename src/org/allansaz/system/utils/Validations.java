package org.allansaz.system.utils;

public class Validations {

//Atributos 
//Metodos    
    public Validations() {
    }

    public Boolean validateTextEmpty(String text) {
        boolean isEmpty = false;

        if (text.isEmpty() == true || text.isBlank() == true) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public Boolean validateTextLenght(String text, int textMax) {
        return text.length() <= textMax;
    }

    public Boolean equalsTexr(String textOriginal, String textCompare) {
        return textOriginal.equals(textCompare);
    }

    public Boolean validateEmail(String email) {
        int dotCount = 0, arrobeCount = 0; //Contamos . y @

//Valida el punto
        for (int index = 0; index < email.length(); index++) {
            if (email.charAt(index) == '.') {
                dotCount++;
            }
            if (dotCount > 1) {
                return false;
            }
        }
//Validar @
        for (int index = 0; index < email.length(); index++) {
            if (email.charAt(index) == '@') {
                arrobeCount++;
            }
        }
        if (arrobeCount != 1) {
            return false;
        }
        return true;
    }

 
}
