package domain;

import org.junit.jupiter.api.BeforeEach;

public class Student {
    private String name;
    private int age;
    private Boolean active;
    private String[] actions;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Boolean getActive() {
        return active;
    }

    public String[] getActions() {
        return actions;
    }
}
