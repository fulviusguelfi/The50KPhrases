/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the50kphrases;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author fulvi
 */
public class The50KPhrasesIT {
    
    public The50KPhrasesIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class The50KPhrases.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        The50KPhrases.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateThe50KMoreUsed method, of class The50KPhrases.
     */
    @Test
    public void testPopulateThe50KMoreUsed() throws Exception {
        System.out.println("populateThe50KMoreUsed");
        The50KPhrases instance = null;
        The50KPhrases expResult = null;
        The50KPhrases result = instance.populateThe50KMoreUsed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showThe50KMoreUsed method, of class The50KPhrases.
     */
    @Test
    public void testShowThe50KMoreUsed() {
        System.out.println("showThe50KMoreUsed");
        The50KPhrases instance = null;
        List expResult = null;
        List result = instance.showThe50KMoreUsed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
