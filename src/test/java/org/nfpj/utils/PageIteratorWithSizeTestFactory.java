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
