package com.sub.repository;

import com.sub.entity.respone.DTO.CountsResp;
import com.sub.entity.respone.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {


  //SELECT * FROM response WHERE statusVat != 'Czynny' OR ISNULL(statusVat)
   @Query("Select r from Response r where r.message  IS NOT NULL")   
   List<Response> findBadResponse ();

   @Query("Select r from Response r where r.statusVat <> 'Czynny' OR r.statusVat IS NULL")
   List<Response> findBadAndNegativeResponse ();

   @Query("Select r from Response r where r.statusVat <> 'Czynny'")
   List<Response> findNegativeResponse ();

    @Query("Select r From Response r where r.message  IS NULL")
    List<Response> findGodResponse ();

    @Query("select r from Response r where r.numberChecked =?1")
    Response getSingleByNumberChecked(String numberChecked);

    @Query("select r from Response r where r.name  like  CONCAT('%',:name,'%') ")
    Response getSingleByName(@Param("name") String name);

   // @Query("select count(r) as checked from  Response r JOIN count(r) as wrong where WHERE statusvat <> \"Czynny\" ")
    //Response getCounts2();
/**
    List<Response>findByStatusVat(String status, Pageable pageRequest );
    long countAllMy(String status);
*/
    /**
    @Query( "select  (Select new (count(r) from Response r)) as checked," +
            " (Select count(r) from Response r WHERE r.statusVat <> \"Czynny\") as wrong," +
            " (Select count(r) from Response r WHERE r.statusVat = \"Czynny\") as ok, " +
            " (Select count(r) from Response r WHERE r.message is not null) as bad" +
            " from Response r")
    List<CountsResp> getCounts();
*/

/**
 @Query("SELECT c, (SELECT COUNT(m) FROM Message m WHERE m.conversation = c AND :participant MEMBER OF m.newFor) " +
         "FROM Conversation c " +
         "WHERE :participant MEMBER OF c.participants " +
         "GROUP BY c, c.lastMessage.createdDate " +
         "ORDER BY c.lastMessage.createdDate DESC")
 List<Object[]> findConversationsPerParticipant(Participant participant, Pageable pageable);
*/


 /**
  * SELECT
  *     (SELECT COUNT(*) FROM response ) as checked,
  *     (SELECT COUNT(*) FROM response WHERE statusvat <> "Czynny") as wrong,
  *     (SELECT COUNT(*) FROM response WHERE statusvat = "Czynny") as ok,
  *     (SELECT COUNT(*) FROM response WHERE message is not null) as bad
  * FROM (SELECT DISTINCT * FROM response) a limit 1;
  */


}
