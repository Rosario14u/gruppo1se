/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

/**
 *
 * @author rosar
 */
public abstract class User {
    private final String username;
    private final String password;
    /**
     * Constructor of User
     * @param username username of User
     * @param password password of User
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * 
     * @return {@code String} username
     */
    public String getUsername() {
        return username;
    }
    /**
     * 
     * @return {@code String} password 
     */
    public String getPassword() {
        return password;
    }
    /**
     * 
     * @return {@code String} representation of User
     */
    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + '}';
    }
    
     
}
