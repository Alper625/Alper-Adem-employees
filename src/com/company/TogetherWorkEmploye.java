package com.company;

public class TogetherWorkEmploye {
    private int employe1ID;
    private int employe2ID;
    private int projectId;
    private long days;

    public TogetherWorkEmploye(int employe1ID, int employe2ID, int projectId, long days) {
        this.employe1ID = employe1ID;
        this.employe2ID = employe2ID;
        this.projectId = projectId;
        this.days = days;
    }

    public int getEmploye1ID() {
        return employe1ID;
    }

    public int getEmploye2ID() {
        return employe2ID;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getDays() {
        return days;
    }
}
