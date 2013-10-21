package com.locatemystickers;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class Auth {
	private static final String AUTH_TOKEN_TYPE = null;
	private Account _account;
	private AccountManager _am;
	
	/**
	 * @param context
	 */
	public Auth(Context context)
	{
		_am = AccountManager.get(context);
		Account[] accounts = _am.getAccountsByType("com.google");
		if (accounts.length ==  1)
		{
			this.set_account(accounts[0]);
		}
	}
	
	public void AuthToken(Activity activity)
	{
		_am.getAuthToken(_account,
						AUTH_TOKEN_TYPE,
						null,
						activity,
						new AccountManagerCallback<Bundle>()
						{
							@Override
							public void run(AccountManagerFuture<Bundle> arg0) {
							    String token;
								try {
									token = arg0.getResult().getString(AccountManager.KEY_AUTHTOKEN);
								    useTasksAPI(token);
								} catch (OperationCanceledException e) {
									e.printStackTrace();
								} catch (AuthenticatorException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}							
							}

							private void useTasksAPI(String token) {
							}
						},
						null);
	}
	
	/**
	 * @param context
	 * @return array of account
	 */
	public Account[] listAccountManager()
	{
		Account[] accounts = _am.getAccountsByType("com.google");
		return accounts;
	}

	
	/**
	 * @return the _account
	 */
	public Account get_account() {
		return _account;
	}

	/**
	 * @param _account the _account to set
	 */
	public void set_account(Account _account) {
		this._account = _account;
	}

	/**
	 * @return the _am
	 */
	public AccountManager get_am() {
		return _am;
	}

	/**
	 * @param _am the _am to set
	 */
	public void set_am(AccountManager _am) {
		this._am = _am;
	}
}
