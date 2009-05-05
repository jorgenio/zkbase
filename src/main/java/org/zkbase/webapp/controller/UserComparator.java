package org.zkbase.webapp.controller;

import java.util.Comparator;

import org.zkbase.model.User;

public class UserComparator implements Comparator<User> {
    private boolean ascending;
    private int columnIndex;
    public UserComparator (boolean ascending, int columnIndex) {
        this.ascending = ascending;
        this.columnIndex = columnIndex;
    }
    public int compare(User o1, User o2) {
        System.out.println("UserComparator : " + o1.getUsername() + " vs " + o2.getUsername());
        int v = 0;
        v = o1.getUsername().compareTo(o2.getUsername());
        
        switch (columnIndex) {
        case 0:
            v = o1.getUsername().compareTo(o2.getUsername());
            break;
        case 1:
            v = o1.getFirstName().compareTo(o2.getFirstName());
            break;
    }
        return ascending ? v: -v;
    }
}
