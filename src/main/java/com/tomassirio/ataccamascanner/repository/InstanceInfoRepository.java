package com.tomassirio.ataccamascanner.repository;

import com.tomassirio.ataccamascanner.model.InstanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceInfoRepository extends JpaRepository<InstanceInfo, Long> {
    InstanceInfo findInstanceInfoById(Long id);
    InstanceInfo findByInstanceName(String instanceName);

}