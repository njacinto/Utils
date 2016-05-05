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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nfpj.utils.arrays.ArrayIteratorTestParams;
import org.nfpj.utils.collections.CollectionIteratorTestParams;

/**
 *
 * @author njacinto
 */
@RunWith(Parameterized.class)
public class PageFilterIteratorTest {
    @Parameterized.Parameters(name = "{index}: {1}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new LinkedList<>();
        for(IteratorTestFactory factory : CollectionIteratorTestParams.data()){
            if(factory instanceof PageFilterIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        for(IteratorTestFactory factory : ArrayIteratorTestParams.data()){
            if(factory instanceof PageFilterIteratorTestFactory){
                data.add(new Object[]{ factory, factory.getName() });
            }
        }
        return data;
    }
    
    protected final PageFilterIteratorTestFactory factory;
    protected final String name;

    public PageFilterIteratorTest(PageFilterIteratorTestFactory factory, String name) {
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
        Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        Character[] expected = new Character[]{'b', 'c'};
        FilterIteratorTest.testFilter(factory.get(0, array.length, new FilterIteratorTest.ExpectedFilterPredicate(expected), array), array, expected);
    }
    
    @Test
    public void testFilter_beginning(){
        Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        Character[] expected = new Character[]{'a', 'b'};
        FilterIteratorTest.testFilter(factory.get(0, array.length, new FilterIteratorTest.ExpectedFilterPredicate(expected), array), array, expected);
    }
    
    @Test
    public void testFilter_End(){
        Character[] array = new Character[]{'a', 'b', 'c', 'd'};
        Character[] expected = new Character[]{'c', 'd'};
        FilterIteratorTest.testFilter(factory.get(0, array.length, new FilterIteratorTest.ExpectedFilterPredicate(expected), array), array, expected);
    }
    
    @Test
    public void testFilter_WithPage(){
        Character[] array = new Character[]{'a', 'b', 'c', 'd', 'e', 'f'};
        Character[] filterOptions = new Character[]{'a', 'b', 'c'};
        Character[] expected = new Character[]{'c'};
        FilterIteratorTest.testFilter(factory.get(2, 4, new FilterIteratorTest.ExpectedFilterPredicate(filterOptions), array), array, expected);
    }
    
}
