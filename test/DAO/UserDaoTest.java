/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ahmed Khan
 */
public class UserDaoTest {

    private UserDao user;

    public UserDaoTest() {
        user = new UserDao("libraryTest");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of findUserByUsernamePassword method, of class UserDao.
     */
    @Test
    public void testFindUserByUsernamePassword() {
        System.out.println("findUserByUsernamePassword");
        String uname = "ahmedkhan";
        String pword = "5f4dcc3b5aa765d61d8327deb882cf99";

        User expResult = new User("ahmedkhan", "5f4dcc3b5aa765d61d8327deb882cf99", "Ahmed", "Khan", false);

        User result = user.findUserByUsernamePassword(uname, pword);
        assertEquals(expResult, result);
    }

    /**
     * Test of findUserByUsername method, of class UserDao.
     */
    @Test
    public void testFindUserByUsername() {
        System.out.println("findUserByUsername");
        String uname = "ahmedkhan";

        User expResult = new User("ahmedkhan", "password", "Ahmed", "Khan", false);

        User result = user.findUserByUsername(uname);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfUserIsAdmin method, of class UserDao.
     */
    @Test
    public void testCheckIfUserIsAdmin() {
        System.out.println("checkIfUserIsAdmin");
        String uname = "ahmedkhan";

        boolean expResult = false;

        boolean result = user.checkIfUserIsAdmin(uname);
        assertEquals(expResult, result);
    }

    /**
     * Test of addUser method, of class UserDao.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User u = new User("testname11", "testpassword11", "testfname11", "testlname11", false);
        boolean expResult = true;
        boolean result = user.addUser(u);
       
        assertTrue((result));
        if (result) {
            System.out.println("Method Returned appropriately, confirming database changed by trying to remove what was added");
            boolean deleted = user.removeUser(u);
            assertEquals(deleted, true);
        }
    }

    /**
     * Test of removeUser method, of class UserDao.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        User u = new User("testname11", "testpassword11", "testfname11", "testlname11", false);
        boolean expResult = false;
        boolean result = user.removeUser(u);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateUser method, of class UserDao.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        String uname = "ahmedkhan";
        String pword = "password";
        
        user.findUserByUsernamePassword(uname, pword);
        
        User u = new User("ahmedkhan", "password", "Ahmed", "Khan", false);
        boolean expResult = false;
        boolean result = user.updateUser(u);
        assertEquals(expResult, result);
    }

}
