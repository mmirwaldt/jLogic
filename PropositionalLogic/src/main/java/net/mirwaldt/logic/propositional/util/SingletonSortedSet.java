package net.mirwaldt.logic.propositional.util;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SingletonSortedSet<E>
        extends AbstractSet<E>
        implements Serializable, SortedSet<E> {
    private final E element;
    private final Comparator<? super E> comparator;

    private SingletonSortedSet(E e, Comparator<? super E> comparator) {
        element = e;
        this.comparator = comparator;
    }
    
    public static <E> SortedSet<E> singletonSortedSet(E e, Comparator<? super E> comparator) {
        return new SingletonSortedSet<>(e, comparator);
    }

    public Iterator<E> iterator() {
        return singletonIterator(element);
    }

    public int size() {
        return 1;
    }

    public boolean contains(Object o) {
        return Objects.equals(o, element);
    }

    // Override default methods for Collection
    @Override
    public void forEach(Consumer<? super E> action) {
        action.accept(element);
    }

    @Override
    public Spliterator<E> spliterator() {
        return singletonSpliterator(element);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(element);
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        if(contains(fromElement) || contains(toElement)) {
            return this;
        } else {
            return Collections.emptySortedSet();
        }
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        if(contains(toElement)) {
            return this;
        } else {
            return Collections.emptySortedSet();
        }
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        if(contains(fromElement)) {
            return this;
        } else {
            return Collections.emptySortedSet();
        }
    }

    @Override
    public E first() {
        return element;
    }

    @Override
    public E last() {
        return element;
    }

    private static <E> Iterator<E> singletonIterator(final E e) {
        return new Iterator<E>() {
            private boolean hasNext = true;
            public boolean hasNext() {
                return hasNext;
            }
            public E next() {
                if (hasNext) {
                    hasNext = false;
                    return e;
                }
                throw new NoSuchElementException();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
            @Override
            public void forEachRemaining(Consumer<? super E> action) {
                Objects.requireNonNull(action);
                if (hasNext) {
                    hasNext = false;
                    action.accept(e);
                }
            }
        };
    }
    
    private static <T> Spliterator<T> singletonSpliterator(final T element) {
        return new Spliterator<T>() {
            long est = 1;

            @Override
            public Spliterator<T> trySplit() {
                return null;
            }

            @Override
            public boolean tryAdvance(Consumer<? super T> consumer) {
                Objects.requireNonNull(consumer);
                if (est > 0) {
                    est--;
                    consumer.accept(element);
                    return true;
                }
                return false;
            }

            @Override
            public void forEachRemaining(Consumer<? super T> consumer) {
                tryAdvance(consumer);
            }

            @Override
            public long estimateSize() {
                return est;
            }

            @Override
            public int characteristics() {
                int value = (element != null) ? Spliterator.NONNULL : 0;

                return value | Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.IMMUTABLE |
                        Spliterator.DISTINCT | Spliterator.SORTED;
            }
        };
    }
}
