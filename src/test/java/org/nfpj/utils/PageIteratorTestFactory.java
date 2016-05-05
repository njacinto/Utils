/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nfpj.utils;

/**
 *
 * @author njacinto
 */
public interface PageIteratorTestFactory extends IteratorTestFactory {
    PageIterator<Character> get(int fromIndex, int toIndex, Character ... c);
    
    @Override
    default PageIterator<Character> get(Character ... c){
        return get(0, (c==null || c.length==0 ? 1 : c.length), c);
    }
}
