package rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rest.domain.GodisnjiOdmor;


public interface GodisnjiRepository extends JpaRepository<GodisnjiOdmor, Integer>{

	List<GodisnjiOdmor> findByPrihvacenOdbijenIsNull();

	//@Query("SELECT GR FROM Godisnji_Odmor GR INNER JOIN(SELECT U FROM User U INNER JOIN Lekar ON U.id = Lekar.id where Lekar.klinika=?1 UNION SELECT U FROM User U INNER JOIN Medicinska_Sestra ON U.id = Medicinska_Sestra.id where Medicinska_Sestra.klinika=?1) AS on GR.med_osoblje_id=a.id")
	//List<GodisnjiOdmor> findGodisnjeKlinike(Integer id);

}