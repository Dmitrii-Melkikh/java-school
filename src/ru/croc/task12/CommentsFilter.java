package ru.croc.task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommentsFilter implements BlackListFilter{

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        List<String> illegalComments = new ArrayList<>();
        for (String comment: comments){
            String newComment = comment.toLowerCase();
            for (String illegalWord: blackList){
                String newIllegalWord = illegalWord.toLowerCase();
                if (newComment.contains(newIllegalWord)){
                    illegalComments.add(comment);
                    break;
                }
            }
        }
        comments.removeAll(illegalComments);
    }
}
