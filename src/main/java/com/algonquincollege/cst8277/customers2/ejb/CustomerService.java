/*****************************************************************c******************o*******v******id********
 * File: CustomerService.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Vinay Patel
 * 
 */
package com.algonquincollege.cst8277.customers2.ejb;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Stateless singleton session bean - CustomerService
 */
@Singleton
public class CustomerService implements Serializable {

    private static final long serialVersionUID = 1L;
    


    public static final String CUSTOMER_PU = "acmeCustomers-PU";
    /**
     * tell the EntityManager to use the transaction persistence context
     */
    @PersistenceContext(name = CUSTOMER_PU)
    protected EntityManager em;
    
    /**
     * get all customers using named query from customer class
     */
    public List<CustomerPojo> getAllCustomers() {
        TypedQuery<CustomerPojo> allCustomersQuery = em.createNamedQuery(ALL_CUSTOMERS_QUERY_NAME, CustomerPojo.class);
        return allCustomersQuery.getResultList();
    }

    /**
     * add a new customer 
     */
    @Transactional
    public CustomerPojo insertNewCustomer(CustomerPojo customerToBeCreated) {
        
        em.persist(customerToBeCreated);
        return customerToBeCreated;
    }
    
    /**
     * read customer by ID using find 
     */
    public CustomerPojo readCustomerById(int customerId) {
        
        return em.find(CustomerPojo.class, customerId);
    }

    /**
     * update existing customer
     */
    @Transactional
    public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
        
        if(readCustomerById(customerWithUpdates.getId())!= null) {
            return em.merge(customerWithUpdates);
        }
        
        return readCustomerById(customerWithUpdates.getId());
    }

    /**
     * delete existing customer
     */
    public void deleteCustomer(int customerId) {
        
        if(readCustomerById(customerId) != null) {
            em.refresh(readCustomerById(customerId));
            em.remove(readCustomerById(customerId));
        }
    }
    
    
    
}
