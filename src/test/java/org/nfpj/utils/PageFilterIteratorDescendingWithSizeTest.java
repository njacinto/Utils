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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nfpj.utils.arrays.ArrayIteratorDescendingTestParams;
import org.nfpj.utils.collections.ListIteratorDescendingTestParams;

/**
 *
 * @author njacinto
 */
@RunWith(Parameterized.class)
public class PageFilterIteratorDescendingWithSizeTest {
    @Parameterized.Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new LinkedList<>();
        for(IteratorTestFactory factory : ListIteratorDescendingTestParams.data()){
            if(factory instanceof PageFilterIteratorWithSizeTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        for(IteratorTestFactory factory : ArrayIteratorDescendingTestParams.data()){
            if(factory instanceof PageFilterIteratorWithSizeTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        return data;
    }
    
    protected final PageFilterIteratorWithSizeTestFactory factory;
    protected final String name;

    public PageFilterIteratorDescendingWithSizeTest(PageFilterIteratorWithSizeTestFactory factory, String name) {
        this.factory = factory;
        this.name = name;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of size method, of class IteratorWithCollectionSize.
     */
    @Test
    public void testSize() {
        IteratorWithSize instance = factory.getWithSize(0, Integer.MAX_VALUE);
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeAfterReachingEnd() {
        IteratorWithSize instance = factory.getWithSize(0, 2, 'a','b');
        int expResult = 2;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeBeforeReachingEnd() {
        IteratorWithSize instance = factory.getWithSize(0, 2, 'a','b');
        int expResult = -1;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeFirstPage() {
        IteratorWithSize instance = factory.getWithSize(0, 2, 
                'a','b', 'c', 'd', 'e', 'f');
        int expResult = 6;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeMiddlePage() {
        IteratorWithSize instance = factory.getWithSize(2, 4, 
                'a','b', 'c', 'd', 'e', 'f');
        int expResult = 6;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeLastPage() {
        IteratorWithSize instance = factory.getWithSize(4, 6, 
                'a','b', 'c', 'd', 'e', 'f');
        int expResult = 6;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeBeyondLastPage() {
        IteratorWithSize instance = factory.getWithSize(6, 8, 
                'a','b', 'c', 'd', 'e', 'f');
        int expResult = 6;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeWithFilter(){
        final Character[] array = new Character[]{'a', 'b', 'c', 'd', 'e', 'f'};
        Character[] filterOptions = new Character[]{'a', 'b', 'c'};
        final Character[] expected = new Character[]{'c'};
        FilterIteratorTest.testFilterWithSize(factory.getWithSize(0, 1, new FilterIteratorTest.ExpectedFilterPredicate(filterOptions), array), 
                array, filterOptions.length, expected);
    }
    
}
