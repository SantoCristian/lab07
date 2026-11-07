package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {
    private static final int AMOUNT = 100;
    private static final double TRANSACTION_FEE = 0.1;
    private static final double MANAGEMENT_FEE = 5;
    private static final int NEGATIVEAMOUNT = -100;
    private static final int ACCEPTABLE_MESSAGE_LENGTH = 10;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi ;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
         this.mRossi = new AccountHolder("Mario", "Rossi", 1);
         this.bankAccount = new StrictBankAccount(mRossi, 0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        assertEquals(0, bankAccount.getTransactionsCount());
        bankAccount.deposit(1, AMOUNT);
        assertEquals(1, bankAccount.getTransactionsCount());
        assertEquals(AMOUNT, bankAccount.getBalance());
        bankAccount.chargeManagementFees(1);
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(AMOUNT-MANAGEMENT_FEE-TRANSACTION_FEE, bankAccount.getBalance());    
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try{
            bankAccount.withdraw(1, NEGATIVEAMOUNT);
            Assertions.fail("Here i tested if bankAccount.withdraw throws an exception, if you see this message it failed");
        }catch(IllegalArgumentException e){
            assertEquals(0, bankAccount.getBalance());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length()>=ACCEPTABLE_MESSAGE_LENGTH);
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        try{
            bankAccount.withdraw(1, 100);
            Assertions.fail("Here i tested if bankAccount.withdraw throws an exception, if you see this message it failed");
        }catch(IllegalArgumentException e){
            assertEquals(0, bankAccount.getBalance());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
            assertTrue(e.getMessage().length()>=ACCEPTABLE_MESSAGE_LENGTH);
        }
        
    }
}
