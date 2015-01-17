package com.surfacetension.keentime;

import java.util.Date;

/**
 * Created by simonkenny on 14/01/15.
 */
public class EventData {

    private long id = -1;
    private String customer = "Customer";
    private String project = "Project";
    private String task = "Task";
    private Date start = new Date();
    private Date end = new Date();
    private String user = "User";
    private String collection = "Collection";

    // --- Constructors
    public EventData() {}

    public EventData(String customer, String project, String task) {
        setCustomer(customer);
        setProject(project);
        setTask(task);
    }

    // --- Calulations
    public long calculateDurationMillis() {
        return end.getTime() - start.getTime();
    }

    public float calculateDurationSecs() {
        long millis = calculateDurationMillis();
        if( millis != 0 ) {
            return (float)(((double)millis)/1000);
        }
        return 0.f;
    }

    // --- Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
