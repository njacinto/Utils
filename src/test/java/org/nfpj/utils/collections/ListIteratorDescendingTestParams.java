/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nfpj.utils.collections;

import java.util.Arrays;
import java.util.Iterator;
import org.nfpj.utils.IteratorTestFactory;
import org.nfpj.utils.PageIterator;
import org.nfpj.utils.predicates.TruePredicate;
import org.nfpj.utils.PageIteratorTestFactory;

/**
 *
 * @author njacinto
 */
public class ListIteratorDescendingTestParams {
    
    public static IteratorTestFactory[] data() {
        IteratorTestFactory[] data = new IteratorTestFactory[]{
                new IteratorTestFactory() {
                    @Override
                    public String getName() {
                        return ListFilterIteratorDescending.class.getSimpleName();
                    }

                    @Override
                    public Iterator<Character> get(Character... c) {
                        return new ListFilterIteratorDescending<Character>(
                                (c==null ? null : Arrays.asList(c)), TruePredicate.INSTANCE);
                    }
                },
                new PageIteratorTestFactory() {
                    @Override
                    public String getName() {
                        return ListPageFilterIteratorDescending.class.getSimpleName();
                    }
                    
                    @Override
                    public PageIterator<Character> get(int fromIndex, int toIndex, Character... c) {
                        return new ListPageFilterIteratorDescending<Character>(
                                (c==null ? null : Arrays.asList(c)), 
                                fromIndex, toIndex, TruePredicate.INSTANCE);
                    }
                }
        };
        return data;
    }
}
