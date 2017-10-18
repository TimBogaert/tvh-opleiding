package be.tvh.my_company;

/**
 * Created by tim036 on 29/09/2017.
 */
public class Project extends BaseEntity {
    String name;
    String description;

    public Project(String name, String description) {
        super(); //create UUID
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
