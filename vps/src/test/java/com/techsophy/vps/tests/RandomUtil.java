package com.techsophy.vps.tests;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RandomUtil {

	/* generate random batchnumber */
	public int generateRandomBatchNumber() {
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		int rand_batchNo = rand.nextInt(1000);
		return rand_batchNo;
	}

	/* generateRandomEnumber */
	public String generateRandomEnumber() {
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		int rand_batchNo = rand.nextInt(100000000);
		String eNumber = "E" + rand_batchNo;
		return eNumber;
	}

	/* get the today date for batch */
	public int getDayOfMonth() {
		Date date = new Date(); // your date
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}
}
