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
public interface PageIteratorWithSizeTestFactory extends PageIteratorTestFactory,
        IteratorWithSizeTestFactory {
    @Override
    PageIterator<Character> get(int fromIndex, int toIndex, Character ... c);
    
    IteratorWithSize<Character> getWithSize(int fromIndex, int toIndex, Character ... c);

    @Override
    default IteratorWithSize<Character> getWithSize(Character... c){
        return getWithSize(0, (c==null || c.length==0 ? 1 : c.length), c);
    }
    
    @Override
    default PageIterator<Character> get(Character ... c){
        return get(0, (c==null || c.length==0 ? 1 : c.length), c);
    }
}
