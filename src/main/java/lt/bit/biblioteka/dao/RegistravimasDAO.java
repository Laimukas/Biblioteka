package lt.bit.biblioteka.dao;

import lt.bit.biblioteka.data.Knyga;
import lt.bit.biblioteka.data.Registravimas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistravimasDAO extends JpaRepository<Registravimas, Integer> {

    @Query("select r from Registravimas r where r.skait_id = :skait and r.returned = 0")
    public List<Registravimas> pagalSkaitytoja(
            @Param("skait")
            Integer skait);

    @Query("select r from Registravimas r where r.skait_id = :skait and r.knyg_id = :knyg")
    public List<Registravimas> pagalSkIdIrKnId(
            @Param("skait")
            Integer skait,
            @Param("knyg")
            Integer knyg);

    @Query("select r from Registravimas r where r.skait_id = :skait and r.returned = 1")
    public List<Registravimas> pagalSkIdPerskaitytosKn(
            @Param("skait")
            Integer skait);
}
