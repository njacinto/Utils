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
package org.nfpj.utils.arrays;

import java.util.Iterator;
import java.util.function.Predicate;
import org.nfpj.utils.FilterIteratorTestFactory;
import org.nfpj.utils.IteratorTestFactory;
import org.nfpj.utils.IteratorWithSize;
import org.nfpj.utils.IteratorWithSizeTestFactory;
import org.nfpj.utils.PageFilterIteratorTestFactory;
import org.nfpj.utils.PageFilterIteratorWithSizeTestFactory;
import org.nfpj.utils.PageIterator;
import org.nfpj.utils.PageIteratorTestFactory;
import org.nfpj.utils.PageIteratorWithSizeTestFactory;

/**
 *
 * @author njacinto
 */
public class ArrayIteratorDescendingTestParams {

    public static IteratorTestFactory[] data() {
        IteratorTestFactory[] data = new IteratorTestFactory[]{
            new FilterIteratorTestFactory() {
                @Override
                public String getName() {
                    return ArrayFilterIteratorDescending.class.getSimpleName();
                }

                @Override
                public Iterator<Character> get(Predicate<Character> filter, Character... c) {
                    return new ArrayFilterIteratorDescending<Character>(c, filter);
                }
            },
            new PageFilterIteratorTestFactory() {
                @Override
                public String getName() {
                    return ArrayPageFilterIteratorDescending.class.getSimpleName();
                }

                @Override
                public PageIterator<Character> get(int fromIndex, int toIndex,
                        Predicate<Character> filter, Character... c) {
                    return new ArrayPageFilterIteratorDescending<Character>(c,
                            fromIndex, toIndex, filter);
                }
            },
            new PageFilterIteratorWithSizeTestFactory() {
                @Override
                public String getName() {
                    return ArrayPageFilterIteratorDescendingWithSize.class.getSimpleName();
                }

                @Override
                public IteratorWithSize<Character> getWithSize(int fromIndex, int toIndex,
                        Predicate<Character> filter, Character... c) {
                    return new ArrayPageFilterIteratorDescendingWithSize<Character>(
                            c, fromIndex, toIndex, filter);
                }

                @Override
                public PageIterator<Character> get(int fromIndex, int toIndex,
                        Predicate<Character> filter, Character... c) {
                    return new ArrayPageFilterIteratorDescendingWithSize<Character>(
                            c, fromIndex, toIndex, filter);
                }
            },
            new IteratorTestFactory() {
                @Override
                public String getName() {
                    return ArrayIteratorDescending.class.getSimpleName();
                }

                @Override
                public Iterator<Character> get(Character... c) {
                    return new ArrayIteratorDescending<Character>(c);
                }
            },
            new PageIteratorTestFactory() {
                @Override
                public String getName() {
                    return ArrayPageIteratorDescending.class.getSimpleName();
                }

                @Override
                public PageIterator<Character> get(int fromIndex, int toIndex,
                        Character... c) {
                    return new ArrayPageIteratorDescending<Character>(c,
                            fromIndex, toIndex);
                }
            },
            new PageIteratorWithSizeTestFactory() {
                @Override
                public String getName() {
                    return ArrayPageIteratorDescendingWithSize.class.getSimpleName();
                }

                @Override
                public IteratorWithSize<Character> getWithSize(int fromIndex, int toIndex,
                        Character... c) {
                    return new ArrayPageIteratorDescendingWithSize<Character>(
                            c, fromIndex, toIndex);
                }

                @Override
                public PageIterator<Character> get(int fromIndex, int toIndex,
                        Character... c) {
                    return new ArrayPageIteratorDescendingWithSize<Character>(
                            c, fromIndex, toIndex);
                }
            },
            new IteratorWithSizeTestFactory() {
                @Override
                public String getName() {
                    return ArrayIteratorDescendingWithSize.class.getSimpleName();
                }

                @Override
                public IteratorWithSize<Character> getWithSize(Character... c) {
                    return new ArrayIteratorDescendingWithSize<Character>(c);
                }
            }
        };
        return data;
    }
}
