/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nfpj.utils;

import java.util.Iterator;
import java.util.function.Predicate;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 */
public interface FilterIteratorTestFactory extends IteratorTestFactory {
    Iterator<Character> get(Predicate<Character> filter, Character ... c);
    
    @Override
    default Iterator<Character> get(Character ... c){
        return get(TruePredicate.INSTANCE, c);
    }
}
