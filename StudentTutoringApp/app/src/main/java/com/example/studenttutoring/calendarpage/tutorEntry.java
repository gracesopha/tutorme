package com.example.studenttutoring.calendarpage;

import java.io.Serializable;

public class tutorEntry implements Serializable {

    public String getName() {
        return name;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getContact_num() {
        return contact_num.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }

    public String getEmail() {
        return email;
    }

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
