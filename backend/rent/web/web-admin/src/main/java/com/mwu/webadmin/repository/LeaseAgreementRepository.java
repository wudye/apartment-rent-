package com.mwu.webadmin.repository;

import com.mwu.model.entity.LeaseAgreement;
import com.mwu.model.enums.LeaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaseAgreementRepository extends JpaRepository<LeaseAgreement,Long> {

    List<LeaseAgreement> findAllByStatusIn(List<LeaseStatus> signed);
}
