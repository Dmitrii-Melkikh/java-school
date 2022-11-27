package ru.croc.task12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main (String[] args) {
        CommentsFilter filter = new CommentsFilter();
        List<String> comments = new ArrayList<>(Arrays.asList("Good film", "I don't like this, but",
                "This is so stupid", "STupId film, fools", "Like that", "Foo", "fools everywhere"));
        Set<String> blacklist = Set.of("STUPID", "FOOLS");
        filter.filterComments(comments, blacklist);
        System.out.println(comments);
    }
}
