package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    @Modifying
    @Query("update #{#entityName} u set u.validity=false where u.id = ?1")
    void delete(Integer id);

    @Query("select case when count(u) > 0 then true else false end from #{#entityName} u where u.email = ?1 ")
    boolean userExistsWithEmail(String email);

    @Query("select case when count(u) > 0 then true else false end from #{#entityName} u where u.email = ?1 and u.id <> ?2 ")
    boolean userExistsWithEmailAndId(String email, Integer id);


    @Query("select case when count(u) > 0 then true else false end from #{#entityName} u where u.id = ?1 ")
    boolean userExistsWithId(Integer id);

    @Query("select u from #{#entityName} u where u.email = ?1 ")
    Optional<UserEntity> findByEmail(String email);

}
