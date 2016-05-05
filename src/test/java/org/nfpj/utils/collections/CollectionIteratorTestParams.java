/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
