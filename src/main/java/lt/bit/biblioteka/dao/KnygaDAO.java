package lt.bit.biblioteka.dao;

import lt.bit.biblioteka.data.Knyga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KnygaDAO extends JpaRepository<Knyga, Integer> {

    @Query("select k from Knyga k where k.author = :authorR")
    public List<Knyga> pagalAutoriu(
            @Param("authorR")
            String authorR);

    @Query("select k from Knyga k where k.type = :typeR")
    public List<Knyga> pagalTipa(
            @Param("typeR")
            String typeR);

    @Query("select k from Knyga k where k.id = :knId")
    public Knyga pagalId(
            @Param("knId")
            Integer knId);

}
