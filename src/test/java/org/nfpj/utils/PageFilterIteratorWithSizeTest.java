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

import java.util.function.Predicate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 */
public abstract class PageFilterIteratorWithSizeTest extends PageFilterIteratorTest {
    
    protected abstract IteratorWithSize<Character> getIteratorWithSize(int fromIndex, int toIndex, 
            Predicate<Character> filter, Character ... values);
    
    public PageFilterIteratorWithSizeTest() {
    }
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }
    
    @After
    @Override
    public void tearDown() {
        super.tearDown();
    }

    /**
     * Test of size method, of class IteratorWithCollectionSize.
     */
    @Test
    public void testSize() {
        IteratorWithSize instance = getIteratorWithSize(0, Integer.MAX_VALUE);
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeAfterReachingEnd() {
        IteratorWithSize instance = getIteratorWithSize(0, 2, 'a','b');
        int expResult = 2;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeBeforeReachingEnd() {
        IteratorWithSize instance = getIteratorWithSize(0, 2, 'a','b');
        int expResult = -1;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeFirstPage() {
        IteratorWithSize instance = getIteratorWithSize(0, 2, 
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
        IteratorWithSize instance = getIteratorWithSize(2, 4, 
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
        IteratorWithSize instance = getIteratorWithSize(4, 6, 
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
        IteratorWithSize instance = getIteratorWithSize(6, 8, 
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
        final Character[] expected = new Character[]{'b', 'd', 'f'};
        IteratorWithSize<Character> instance = getIteratorWithSize(0, 6, new Predicate<Character>() {
            @Override
            public boolean test(Character t) {
                for(Character c : expected){
                    if(c.equals(t)){
                        return true;
                    }
                }
                return false;
            }
        }, array);
        int i=0;
        while(instance.hasNext()){
            assertEquals("Value on iterator don't match value expected",
                    expected[i++], instance.next());
        }
        assertEquals("Number of elements expected is not correct", 
                expected.length, i);
        assertEquals("Size expected is not correct", 
                expected.length, instance.size());
    }
    
    //
    
    protected IteratorWithSize<Character> getIteratorWithSize(int fromIndex, int toIndex, 
            Character ... values){
        return getIteratorWithSize(fromIndex, toIndex, TruePredicate.getInstance(), values);
    }
    
}
