package com.api.utils;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

import io.qameta.allure.Step;

public class VaultDBConfig {
	
	private static VaultConfig vaultConfig;
	private static Vault vault;
	private static LogicalResponse response;
	private static Map<String,String> dataMap;
	
	private static final Logger LOGGER = LogManager.getLogger(VaultDBConfig.class);

	static
	{
		try {
			vaultConfig = new VaultConfig()
					.address(System.getenv("VAULT_SERVER"))
					.token(System.getenv("VAULT_TOKEN"))
					.build();
		} 
		
		catch (VaultException e) 
		{
			LOGGER.error("Something went wrong with the vault config",e);
			
			e.printStackTrace();
		}
		
		vault = new Vault(vaultConfig);
	}
	
	private VaultDBConfig()
	{
		
	}
	
	@Step("Retrieving the secret from the vault")
	public static String getSecret(String key)
	{
		try {
			
			response = vault.logical().read("secret/phoenix/qa/database");
			
			dataMap = response.getData();
			
		} 
		
		catch (VaultException e) {
			
			LOGGER.error("Something went wrong with the reading of vault response",e);

			e.printStackTrace();
			
			return null;
		}
		
		String secretValue = dataMap.get(key);
		
		LOGGER.info("Secret found in the vault");
		
		return secretValue;
		
	}
	
	
}
