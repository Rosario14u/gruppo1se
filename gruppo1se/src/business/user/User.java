/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import java.util.Objects;

/**
 *
 * @author rosar
 */
//Class developed by Rosario Gaeta
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
    
    
    
     
}
