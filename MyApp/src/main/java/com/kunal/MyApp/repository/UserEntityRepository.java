package com.kunal.MyApp.repository;

import com.kunal.MyApp.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,String> {

    @Query(name = "select mobile_number from user_properties where mobile_number:=mobileNumber", nativeQuery = true)
    boolean findByMobileNumber(@Param("mobileNumber") String mobileNumber);
}
