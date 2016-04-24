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
package org.nfpj.utils.collections;

import java.util.Arrays;
import java.util.function.Predicate;
import org.nfpj.utils.IteratorWithSize;
import org.nfpj.utils.PageFilterIteratorWithSizeTest;
import org.nfpj.utils.PageIterator;

/**
 *
 * @author njacinto
 */
public class CollectionPagedFilterIteratorWithSizeTest extends PageFilterIteratorWithSizeTest {
    
    public CollectionPagedFilterIteratorWithSizeTest() {
    }

    @Override
    protected IteratorWithSize<Character> getIteratorWithSize(int fromIndex, int toIndex, 
            Predicate<Character> filter, Character... values) {
        return new CollectionPagedFilterIteratorWithSize(values==null ? null : Arrays.asList(values), 
                fromIndex, toIndex, filter);
    }

    @Override
    protected PageIterator<Character> getIterator(int fromIndex, int toIndex, 
            Predicate<Character> filter, Character... values) {
        return new CollectionPagedFilterIteratorWithSize(values==null ? null : Arrays.asList(values), 
                fromIndex, toIndex, filter);
    }
}
