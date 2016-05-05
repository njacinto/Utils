/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nfpj.utils;

import java.util.Iterator;

/**
 *
 * @author njacinto
 */
public interface IteratorWithSizeTestFactory extends IteratorTestFactory {
    IteratorWithSize<Character> getWithSize(Character ... c);

    @Override
    default Iterator<Character> get(Character... c){
        return getWithSize(c);
    }
}
