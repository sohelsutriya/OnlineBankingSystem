/**
 * 
 */
package com.cg.obs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.obs.model.Transaction;

/**
 * @author sohel
 *
 */
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

}
