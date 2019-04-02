package net.mirwaldt.logic.propositional.impl;

import java.util.Comparator;
import java.util.List;

public class ListComparator implements Comparator<List<Integer>> {
    
    public static final ListComparator listComparator = new ListComparator();
    
    @Override
    public int compare(List<Integer> leftList, List<Integer> rightList) {
        if(leftList.size() == rightList.size()) {
            for (int index = 0; index < leftList.size(); index++) {
                Integer left = leftList.get(index);
                Integer right = rightList.get(index);
                int result = Integer.compare(left, right);
                if(result != 0) {
                    return result;
                }
            }
            return 0;
        } else {
            throw new IllegalStateException("Different list sizes: " + leftList.size() + " vs." + rightList.size());
        }
    }
}
