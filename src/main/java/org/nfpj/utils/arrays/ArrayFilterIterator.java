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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 * @param <T> the type of object being returned by this iterator
 */
public class ArrayFilterIterator<T> implements Iterator<T> {
    
    private final T[] array;
    private final Predicate<T> predicate;
    private int nextIndex;

    // <editor-fold defaultstate="expanded" desc="Constructors">
    /**
     * Creates an instance of this class
     * 
     * @param array the array from where this instance will extract the elements
     * @param predicate the filter to be applied to the elements
     */
    public ArrayFilterIterator(T[] array, Predicate<T> predicate) {
        this.array = array!=null ? array : ArrayUtil.empty();
        this.predicate = predicate!=null ? predicate : TruePredicate.getInstance();
        this.nextIndex = getNextIndex(-1);
    }
    // </editor-fold>
    // <editor-fold defaultstate="expanded" desc="Public methods">
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return nextIndex != -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T next() {
        if(nextIndex==-1){
            throw new NoSuchElementException("The underline collection has no elements.");
        }
        int index = nextIndex;
        nextIndex = getNextIndex(nextIndex);
        return array[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("The iterator doesn't allow changes.");
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Private methods">
    /**
     * Searches for the next element that matches the filtering conditions and
     * returns it.
     * 
     * @return the next element that matches the filtering conditions or null
     *          if no more elements are available
     */
    private int getNextIndex(int currIndex){
        if(nextIndex!=-1){
            for(int i=currIndex+1; i<array.length; i++){
                if(predicate.test(array[i])){
                    return i;
                }
            }
        }
        return -1;
    }
    // </editor-fold>
}
