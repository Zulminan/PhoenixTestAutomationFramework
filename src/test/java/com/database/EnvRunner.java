package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dotenv dotenv = Dotenv.load();
		
		System.out.println(dotenv.get("DB_URL"));
		
	}

}
