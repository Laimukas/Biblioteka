package lt.bit.biblioteka.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "knygos")
public class Knyga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String author;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date year;

    private String type;


    public Knyga() {
    }

    public Knyga(Integer id, String name, String author, Date year, String type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knyga knyga)) return false;
        return Objects.equals(getId(), knyga.getId()) && Objects.equals(getName(), knyga.getName()) && Objects.equals(getAuthor(), knyga.getAuthor()) && Objects.equals(getYear(), knyga.getYear()) && Objects.equals(getType(), knyga.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAuthor(), getYear(), getType());
    }

    @Override
    public String toString() {
        return "Knyga{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", buildYear=" + year +
                ", type='" + type + '\'' +
                '}';
    }
}
