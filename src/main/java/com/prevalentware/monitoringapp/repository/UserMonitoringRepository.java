package com.prevalentware.monitoringapp.repository;

import com.prevalentware.monitoringapp.model.User;
import com.prevalentware.monitoringapp.model.UserMonitoring;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserMonitoringRepository extends JpaRepository<UserMonitoring, String> {
    Page<UserMonitoring> findByUserEmailAndCreatedAtBetween(String userEmail, Date startDate, Date endDate, Pageable pageable);


    @Query(value = "SELECT top_user, COUNT(user_monitoring.user) AS record_count " +
            "FROM User top_user " +
            "LEFT JOIN UserMonitoring user_monitoring ON top_user.id = user_monitoring.user.id " +
            "WHERE user_monitoring.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY top_user " +
            "ORDER BY record_count DESC ")
    List<User> findTop3UsersWithMostRecords(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query("SELECT top_user " +
            "FROM User top_user " +
            "JOIN UserMonitoring user_monitoring ON top_user.id = user_monitoring.user.id " +
            "JOIN CountryToUser cu ON top_user.id = cu.user.id " +
            "WHERE cu.country.id = :countryId " +
            "AND user_monitoring.createdAt BETWEEN :startDate AND :endDate " +
            "AND user_monitoring.description = :eventType " +
            "GROUP BY top_user " +
            "ORDER BY COUNT(user_monitoring.id) DESC")
    Page<User> findTopUsersByEventTypeAndCountry(@Param("countryId") String countryId, @Param("eventType") String eventType, @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
}
