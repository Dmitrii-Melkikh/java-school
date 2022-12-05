package ru.croc.task14;

import java.util.*;
import java.util.function.Predicate;

public interface BlackListFilter<T> {

    /**
     * From the given list of comments removes ones
     * that contain words from the black list.
     *
     * @param comments  comments; every comment
     *                 is a sequence of words, separated
     *                 by spaces, punctuation or line breaks
     * @param predicate defines a comment that does not pass filtering
     */
    default List<T> filterComments(Iterable<T> comments, Predicate<T> predicate) {
        List<T> filterComments = new ArrayList<>();
        Iterator<T> it = comments.iterator();
        while(it.hasNext()) {
            T comment = it.next();
            if (!predicate.test(comment)){
                filterComments.add(comment);
            }
        }
        return filterComments;
    };
}
