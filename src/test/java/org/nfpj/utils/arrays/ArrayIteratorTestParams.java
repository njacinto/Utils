/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nfpj.utils.arrays;

import java.util.Iterator;
import java.util.function.Predicate;
import org.nfpj.utils.FilterIteratorTestFactory;
import org.nfpj.utils.IteratorTestFactory;
import org.nfpj.utils.IteratorWithSize;
import org.nfpj.utils.PageFilterIteratorTestFactory;
import org.nfpj.utils.PageFilterIteratorWithSizeTestFactory;
import org.nfpj.utils.PageIterator;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 */
public class ArrayIteratorTestParams {
    
    public static IteratorTestFactory[] data() {
        FilterIteratorTestFactory[] data = new FilterIteratorTestFactory[]{
            new FilterIteratorTestFactory() {
                @Override
                public String getName() {
                    return ArrayFilterIterator.class.getSimpleName();
                }

                @Override
                public Iterator<Character> get(Predicate<Character> filter, Character... c) {
                    return new ArrayFilterIterator<Character>(c, filter);
                }
            },
            new PageFilterIteratorTestFactory() {
                @Override
                public String getName() {
                    return ArrayPageFilterIterator.class.getSimpleName();
                }

                @Override
                public PageIterator<Character> get(int fromIndex, int toIndex, 
                        Predicate<Character> filter, Character... c) {
                    return new ArrayPageFilterIterator<Character>(
                            c, fromIndex, toIndex, filter);
                }
            },
            new PageFilterIteratorWithSizeTestFactory() {
                @Override
                public String getName() {
                    return ArrayPageFilterIteratorWithSize.class.getSimpleName();
                }

                @Override
                public IteratorWithSize<Character> getWithSize(int fromIndex, int toIndex,
                        Predicate<Character> filter, Character... c) {
                    return new ArrayPageFilterIteratorWithSize<Character>(
                            c, fromIndex, toIndex, filter);
                }

                @Override
                public PageIterator<Character> get(int fromIndex, int toIndex,
                        Predicate<Character> filter, Character... c) {
                    return new ArrayPageFilterIteratorWithSize<Character>(
                            c, fromIndex, toIndex, filter);
                }
            }
        };
        return data;
    }
}
