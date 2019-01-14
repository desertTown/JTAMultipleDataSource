package com.evan.jta.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.eclipse.persistence.transaction.JTATransactionController;

import javax.transaction.TransactionManager;

/**
 * @author EvanHuang
 * @date 1/14/2019 11:14 AM
 * @since
 */
public class AtomikosTransactionController extends JTATransactionController {
    private UserTransactionManager utm;

    public AtomikosTransactionController() {
        utm = new UserTransactionManager();
    }
    /**
     * INTERNAL: Obtain and return the JTA TransactionManager on this platform
     */
    protected TransactionManager acquireTransactionManager() throws Exception {
        return utm;
    }

    @Override
    public TransactionManager getTransactionManager() {

        return utm;
    }
}
