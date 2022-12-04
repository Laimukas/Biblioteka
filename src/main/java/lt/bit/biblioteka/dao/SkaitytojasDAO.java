package lt.bit.biblioteka.dao;

import lt.bit.biblioteka.data.Knyga;
import lt.bit.biblioteka.data.Skaitytojas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkaitytojasDAO extends JpaRepository<Skaitytojas, Integer> {

    @Query("select s from Skaitytojas s where s.id = :skId")
    public List<Skaitytojas> pagalId(
            @Param("skId")
            Integer skId);
}
