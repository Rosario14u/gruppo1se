/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rosar
 */
public class UserTest {
    private User user;
    public UserTest() {
        user = new UserImpl();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        String expResult = "admin";
        String result = user.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        String expResult = "password";
        String result = user.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        String expResult = "User{" + "username=" + "admin" + ", password=" + "password" + '}';
        String result = user.toString();
        assertEquals(expResult, result);
    }

    public class UserImpl extends User {

        public UserImpl() {
            super("admin", "password");
        }
    }
    
}
