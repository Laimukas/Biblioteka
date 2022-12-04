package lt.bit.biblioteka.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "registravimas")
public class Registravimas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer skait_id;

    private Integer knyg_id;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finish;

    private Integer returned;

    public Registravimas() {
    }

    public Registravimas(Integer id, Integer skaitId, Integer knygId, Date readingStart, Date readingFinished, Integer returned) {
        this.id = id;
        this.skait_id = skaitId;
        this.knyg_id = knygId;
        this.start = readingStart;
        this.finish = readingFinished;
        this.returned = returned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkaitId() {
        return skait_id;
    }

    public void setSkaitId(Integer skaitId) {
        this.skait_id = skaitId;
    }

    public Integer getKnygId() {
        return knyg_id;
    }

    public void setKnygId(Integer knygId) {
        this.knyg_id = knygId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date readingStart) {
        this.start = readingStart;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date readingFinished) {
        this.finish = readingFinished;
    }

    public Integer isReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registravimas that)) return false;
        return isReturned() == that.isReturned() && Objects.equals(getId(), that.getId()) && Objects.equals(getSkaitId(), that.getSkaitId()) && Objects.equals(getKnygId(), that.getKnygId()) && Objects.equals(getStart(), that.getStart()) && Objects.equals(getFinish(), that.getFinish());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSkaitId(), getKnygId(), getStart(), getFinish(), isReturned());
    }

    @Override
    public String toString() {
        return "Registravimas{" +
                "id=" + id +
                ", skaitId=" + skait_id +
                ", knygId=" + knyg_id +
                ", readingStart=" + start +
                ", readingFinished=" + finish +
                ", returned=" + returned +
                '}';
    }
}
