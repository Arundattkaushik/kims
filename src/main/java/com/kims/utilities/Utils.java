package com.kims.utilities;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class Utils {
	
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void clearCache() {
        entityManager.clear();
    }

	
	public static String generateInvoiceNumber() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the format for the invoice number
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");

        // Format the current date and time based on the defined pattern
        String invoiceNumber = now.format(formatter);

        return invoiceNumber;
    }
	
	
	
	private static final String[] tensNames = {
	        "",
	        " ten",
	        " twenty",
	        " thirty",
	        " forty",
	        " fifty",
	        " sixty",
	        " seventy",
	        " eighty",
	        " ninety"
	    };

	    private static final String[] numNames = {
	        "",
	        " one",
	        " two",
	        " three",
	        " four",
	        " five",
	        " six",
	        " seven",
	        " eight",
	        " nine",
	        " ten",
	        " eleven",
	        " twelve",
	        " thirteen",
	        " fourteen",
	        " fifteen",
	        " sixteen",
	        " seventeen",
	        " eighteen",
	        " nineteen"
	    };

		
		 private static String convertLessThanOneThousand(int number) { String
		 current;
		 
		 if (number % 100 < 20) { current = numNames[number % 100]; number /= 100; }
		 else { current = numNames[number % 10]; number /= 10;
		 
		 current = tensNames[number % 10] + current; number /= 10; } if (number == 0)
		 return current; return numNames[number] + " hundred" + current; }
		

	
	 private static String convertLessThanOneHundred(int number) { if (number <
	 20) { return numNames[number]; } return tensNames[number / 10] +
	 numNames[number % 10]; }
	 

	    public static String convert(long number) {
	        if (number == 0) {
	            return "zero";
	        }

	        String snumber = Long.toString(number);

	        // pad with "0", Just to make sure the initial length of the number it is necessary
	        String mask = "000000000";
	        DecimalFormat df = new DecimalFormat(mask);
	        snumber = df.format(number);	       
	        
	        int crores = Integer.parseInt(snumber.substring(0, 2));
	        
	        int lakhs = Integer.parseInt(snumber.substring(2, 4));
	        
	        int thousands = Integer.parseInt(snumber.substring(4, 6));
	        
	        int hundreds = Integer.parseInt(snumber.substring(6, 7));
	        
	        int rest = Integer.parseInt(snumber.substring(7, 9));

	        String result = "";

	        if (crores > 0) {
	            result += convertLessThanOneHundred(crores) + " crore ";
	        }

	        if (lakhs > 0) {
	            result += convertLessThanOneHundred(lakhs) + " lakh ";
	        }

	        if (thousands > 0) {
	            result += convertLessThanOneHundred(thousands) + " thousand ";
	        }

	        if (hundreds > 0) {
	            result += convertLessThanOneThousand(hundreds) + " hundreds ";
	        }

	        if (rest > 0) {
	            result += convertLessThanOneThousand(rest);
	        }

	        return result.trim().replaceAll("\\s+", " ");
	    }

	    //This is the final method that we have to use in any class
	    public static String numberToWords(double amount) {
	        long rupees = (long) amount;
	        long paise = Math.round((amount - rupees) * 100);

	        String rupeePart = convert(rupees);
	        String paisePart = paise > 0 ? " and " + convert(paise) + " paise" : "";

	        return "INR " + rupeePart + paisePart;
	    }
	
}
