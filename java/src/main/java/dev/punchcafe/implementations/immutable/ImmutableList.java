package dev.punchcafe.implementations.immutable;

import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class ImmutableList<T> {

    public static <T> ImmutableList<T> of(final T... elements){
        return new ImmutableList<>(Lists.newArrayList(elements));
    }

    private enum ConcatType {
        PREPEND, APPEND
    }

    private final int totalSize;
    private final int domainSize;
    private final ArrayList<T> domain;
    private final ImmutableList<T> parent;
    private final ConcatType concatType;

    private ImmutableList(@Nonnull final ArrayList<T> domain){

        this.domain = domain;
        this.parent = null;
        this.concatType = null;
        this.domainSize = this.domain.size();
        this.totalSize = this.domainSize;
    }

    private ImmutableList(@Nonnull final ImmutableList<T> parent,
                          @Nonnull final ArrayList<T> domain,
                          @Nonnull final ConcatType concatType){

        this.domain = domain;
        this.parent = parent;
        this.concatType = concatType;
        this.domainSize = this.domain.size();
        this.totalSize = this.parent.totalSize + this.domainSize;
    }

    public T get(int index){
        if(parent == null){
            return this.domain.get(index);
        }
        if(index + 1 > this.totalSize || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        // extract based on concat case, or inject calculation strategy at instantiation to avoid if check
        // the next operations should be safe because we've already calculated the total domain sizes etc, and we
        // know an index isn't out of bounds, so we don't have to worry about it accessing something which isn't there
        if(this.concatType == ConcatType.PREPEND){
            if(index < this.domainSize){
                return domain.get(index);
            } else {
                return parent.get(index - this.domainSize);
            }
        } else if(this.concatType == ConcatType.APPEND) {
            if(index >= (this.parent.totalSize)){
                return this.domain.get(index - this.parent.totalSize);
            } else {
                return this.parent.get(index);
            }
        }
        throw new RuntimeException();
    }

    public ImmutableList<T> concatenate(final T... elements){
        return new ImmutableList<>(this,
                Lists.newArrayList(elements),
                ConcatType.APPEND);
    }

    public ImmutableList<T> prepend(final T... elements){
        return new ImmutableList<>(this,
                Lists.newArrayList(elements),
                ConcatType.PREPEND);
    }
}
