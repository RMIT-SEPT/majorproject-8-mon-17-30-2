package com.rmit.sept.majorProject.utility;

import java.util.Comparator;
import com.rmit.sept.majorProject.dto.DateTime;

public class DateTimeSort implements Comparator<DateTime>{

    @Override
    public int compare(DateTime o1, DateTime o2) {

        int i = o1.getDate().compareTo(o2.getDate());
        if(i != 0) return i;

        i = o2.getStartTime().compareTo(o2.getStartTime());
        if(i != 0) return i;

        i = o2.getEndTime().compareTo(o2.getEndTime());
        return i;

    }
    
}
