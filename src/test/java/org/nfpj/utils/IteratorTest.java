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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.nfpj.utils.arrays.ArrayIteratorTestParams;
import org.nfpj.utils.collections.CollectionIteratorTestParams;

/**
 *
 * @author njacinto
 */
@RunWith(Parameterized.class)
public class IteratorTest {
    @Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new LinkedList<>();
        for(IteratorTestFactory factory : CollectionIteratorTestParams.data()){
            data.add(new Object[]{ factory, factory.getName() });
        }
        for(IteratorTestFactory factory : ArrayIteratorTestParams.data()){
            data.add(new Object[]{ factory, factory.getName() });
        }
        return data;
    }
    
    protected final IteratorTestFactory factory;
    protected final String name;
    
    public IteratorTest(IteratorTestFactory factory, String name) {
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
    public void testCreationWithNullParameters() {
        Iterator<Character> instance = factory.get((Character[])null);
        boolean expResult = false;
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hasNext method, of class FilterIterator.
     */
    @Test
    public void testHasNext_true() {
        Iterator<Character> instance = factory.get('a', 'b');
        boolean expResult = true;
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testHasNext_false() {
        Iterator<Character> instance = factory.get(new Character[0]);
        boolean expResult = false;
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
    }

    /**
     * Test of next method, of class FilterIterator.
     */
    @Test
    public void testNext() {
        Iterator<Character> instance = factory.get('a', 'b');
        Object expResult = 'a';
        Object result = instance.next();
        assertEquals(expResult, result);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testNext_empty() {
        Iterator<Character> instance = factory.get(new Character[0]);
        instance.next();
    }

    /**
     * Test of remove method, of class FilterIterator.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        Iterator<Character> instance = factory.get('a', 'b');
        instance.next();
        instance.remove();
    }
    
    @Test
    public void testIteractions(){
        Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        Iterator<Character> instance = factory.get(array);
        int i=0;
        while(instance.hasNext()){
            assertEquals("Value on iterator don't match value on collection",
                    array[i++], instance.next());
        }
        assertEquals("Number of elements iterated don't match the number of elements in collection", 
                array.length, i);
    }
}
