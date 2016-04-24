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

import java.util.Iterator;
import java.util.function.Predicate;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 */
public abstract class FilterIteratorTest extends IteratorTest {
    
    protected abstract Iterator<Character> getIterator(Predicate<Character> filter, Character ... values);
    
    public FilterIteratorTest() {
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
    
    @Test
    public void testFilter(){
        Character[] array = new Character[]{'a', 'b', 'c'};
        Character[] expected = new Character[]{'b'};
        Iterator<Character> instance = getIterator(new Predicate<Character>() {
            @Override
            public boolean test(Character t) {
                return t=='b';
            }
        }, array);
        int i=0;
        while(instance.hasNext()){
            assertEquals("Value on iterator don't match value expected",
                    expected[i++], instance.next());
        }
        assertEquals("Number of elements expected is not correct", 
                expected.length, i);
    }
    
    // Protected

    @Override
    protected Iterator<Character> getIterator(Character... values) {
        return getIterator(TruePredicate.INSTANCE, values);
    }
    
}
