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

import java.util.function.Predicate;
import org.nfpj.utils.IteratorWithSize;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 * @param <T> the type of object being returned by this iterator
 */
public class ArrayPageFilterIteratorWithSize<T> extends ArrayPageFilterIterator<T> implements IteratorWithSize<T> {
    
    // <editor-fold defaultstate="expanded" desc="Constructors">
    /**
     * Creates an instance of this class
     * 
     * @param array the array from where this instance will extract the elements
     * @param fromIndex
     * @param toIndex
     * @param predicate the filter to be applied to the elements
     */
    public ArrayPageFilterIteratorWithSize(T[] array, int fromIndex, int toIndex, 
            Predicate<T> predicate) {
        super(array, fromIndex, toIndex, predicate);
    }
    
    /**
     * 
     * @param array the array from where this instance will extract the elements
     * @param fromIndex
     * @param toIndex
     * @param predicate the filter to be applied to the elements
     * @param prevIndex the index of the previous element. -1 for ascending 
     *              interaction starting on the first element
     */
    protected ArrayPageFilterIteratorWithSize(T[] array, int fromIndex, int toIndex, 
            Predicate<T> predicate, int prevIndex) {
        super(array, fromIndex, toIndex, predicate, prevIndex);
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
                if(predicate.test(array[i])){
                    countElements++;
                    if(countElements>fromIndex && countElements<=toIndex){
                        return i;
                    }
                }
            }
        }
        return END_OF_ITERATION;
    }
    // </editor-fold>
}
