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
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nfpj.utils.arrays.ArrayIteratorDescendingTestParams;
import org.nfpj.utils.collections.ListIteratorDescendingTestParams;

/**
 *
 * @author njacinto
 */
@RunWith(Parameterized.class)
public class FilterIteratorDescendingTest extends FilterIteratorTest {
    @Parameterized.Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new LinkedList<>();
        for(IteratorTestFactory factory : ListIteratorDescendingTestParams.data()){
            if(factory instanceof FilterIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        for(IteratorTestFactory factory : ArrayIteratorDescendingTestParams.data()){
            if(factory instanceof FilterIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        return data;
    }

    public FilterIteratorDescendingTest(FilterIteratorTestFactory factory, String name) {
        super(factory, name);
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
    @Override
    public void testFilter(){
        final Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        final Character[] expected = new Character[]{'c', 'b'};
        FilterIteratorTest.testFilter(
                factory.get(new FilterIteratorTest.ExpectedFilterPredicate(expected), array), 
                array, expected);
    }
    
    @Test
    @Override
    public void testFilter_beginning(){
        final Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        final Character[] expected = new Character[]{'b', 'a'};
        FilterIteratorTest.testFilter(
                factory.get(new FilterIteratorTest.ExpectedFilterPredicate(expected), array), 
                array, expected);
    }
    
    @Test
    @Override
    public void testFilter_End(){
        final Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        final Character[] expected = new Character[]{'d', 'c'};
        FilterIteratorTest.testFilter(
                factory.get(new FilterIteratorTest.ExpectedFilterPredicate(expected), array), 
                array, expected);
    }
}
