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
import org.nfpj.utils.PageIterator;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 * @param <T> the type of object being returned by this iterator
 */
public class ArrayPagedFilterIterator<T> implements PageIterator<T> {
    
    protected final T[] array;
    protected final Predicate<T> predicate;
    protected final int fromIndex;
    protected final int toIndex;
    protected int nextIndex = 0;
    protected int countElements = 0;

    // <editor-fold defaultstate="expanded" desc="Constructors">
    /**
     * Creates an instance of this class
     * 
     * @param array the array from where this instance will extract the elements
     * @param fromIndex
     * @param toIndex
     * @param predicate the filter to be applied to the elements
     */
    public ArrayPagedFilterIterator(T[] array, int fromIndex, int toIndex, Predicate<T> predicate) {
        if(fromIndex>=toIndex){
            throw new IllegalArgumentException("fromIndex cannot be bigger or equal to toIndex");
        }
        this.array = array!=null ? array : ArrayUtil.empty();
        this.predicate = predicate!=null ? predicate : TruePredicate.getInstance();
        this.fromIndex = fromIndex<0 ? 0 : fromIndex;
        this.toIndex = toIndex;
        this.nextIndex = getNextIndex(-1);
    }
    // </editor-fold>
    // <editor-fold defaultstate="expanded" desc="Public methods">
    /**
     * {@inheritDoc} 
     */
    @Override
    public int getFromIndex() {
        return fromIndex;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public int getToIndex() {
        return toIndex;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public int getPage() {
        return (fromIndex/(toIndex-fromIndex))+1;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public int getPageSize() {
        return (nextIndex!=-1)? -1 : countElements-fromIndex;
    }
    
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
     * @param currIndex
     * @return the next element that matches the filtering conditions or null
     *          if no more elements are available
     */
    protected int getNextIndex(int currIndex){
        if(nextIndex!=-1 && countElements<toIndex){
            for(int i=currIndex+1; i<array.length; i++){
                if(predicate.test(array[i])){
                    countElements++;
                    if(countElements>fromIndex){
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    // </editor-fold>
}
