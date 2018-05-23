package no.itera.bloggingplatform.repository.jpa;

import no.itera.bloggingplatform.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorJpaRepository extends JpaRepository<Author, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Author " +
            "set email = :email, firstName = :firstName, lastName = :lastName," +
            " phone = :phone, userName = :userName, password = :password " +
            "where id = :authorId")
    void update(
            @Param("authorId") Long id,
            @Param("email") String email,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phone") String phone,
            @Param("userName") String userName,
            @Param("password") char[] password
    );

}
