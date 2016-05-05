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
import java.util.function.Predicate;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nfpj.utils.arrays.ArrayIteratorTestParams;
import org.nfpj.utils.collections.CollectionIteratorTestParams;

/**
 *
 * @author njacinto
 */
@RunWith(Parameterized.class)
public class FilterIteratorTest {
    @Parameterized.Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new LinkedList<>();
        for(IteratorTestFactory factory : CollectionIteratorTestParams.data()){
            if(factory instanceof FilterIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        for(IteratorTestFactory factory : ArrayIteratorTestParams.data()){
            if(factory instanceof FilterIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        return data;
    }
    
    
    protected final FilterIteratorTestFactory factory;
    protected final String name;

    public FilterIteratorTest(FilterIteratorTestFactory factory, String name) {
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
    public void testFilter(){
        final Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        final Character[] expected = new Character[]{'b', 'c'};
        testFilter(factory.get(new ExpectedFilterPredicate(expected), array), array, expected);
    }
    
    @Test
    public void testFilter_beginning(){
        final Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        final Character[] expected = new Character[]{'a', 'b'};
        testFilter(factory.get(new ExpectedFilterPredicate(expected), array), array, expected);
    }
    
    @Test
    public void testFilter_End(){
        final Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        final Character[] expected = new Character[]{'c', 'd'};
        testFilter(factory.get(new ExpectedFilterPredicate(expected), array), array, expected);
    }
    
    public static void testFilter(Iterator<Character> instance,
            final Character[] array, final Character[] expected){;
        int i=0;
        while(instance.hasNext()){
            assertEquals("Value on iterator don't match value expected",
                    expected[i++], instance.next());
        }
        assertEquals("Number of elements expected is not correct", 
                expected.length, i);
    }
    
    public static void testFilterWithSize(IteratorWithSize<Character> instance,
            final Character[] array, int sizeExpected, final Character[] expected){;
        int i=0;
        while(instance.hasNext()){
            assertEquals("Value on iterator don't match value expected",
                    expected[i++], instance.next());
        }
        assertEquals("Number of elements expected is not correct", 
                expected.length, i);
        assertEquals("Size expected is not correct", 
                sizeExpected, instance.size());
    }
    
    // public classes
    public static class ExpectedFilterPredicate implements Predicate<Character> {
        private final Character[] expected;

        public ExpectedFilterPredicate(Character[] expected) {
            this.expected = expected;
        }

        @Override
        public boolean test(Character t) {
            for(Character chr : expected){
                if(t.equals(chr)){
                    return true;
                }
            }
            return false;
        }
    }
}
