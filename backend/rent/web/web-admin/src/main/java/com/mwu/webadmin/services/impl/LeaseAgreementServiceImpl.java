package com.mwu.webadmin.services.impl;

import com.mwu.model.entity.LeaseAgreement;
import com.mwu.model.enums.LeaseStatus;
import com.mwu.webadmin.repository.LeaseAgreementRepository;
import com.mwu.webadmin.services.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaseAgreementServiceImpl implements LeaseAgreementService {

    @Autowired
    private LeaseAgreementRepository leaseAgreementRepository;

    @Override
    public List<LeaseAgreement> findByEndDateAndStatus(Date now, List<LeaseStatus> list) {
        List<LeaseAgreement> agreements = leaseAgreementRepository.findAllByStatusIn(List.of(LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING));

        List<LeaseAgreement> agreementsExpired = new ArrayList<>();
        for (LeaseAgreement l : agreements) {
            if (l.getLeaseEndDate() != null && l.getLeaseEndDate().before(now)) {
                agreementsExpired.add(l);
//                l.setStatus(LeaseStatus.EXPIRED);
            }

        }
        return agreementsExpired;
    }

    @Override
    public void update(LeaseAgreement agreement) {
        agreement.setStatus(LeaseStatus.EXPIRED);
        leaseAgreementRepository.save(agreement);

    }
}
