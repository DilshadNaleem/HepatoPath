package com.HepatoPath.HepatoPath.Admin.Repository;

import com.HepatoPath.HepatoPath.Admin.DTO.EmergencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmergencyRequestRepository extends JpaRepository<EmergencyRequest,Long>
{
    Optional<EmergencyRequest> findByTrackingId(String trackingId);
    List<EmergencyRequest> findByStatus (String status);
}
