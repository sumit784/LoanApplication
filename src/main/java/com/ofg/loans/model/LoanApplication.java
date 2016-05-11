package com.ofg.loans.model;

import com.ofg.loans.Exceptions.ApplicationDenided;
import com.ofg.loans.service.risk.Criteria;
import com.ofg.loans.service.risk.RiskService;

/**
 * Loan application entity.
 */
public class LoanApplication implements RiskService {

    private final Criteria checkOfAmount;
    private final Criteria checkOfTime;
    private final Criteria checkOfIP;
    // additional checking
    private final Criteria checkEntity;
    private final Criteria financialOpportunity;
    private final Criteria reliabilityCheck;

    public LoanApplication() throws ApplicationDenided {
        throw new ApplicationDenided();
    }

    public LoanApplication(Criteria checkOfAmount, Criteria checkOfTime, Criteria checkOfIP,
                           Criteria checkEntity, Criteria financialOpportunity, Criteria reliabilityCheck)
    {
        this.checkOfAmount = checkOfAmount;
        this.checkOfTime = checkOfTime;
        this.checkOfIP = checkOfIP;
        this.checkEntity = checkEntity;
        this.financialOpportunity = financialOpportunity;
        this.reliabilityCheck = reliabilityCheck;
    }

    public void checkLoanApplication() throws ApplicationDenided
    {
        checkOfAmount.check();
        checkOfTime.check();
        checkOfIP.check();
        checkEntity.check();
        financialOpportunity.check();
        reliabilityCheck.check();
    }

    @Override
    public Boolean approve(LoanApplication loanApplication) {
        try {
            checkLoanApplication();
        } catch (ApplicationDenided e1) {
            return false;
        }
        return true;
    }
}
