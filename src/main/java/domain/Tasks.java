package domain;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.List;

public class Tasks {
    private int id;
    private  String description;
    private EnumStatus enumStatus;
    private Date createdAt;
    private Date updatedAt;

    public Tasks() {
    }

    public Tasks(int id, String description, EnumStatus enumStatus, Date createdAt, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.enumStatus = enumStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Tasks(int id, String description, EnumStatus todo, long time, long dateTime){
    }

    public void printTasks (List<Tasks> tasksList){
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.print("ID:" +tasksList.get(i).getId());
            System.out.print(" | " + tasksList.get(i).getDescription());
            System.out.print(" | " + tasksList.get(i).getEnumStatus());
            System.out.print(" | Criada em: " + tasksList.get(i).getCreatedAt());
            System.out.println(" | Atualizada em: " + tasksList.get(i).getUpdatedAt());
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    public void setEnumStatus(EnumStatus enumStatus) {
        this.enumStatus = enumStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

