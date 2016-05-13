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
import java.util.Iterator;
import java.util.function.Predicate;
import org.nfpj.utils.FilterIteratorTestFactory;
import org.nfpj.utils.IteratorTestFactory;
import org.nfpj.utils.IteratorWithSize;
import org.nfpj.utils.PageFilterIteratorTestFactory;
import org.nfpj.utils.PageFilterIteratorWithSizeTestFactory;
import org.nfpj.utils.PageIterator;

/**
 *
 * @author njacinto
 */
public class CollectionIteratorTestParams {
    
    public static IteratorTestFactory[] data() {
        IteratorTestFactory[] data = new IteratorTestFactory[]{
                new FilterIteratorTestFactory() {
                    @Override
                    public String getName() {
                        return CollectionFilterIterator.class.getSimpleName();
                    }

                    @Override
                    public Iterator<Character> get(Predicate<Character> filter, Character... c) {
                        return new CollectionFilterIterator<Character>(
                                (c == null ? null : Arrays.asList(c)), filter);
                    }
                },
                new PageFilterIteratorTestFactory() {
                    @Override
                    public String getName() {
                        return CollectionPageFilterIterator.class.getSimpleName();
                    }

                    @Override
                    public PageIterator<Character> get(int fromIndex, int toIndex, 
                            Predicate<Character> filter, Character... c) {
                        return new CollectionPageFilterIterator<Character>(
                                (c == null ? null : Arrays.asList(c)),
                                fromIndex, toIndex, filter);
                    }

                },
                new PageFilterIteratorWithSizeTestFactory() {
                    @Override
                    public String getName() {
                        return CollectionPageFilterIteratorWithSize.class.getSimpleName();
                    }

                    @Override
                    public IteratorWithSize<Character> getWithSize(int fromIndex, int toIndex,
                            Predicate<Character> filter, Character... c) {
                        return new CollectionPageFilterIteratorWithSize<Character>(
                                (c == null ? null : Arrays.asList(c)),
                                fromIndex, toIndex, filter);
                    }

                    @Override
                    public PageIterator<Character> get(int fromIndex, int toIndex,
                            Predicate<Character> filter, Character... c) {
                        return new CollectionPageFilterIteratorWithSize<Character>(
                                (c == null ? null : Arrays.asList(c)),
                                fromIndex, toIndex, filter);
                    }

                }
        };
        return data;
    }
}
