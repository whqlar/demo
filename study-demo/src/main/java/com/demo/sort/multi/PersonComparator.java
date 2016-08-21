package com.demo.sort.multi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wu on 16/6/23.
 */

public enum PersonComparator implements Comparator<Person> {
    ID_SORT {
        public int compare(Person o1, Person o2) {
            return Integer.valueOf(o1.getId()).compareTo(o2.getId());
        }},
    NAME_SORT {
        public int compare(Person o1, Person o2) {
            return o1.getFullName().compareTo(o2.getFullName());
        }};

    public static Comparator<Person> decending(final Comparator<Person> other) {
        return new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return other.compare(o1, o2);
            }
        };
    }

    public static Comparator<Person> getComparator(final PersonComparator... multipleOptions) {
        return new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                for (PersonComparator option : multipleOptions) {
                    int result = option.compare(o1, o2);
                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            }
        };
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<Person>();
        Person person1 = new Person(1, "aa");
        Person person2 = new Person(2, "bb");
        Person person3 = new Person(3, "aa");
        Person person4 = new Person(4, "bb");

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        System.out.println(list);
        Collections.sort(list, decending(getComparator(NAME_SORT, ID_SORT)));
        System.out.println(list);

    }
}


