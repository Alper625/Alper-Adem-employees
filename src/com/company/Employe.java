package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Employe {
    private int Id;
    private int projectId;
    private Date from;
    private Date end;
    Date date;

    public Employe(int id, int projectId, String from, String end) {

        Id = id;
        this.projectId = projectId;
        if(from.equals("NULL"))
        {

            LocalDate local=LocalDate.now();
            from =local.toString();



        }
        if(end.equals("NULL"))
        {
            LocalDate local=LocalDate.now();
            end =local.toString();

        }
        this.from=formatDate(from);
        this.end=formatDate(end);


    }
    private Date formatDate(String str)
    {

        if (str != null) {
            for (String parse : formats) {

                try {
                    return new SimpleDateFormat(parse).parse(str);
                } catch (ParseException e) {
                }
            }
        }
        return null;
    }
    private String[] formats = {
            "yyyy-MM-dd",   "yyyy-MM-dd",
            "yyyy-MM-dd",    "yyyy-MM-dd",
            "yyyy-MM-dd",     "yyyy-MM-dd",
            "MM/dd/yyyy",      "MM/dd/yyyy",
            "MM/dd/yyyy'", "MM/dd/yyyy",
            "MM/dd/yyyy",     "MM/dd/yyyy",
            "yyyy:MM:dd",        "yyyyMMdd",
            "yyyy/MM/dd",        "yyyy/MM/dd"

    };

    public int getId() {
        return Id;
    }

    public int getProjectId() {
        return projectId;
    }

    public Date getFrom() {
        return from;
    }

    public Date getEnd() {
        return end;
    }
}
