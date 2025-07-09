package com.mwu.webadmin.services;

import com.mwu.model.entity.LeaseAgreement;
import com.mwu.model.enums.LeaseStatus;

import java.util.Date;
import java.util.List;

public interface LeaseAgreementService {
    List<LeaseAgreement> findByEndDateAndStatus(Date now, List<LeaseStatus> list);

    void update(LeaseAgreement agreement);
}
