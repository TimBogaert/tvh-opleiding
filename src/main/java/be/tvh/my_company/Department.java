package be.tvh.my_company;

/**
 * Created by tim036 on 29/09/2017.
 */
public class Department extends BaseEntity {
    //    private String identifier; //TODO: ?? basis-identifier?/
    private String name;
    private String description;


    public Department(String name, String description) {
        super(); //create UUID
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department{" + getIdentifier() +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
