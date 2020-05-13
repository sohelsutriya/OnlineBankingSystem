/**
 * 
 */
package com.cg.obs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.obs.dao.TransactionDao;
import com.cg.obs.model.Transaction;

/**
 * @author sohel
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionDao dao;

	@Override
	public List<Transaction> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Transaction> getTransactionsByAccountNo(String accountNo) {
		return dao.findAll().stream().filter(x -> (x.getAccountNo().equals(accountNo))).collect(Collectors.toList());
	}

	@Override
	public void addTransaction(Transaction transaction) {
		if(transaction !=null)
		dao.save(transaction);
	}

}
