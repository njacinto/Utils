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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author njacinto
 */
public abstract class IteratorWithSizeTest extends IteratorTest {
    
    protected abstract IteratorWithSize<Character> getIterator(Character ... values);
    
    public IteratorWithSizeTest() {
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
        IteratorWithSize instance = getIterator();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeAfterReachingEnd() {
        IteratorWithSize instance = getIterator('a','b');
        int expResult = 2;
        while(instance.hasNext()){
            instance.next();
        }
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSizeBeforeReachingEnd() {
        IteratorWithSize instance = getIterator('a','b');
        int expResult = -1;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
}
