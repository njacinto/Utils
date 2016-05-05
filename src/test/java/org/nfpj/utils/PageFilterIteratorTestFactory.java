/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nfpj.utils;

import java.util.function.Predicate;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 */
public interface PageFilterIteratorTestFactory extends PageIteratorTestFactory, 
        FilterIteratorTestFactory {
    PageIterator<Character> get(int fromIndex, int toIndex, 
            Predicate<Character> filter, Character ... c);
    
    @Override
    default PageIterator<Character> get(Predicate<Character> filter, Character ... c){
        return get(0, (c==null || c.length==0 ? 1 : c.length), filter, c);
    }
    
    @Override
    default PageIterator<Character> get(int fromIndex, int toIndex, Character ... c){
        return get(fromIndex, toIndex, TruePredicate.INSTANCE, c);
    }
    
    @Override
    default PageIterator<Character> get(Character ... c){
        return get(0, (c==null || c.length==0 ? 1 : c.length), TruePredicate.INSTANCE, c);
    }
}
