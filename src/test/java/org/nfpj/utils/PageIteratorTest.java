/*
 * The MIT License
 *
 * Copyright 2016 njacinto.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.nfpj.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nfpj.utils.arrays.ArrayIteratorTestParams;
import org.nfpj.utils.collections.CollectionIteratorTestParams;

/**
 *
 * @author njacinto
 */
@RunWith(Parameterized.class)
public class PageIteratorTest {
    @Parameterized.Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new LinkedList<>();
        for(IteratorTestFactory factory : CollectionIteratorTestParams.data()){
            if(factory instanceof PageIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        for(IteratorTestFactory factory : ArrayIteratorTestParams.data()){
            if(factory instanceof PageIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        return data;
    }
    
    protected final PageIteratorTestFactory factory;
    protected final String name;

    public PageIteratorTest(PageIteratorTestFactory factory, String name) {
        this.factory = factory;
        this.name = name;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testCreateInvalidFrom() {
        PageIterator instance = factory.get(-1, 2, 'a', 'b');
        int expResult = 0;
        int result = instance.getFromIndex();
        assertEquals(expResult, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidTo_EqualToFrom() {
        PageIterator instance = factory.get(1, 1, 'a', 'b');
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidTo_SmallerThanFrom() {
        PageIterator instance = factory.get(1, 0, 'a', 'b');
    }

    /**
     * Test of getFromIndex method, of class PageIterator.
     */
    @Test
    public void testGetFromIndex() {
        PageIterator instance = factory.get(1, 2, 'a', 'b');
        int expResult = 1;
        int result = instance.getFromIndex();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetFromIndex_NegativeSet() {
        PageIterator instance = factory.get(-1, 2, 'a', 'b');
        int expResult = 0;
        int result = instance.getFromIndex();
        assertEquals(expResult, result);
    }

    /**
     * Test of getToIndex method, of class PageIterator.
     */
    @Test
    public void testGetToIndex() {
        PageIterator instance = factory.get(1, 2, 'a', 'b');
        int expResult = 2;
        int result = instance.getToIndex();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPage method, of class PageIterator.
     */
    @Test
    public void testGetPage() {
        PageIterator instance = factory.get(2, 3, 'a', 'b', 'c', 'd');
        int expResult = 3;
        int result = instance.getPage();
        assertEquals(expResult, result);
        instance = factory.get(2, 4, 'a', 'b', 'c', 'd');
        expResult = 2;
        result = instance.getPage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPageSize method, of class PageIterator.
     */
    @Test
    public void testGetPageSize_BeforeGettingPageElements() {
        PageIterator instance = factory.get(2, 4, 'a', 'b', 'c', 'd');
        int expResult = -1;
        int result = instance.getPageSize();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetPageSize_AfterGettingPageElements() {
        PageIterator instance = factory.get(2, 4, 'a', 'b', 'c', 'd');
        int expResult = 2;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.getPageSize();
        assertEquals(expResult, result);
        instance = factory.get(3, 6, 'a', 'b', 'c', 'd');
        expResult = 1;
        while(instance.hasNext()){
            instance.next();
        }
        result = instance.getPageSize();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetPageElements() {
        Character[] data = new Character[]{'a', 'b', 'c', 'd'};
        int[][] toFromTotalIndexArray = new int[][]{
            {0,2,2},
            {2,4,2},
            {1,3,2},
            {3,6,1}
        };
        for(int[] toFromIndex : toFromTotalIndexArray){
            PageIterator instance = factory.get(toFromIndex[0], toFromIndex[1], data);
            Character[] expResult = Arrays.copyOfRange(data, toFromIndex[0], toFromIndex[1]);
            int i = 0;
            while(instance.hasNext()){
                assertEquals("Unexpected value when from="+toFromIndex[0]+" and to="+toFromIndex[1], 
                        expResult[i++], instance.next());
            }
            assertEquals("Unexpected number of elements when from="+toFromIndex[0]+" and to="+toFromIndex[1], 
                    toFromIndex[2], i);
        }
    }
}
