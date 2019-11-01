/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.User;

/**
 *
 * @author Tom
 */
public interface UserDaoInterface 
{
    // Find User based on username and password

    /**
     *
     * @param uname
     * @param pword
     * @return
     */
    public User findUserByUsernamePassword(String uname, String pword);
    
    // Find first user with that username

    /**
     *
     * @param uname
     * @return
     */
    public User findUserByUsername(String uname);
    
    // Check if a specific User has admin status
    // This will return true if this user has admin status,
    // and false otherwise

    /**
     *
     * @param uname
     * @return
     */
    public boolean checkIfUserIsAdmin(String uname);
    
    // Add a user to the database
    // This will return true if the user was added to the database
    // and false if the user couldn't be added.

    /**
     *
     * @param u
     * @return
     */
    public boolean addUser(User u);
    
    // Remove user
    // This will return true if the user could be removed from the database
    // and false if the user couldn't be removed.

    /**
     *
     * @param u
     * @return
     */
    public boolean removeUser(User u);
    
    // and false if the user couldn't be updated.

    /**
     *
     * @param u
     * @return
     */
    public boolean updateUser(User u);
    

}
