/**
 * 
 */
package com.cg.obs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.obs.model.Transaction;

/**
 * @author sohel
 *
 */
@Service
public interface TransactionService {
	public List<Transaction> findAll();

	public List<Transaction> getTransactionsByAccountNo(String accountNo);

	public void addTransaction(Transaction transaction);
}
