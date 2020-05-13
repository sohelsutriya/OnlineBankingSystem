/**
 * 
 */
package com.cg.obs.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.obs.model.Account;
import com.cg.obs.model.Transaction;
import com.cg.obs.service.FundTransferService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author sohel
 *
 */
@RestController
@Api
public class FundTransferController {
	@Autowired
	FundTransferService service;
	private static final Logger logger = LoggerFactory.getLogger(FundTransferController.class);

	@ApiOperation(value = "fundTransfer", nickname = "fundTransfer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Transaction.class),
			@ApiResponse(code = 500, message = "Failure", response = Transaction.class) })
	@PostMapping(path = "/fundtransfer/from/{senderAccountNo}/to/{recieverAccountNo}/{amount}")
	public String fundTransfer(@PathVariable String senderAccountNo, @PathVariable String recieverAccountNo,
			@PathVariable double amount) {
		System.out.println("this will transfer funds from one account to other and update transactions also");
		// Account senderAccount;
		// Account recieverAccount;
		System.out.println(senderAccountNo + recieverAccountNo + amount);
		if (service.ifAccountExist(senderAccountNo) && service.ifAccountExist(recieverAccountNo)) {
			try {
				Account senderAccount = service.getAccountByAccountNo(senderAccountNo);
				Account recieverAccount = service.getAccountByAccountNo(recieverAccountNo);
				if (senderAccount.getBalance() >= amount + 500) {
					System.out.println(senderAccount);
					senderAccount.setBalance(senderAccount.getBalance() - amount);
					service.updateAccount(senderAccount);
					service.postTransaction(new Transaction("Debit", new Date(), amount, senderAccountNo));
					System.out.println(recieverAccount);
					recieverAccount.setBalance(recieverAccount.getBalance() + amount);
					service.updateAccount(recieverAccount);
					service.postTransaction(new Transaction("Credit", new Date(), amount, recieverAccountNo));
					return "fund transferred";
				} else
					return "Balance Not avaialable";
			} catch (Exception exc) {
				logger.info(exc.getMessage());
			}
			return "Insufficient Balance";
		} else {
			logger.info("Account not found");
			return "there is some issue transferring funds";
		}
	}

	@GetMapping(path = "/test/{accountNo}")
	public boolean test(@PathVariable String accountNo) {
		return service.ifAccountExist(accountNo);
	}
}
