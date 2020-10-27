/*****************************************************************c******************o*******v******id********
 * File: CustomerDaoImpl.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author vinay Patel
 */
package com.algonquincollege.cst8277.customers2.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.ejb.CustomerService;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
* Description: Implements the C-R-U-D API for the database
*/
@Named
@ApplicationScoped
public class CustomerDaoImpl implements CustomerDao, Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /**
    * dependecy injection on feild customerService
    */
    @EJB
    protected CustomerService customerService;
    
    protected ServletContext sc;
    

    @Inject
    public CustomerDaoImpl(ServletContext sc) {
        super();
        this.sc = sc;
    }
    
    protected void logMsg(String msg) {
        sc.log(msg);
    }
    

    // delegate all C-R-U-D operations to EntityManager

    /**
    * read all customers
    */
    @Override
    public List<CustomerPojo> readAllCustomers() {
        logMsg("reading all customers");
        return customerService.getAllCustomers();
        
    }

    /**
    * create a new customer
    */
    @Override
    @Transactional
    public CustomerPojo createCustomer(CustomerPojo customerToBeCreated) {
        logMsg("creating an customer");
        
        return customerService.insertNewCustomer(customerToBeCreated);
    }

    /**
    * read customer by ID
    */
    @Override
    public CustomerPojo readCustomerById(int customerId) {
        logMsg("read a specific customer");
        //return em.something(CustomerPojo.class, customerId);
        return customerService.readCustomerById(customerId);
    }

    /**
    * Update customer
    */
    @Override
    @Transactional
    public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
        logMsg("updating a specific customer");
       
        return customerService.updateCustomer(customerWithUpdates);
       
    }

    /**
    * delete customer
    */
    @Override
    @Transactional
    public void deleteCustomerById(int customerId) {
        logMsg("deleting a specific customer");
        customerService.deleteCustomer(customerId);
    }

}