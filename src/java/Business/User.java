/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Objects;

/**
 *
 * @author Tom
 */

// * Based on Users table
// *      create table if not exists users
//        (
//            username varchar(20) not null,
//            password varchar(10) not null,
//            firstName varchar(20),
//            lastName varchar(30),
//            isAdmin boolean not null default FALSE,
//            PRIMARY KEY (username)
//        );
// */
public class User implements Comparable<User>
{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isAdmin;

    /**
     *
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param isAdmin
     */
    public User(String username, String password, String firstName, String lastName, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @return
     */
    public String getUsername()
    {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     *
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     *
     * @return
     */
    public boolean isAdmin()
    {
        return isAdmin;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "User{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", isAdmin=" + isAdmin + '}';
    }
    
    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.username);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
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
    
    /**
     *
     * @param u
     * @return
     */
    @Override
    public int compareTo(User u)
    {
        return this.username.compareTo(u.username);
    }
}
