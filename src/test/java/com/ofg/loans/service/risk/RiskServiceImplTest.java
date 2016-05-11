package com.ofg.loans.service.risk;

import com.ofg.loans.model.Client;
import com.ofg.loans.model.Loan;
import com.ofg.loans.service.ClientLoanApplication;
import org.junit.Before;
import org.junit.Test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Please add test cases for your risk service implementation.
 */
public class RiskServiceImplTest {

    private RiskService service;
    private Client client;
    private Loan loan;
    private ClientLoanApplication clap;

    @Before
    public void setUp() throws Exception {
        client = new Client();
        loan   = new Loan();
    }

    @Test
    public void shouldPassAgeControll() {
        client.setAge(22);
        loan.setAmount(99);
        clap = new ClientLoanApplication(client,loan);
        assertTrue(clap.approve(clap));
    }

    @Test
    public void shouldNotPassAgeControll() {
        client.setAge(18);
        loan.setAmount(99);
        clap = new ClientLoanApplication(client,loan);
        assertEquals(false, clap.approve(clap));
    }

    @Test
    public void shouldNotPassfinancialOpportunityControll() {
        client.setIncome(3000);
        client.setExpense(5000);
        loan.setAmount(99);
        clap = new ClientLoanApplication(client,loan);
        assertEquals(false, clap.approve(clap));
    }

    @Test
    public void shouldNotPassReliabilityControll() {
        client.setWasDelay(true);
        loan.setAmount(99);
        clap = new ClientLoanApplication(client,loan);
        assertEquals(false, clap.approve(clap));
    }

    @Test
    public void shouldNotPassAmountControll() {
        loan.setAmount(150);
        clap = new ClientLoanApplication(client,loan);
        assertEquals(false, clap.approve(clap));
    }

    @Test
    public void shouldPassIPControll() throws UnknownHostException {
        client.addInetAddress(new Date(), InetAddress.getByName("127.0.0.1"));
        client.addInetAddress(new Date(), InetAddress.getByName("127.0.0.1"));
        client.addInetAddress(new Date(), InetAddress.getByName("127.0.0.2"));
        clap = new ClientLoanApplication(client,loan);
        assertTrue(clap.approve(clap));
    }

    @Test
    public void shouldNotPassIPControll() throws UnknownHostException {
        client.addInetAddress(new Date(), InetAddress.getByName("127.0.0.1"));
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.addInetAddress(new Date(), InetAddress.getByName("127.0.0.1"));
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.addInetAddress(new Date(), InetAddress.getByName("127.0.0.1"));
        clap = new ClientLoanApplication(client,loan);
        assertEquals(false, clap.approve(clap));
    }

}