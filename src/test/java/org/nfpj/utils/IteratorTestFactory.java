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
public interface IteratorTestFactory {
    String getName();
    Iterator<Character> get(Character ... c);
}
