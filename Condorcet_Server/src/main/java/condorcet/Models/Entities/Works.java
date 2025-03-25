package condorcet.Models.Entities;

public class Works
{
    private int task_Id;
    private String task_FIO;
    private String number_groups;
    private String ryk_;
    private String works;
    private String status_works;
    private String accepted_time;
    private String check_time;

    // Constructor
    public Works(int task_Id, String task_FIO, String number_groups, String ryk_, String works, String status_works, String accepted_time, String check_time) {
        this.task_Id = task_Id;
        this.task_FIO = task_FIO;
        this.number_groups = number_groups;
        this.ryk_ = ryk_;
        this.works = works;
        this.status_works = status_works;
        this.accepted_time = accepted_time;
        this.check_time = check_time;
    }

    public Works() {

    }


    // Getter and Setter for task_Id
    public int getTask_Id() {
        return task_Id;
    }
    public void setTask_Id(int task_Id) {
        this.task_Id = task_Id;
    }

    // Getter and Setter for task_FIO
    public String getTask_FIO() {
        return task_FIO;
    }
    public void setTask_FIO(String task_FIO) {
        this.task_FIO = task_FIO;
    }

    // Getter and Setter for number_groups
    public String getNumber_groups() {
        return number_groups;
    }
    public void setNumber_groups(String number_groups) {
        this.number_groups = number_groups;
    }

    // Getter and Setter for ryk_
    public String getRyk_() {
        return ryk_;
    }
    public void setRyk_(String ryk_) {
        this.ryk_ = ryk_;
    }

    // Getter and Setter for works
    public String getWorks() {
        return works;
    }
    public void setWorks(String works) {
        this.works = works;
    }

    // Getter and Setter for status_works
    public String getStatus_works() {
        return status_works;
    }
    public void setStatus_works(String status_works) {
        this.status_works = status_works;
    }


    // Getter and Setter for time_file
    public String getAccepted_time() {
        return accepted_time;
    }
    public void setAccepted_time(String accepted_time) {
        this.accepted_time = accepted_time;
    }

    // Getter and Setter for time_file
    public String getCheck_time() {
        return check_time;
    }
    public void setCheck_time(String check_time) {
        this.check_time = check_time;}
}

