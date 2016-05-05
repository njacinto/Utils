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
package org.nfpj.utils.collections;

import java.util.Iterator;
import java.util.function.Predicate;
import org.nfpj.utils.IteratorWithSize;

/**
 *
 * @author njacinto
 * @param <T> the type of object being returned by this iterator
 */
public class ListPageFilterIteratorDescendingWithSize<T> extends CollectionPageFilterIterator<T> implements IteratorWithSize<T> {
    
    // <editor-fold defaultstate="expanded" desc="Constructors">
    /**
     * Creates an instance of this class
     * 
     * @param it the iterator from where this instance will extract the elements
     * @param offset
     * @param count
     * @param predicate the filter to be applied to the elements
     */
    public ListPageFilterIteratorDescendingWithSize(Iterator<T> it, int offset, int count, Predicate<T> predicate) {
        super(it, offset, count, predicate);
    }

    /**
     * Creates an instance of this class
     * 
     * @param collection the collection from where this instance will extract the elements
     * @param offset
     * @param count
     * @param predicate the filter to be applied to the elements
     */
    public ListPageFilterIteratorDescendingWithSize(Iterable<T> collection, int offset, int count, Predicate<T> predicate) {
        super(collection, offset, count, predicate);
    }
    // </editor-fold>
    // <editor-fold defaultstate="expanded" desc="Public methods">
    /**
     * {@inheritDoc} 
     */
    @Override
    public int size() {
        return next!=null ? -1 : countElements;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Protected methods">
    /**
     * Searches for the next element that matches the filtering conditions and
     * returns it.
     * 
     * @return the next element that matches the filtering conditions or null
     *          if no more elements are available
     */
    @Override
    protected T getNext(){
        T tmp;
        while(it.hasNext()){
            if(predicate.test(tmp=it.next())){
                countElements++;
                if(countElements>fromIndex && countElements<=toIndex){
                    return tmp;
                }
            }
        }
        return null;
    }
    // </editor-fold>
}
