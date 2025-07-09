package com.mwu.webadmin.schedule;

import com.mwu.model.entity.LeaseAgreement;
import com.mwu.model.enums.LeaseStatus;
import com.mwu.webadmin.services.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private LeaseAgreementService leaseAgreementService;

    @Scheduled(cron = "0 0 0 * * *")
    private void checkLeaseStatus() {
        Date now = new Date();
        List<LeaseAgreement> agreements = leaseAgreementService.findByEndDateAndStatus(now, Arrays.asList(LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING));
        for (LeaseAgreement agreement : agreements) {
            agreement.setStatus(LeaseStatus.EXPIRED);
            leaseAgreementService.update(agreement);
        }
    }
}
