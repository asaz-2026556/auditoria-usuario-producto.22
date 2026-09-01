package org.allansaz.system.model;

public class User {

//Atributos    
    private String name;
    private String lastname;
    private String email;
    private String user;
    private String password;
    private String idUser;

//Constructores
    public User() {

    }

    public User(String name, String lastname, String email, String user, String password, String idUser) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.user = user;
        this.password = password;
        this.idUser = idUser;
    }

    public User(String name, String lastname, String email, String user, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.user = user;
        this.password = password;
    }

//Getters y Setters    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}
