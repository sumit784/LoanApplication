package com.ofg.loans.service;

import com.ofg.loans.model.Client;
import com.ofg.loans.model.Loan;
import com.ofg.loans.model.LoanApplication;

/**
 * Created by sockor on 10.05.2016.
 */
public class ClientLoanApplication extends LoanApplication {

    public ClientLoanApplication(Client client, Loan loan) {
        super(client::checkOfIP,
                client::checkEntity,
                client::financialOpportunity,
                client::reliabilityCheck,
                loan::checkOfAmount,
                loan::checkOfTime
        );
    }
}
