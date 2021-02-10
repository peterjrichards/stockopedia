package net.thefuturetoday.stockopedia.securityanalytics.model;

public class AttributeDto {
    private int id;
    private String name;

    public AttributeDto() {
    }

    public AttributeDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AttributeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
