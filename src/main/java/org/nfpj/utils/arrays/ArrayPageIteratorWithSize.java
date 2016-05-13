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
package org.nfpj.utils.arrays;

import org.nfpj.utils.IteratorWithSize;

/**
 *
 * @author njacinto
 * @param <T> the type of object being returned by this iterator
 */
public class ArrayPageIteratorWithSize<T> extends ArrayPageIterator<T> implements IteratorWithSize<T> {
    
    // <editor-fold defaultstate="expanded" desc="Constructors">
    /**
     * Creates an instance of this class
     * 
     * @param array the array from where this instance will extract the elements
     * @param fromIndex
     * @param toIndex
     */
    public ArrayPageIteratorWithSize(T[] array, int fromIndex, int toIndex) {
        super(array, fromIndex, toIndex);
    }
    
    /**
     * 
     * @param array the array from where this instance will extract the elements
     * @param fromIndex
     * @param toIndex
     * @param prevIndex the index of the previous element. -1 for ascending 
     *              interaction starting on the first element
     */
    protected ArrayPageIteratorWithSize(T[] array, int fromIndex, int toIndex, 
            int prevIndex) {
        super(array, fromIndex, toIndex, prevIndex);
    }
    // </editor-fold>
    // <editor-fold defaultstate="expanded" desc="Public methods">

    /**
     * {@inheritDoc} 
     */
    @Override
    public int getPageSize() {
        return hasNext()? -1 : (countElements>toIndex ? toIndex : countElements)-fromIndex;
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public int size() {
        return hasNext() ? -1 : countElements;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Protected methods">
    /**
     * Searches for the next element that matches the filtering conditions and
     * returns it.
     * 
     * @param currIndex
     * @return the next element that matches the filtering conditions or null
     *          if no more elements are available
     */
    @Override
    protected int getNextIndex(int currIndex){
        if(currIndex!=END_OF_ITERATION){
            for(int i=currIndex+1; i<array.length; i++){
                countElements++;
                if(countElements>fromIndex && countElements<=toIndex){
                    return i;
                }
            }
        }
        return END_OF_ITERATION;
    }
    // </editor-fold>
}
