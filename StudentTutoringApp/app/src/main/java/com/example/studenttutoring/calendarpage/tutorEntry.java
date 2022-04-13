package com.example.studenttutoring.calendarpage;

public class tutorEntry {

    private final String name;
    private final String start_time;
    private final String end_time;
    private final String contact_num;
    private final String email;

    tutorEntry(String name, String start_time,String end_time, String contact_num, String email){
        this.name = name;
        this.start_time=start_time;
        this.end_time=end_time;
        this.contact_num=contact_num;
        this.email=email;
    }
}
