package utility;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {
	/**
	 * TERBILANG
	 * @param angka
	 * @return
	 */
	public static String spellNumber (String angka) {
		return spellNumber(new BigDecimal(angka));
	}
	public static String spellNumber (double angka) {
		return spellNumber(new BigDecimal(angka));
	}
	public static String spellNumber (int angka) {
		return spellNumber(new BigDecimal(angka));
	}
	public static String spellNumber (float angka) {
		return spellNumber(new BigDecimal(angka));
	}
	public static String spellNumber (long angka) {
		return spellNumber(new BigDecimal(angka));
	}
	public static String spellNumber (BigDecimal angka) {
		String[] nomina={"","satu","dua","tiga","empat","lima","enam", "tujuh","delapan","sembilan","sepuluh","sebelas"};	
		
		// TODO Check Decimal Digit
		String strDecimal = "";
		BigDecimal decimal = angka.subtract(angka.setScale(0, RoundingMode.DOWN));
		if (decimal.compareTo(new BigDecimal("0")) > 0) {
			
			String remZero = decimal.toString();
			while (remZero.substring(remZero.length()-2, remZero.length()-1).equals("0")) 
				remZero = remZero.substring(0, remZero.length()-2);
			
			decimal = new BigDecimal(remZero);
			int SCALE = decimal.scale();
			for (int i=0; i<SCALE; i++) 
				decimal = decimal.multiply(new BigDecimal("10"));
			
			decimal = decimal.setScale(0, RoundingMode.DOWN);
			strDecimal = " koma " + spellNumber(decimal);
			System.out.println(strDecimal);
		}
		
		// TODO Round Number
		angka = angka.setScale(0, RoundingMode.DOWN);
		
		// TODO Salto 3x
		final String MAX = "999999999999999999";
		if (angka.compareTo(new BigDecimal(MAX)) > 0) 
			return "maksimal "+MAX.length()+" digit";
		
		else if(angka.compareTo(new BigDecimal("12")) <0 )
        	return nomina[angka.intValue()];
        
		else if(angka.compareTo(new BigDecimal("12")) >=0 && angka.compareTo(new BigDecimal("19")) <=0 )
        	return nomina[angka.intValue()%10] +" belas ";
        
		else if(angka.compareTo(new BigDecimal("20")) >=0 && angka.compareTo(new BigDecimal("99")) <=0 )
        	return nomina[angka.intValue()/10] +" puluh "+nomina[angka.intValue()%10];
        
		else if(angka.compareTo(new BigDecimal("100")) >= 0 && angka.compareTo(new BigDecimal("199")) <=0 )
        	return "seratus "+ spellNumber(angka.remainder(new BigDecimal("100")));
        
		else if(angka.compareTo(new BigDecimal("200")) >=0 && angka.compareTo(new BigDecimal("999")) <=0 ) 
			return nomina[angka.divide(new BigDecimal("100")).intValue()]+" ratus "+spellNumber(angka.remainder(new BigDecimal("100")));
			
        else if(angka.compareTo(new BigDecimal("1000")) >=0 && angka.compareTo(new BigDecimal("1999")) <=0 )
        	return "seribu "+ spellNumber(angka.remainder(new BigDecimal("1000")));
        
		else if(angka.compareTo(new BigDecimal("2000")) >= 0 && angka.compareTo(new BigDecimal("999999")) <=0 ) 
        	return spellNumber(angka.divide(new BigDecimal("1000")).setScale(0, RoundingMode.DOWN))+" ribu " + spellNumber(angka.remainder(new BigDecimal("1000")));
        	
		else if(angka.compareTo(new BigDecimal("1000000")) >= 0 && angka.compareTo(new BigDecimal("999999999")) <=0 )
        	return spellNumber(angka.divide(new BigDecimal("1000000")).setScale(0, RoundingMode.DOWN))+" juta " + spellNumber(angka.remainder(new BigDecimal("1000000")));
        
		else if(angka.compareTo(new BigDecimal("1000000000")) >= 0 && angka.compareTo(new BigDecimal("999999999999")) <=0 )
        	return spellNumber(angka.divide(new BigDecimal("1000000000")).setScale(0, RoundingMode.DOWN))+" miliar " + spellNumber(angka.remainder(new BigDecimal("1000000000")));
        
		else if(angka.compareTo(new BigDecimal("1000000000000")) >= 0 && angka.compareTo(new BigDecimal("999999999999999")) <=0 )
        	return spellNumber(angka.divide(new BigDecimal("1000000000000")).setScale(0, RoundingMode.DOWN))+" triliun "+ spellNumber(angka.remainder(new BigDecimal("1000000000000")));
        
		else if(angka.compareTo(new BigDecimal("1000000000000000")) >= 0 && angka.compareTo(new BigDecimal("999999999999999999")) <=0 )
        	return spellNumber(angka.divide(new BigDecimal("1000000000000000")).setScale(0, RoundingMode.DOWN)) +" biliun "+ spellNumber(angka.remainder(new BigDecimal("1000000000000000")));
        
        return "";
	}
	/*Format Angka Puluhan*/
	public static String currencyFormat(String nominal) {
		return Numberformat(Double.parseDouble(nominal));
	}
	public static String currencyFormat(double nominal) {
		return Numberformat(nominal);
	}
	public static String currencyFormat(long nominal) {
		return Numberformat(nominal);
	}
	public static String currencyFormat(BigDecimal nominal) {
		return Numberformat(nominal.doubleValue());
	}
	public static String currencyFormat(int nominal) {
		return Numberformat(nominal);
	}
	
	// Format Numeric untuk VB dengan tipe data varchar di database
	// Pemisah Decimal diganti dengan koma
	public static String formatVB(String value, int decimalDigit) {
		return formatVB(new BigDecimal(value.trim().replace(",", ".")), decimalDigit);
	}
	public static String formatVB(String value) {
		return formatVB(new BigDecimal(value.trim().replace(",", ".")));
	}
	public static String formatVB(double value, int decimalDigit) {
		return formatVB(new BigDecimal(value), decimalDigit);
	}
	public static String formatVB(double value) {
		return formatVB(new BigDecimal(value));
	}
	public static String formatVB(int value, int decimalDigit) {
		return formatVB(new BigDecimal(value), decimalDigit);
	}
	public static String formatVB(int value) {
		return formatVB(new BigDecimal(value));
	}
	public static String formatVB(long value, int decimalDigit) {
		return formatVB(new BigDecimal(value), decimalDigit);
	}
	public static String formatVB(long value) {
		return formatVB(new BigDecimal(value));
	}
	public static String formatVB(float value, int decimalDigit) {
		return formatVB(new BigDecimal(value), decimalDigit);
	}
	public static String formatVB(float value) {
		return formatVB(new BigDecimal(value));
	}
	public static String formatVB(BigDecimal value) {
		return formatVB(value, 2);
	}
	public static String formatVB(BigDecimal value, int decimalDigit) {
		return String.valueOf(value.setScale(decimalDigit, RoundingMode.HALF_UP)).replace(".", ",");
	}
	
	public static BigDecimal deFormatVB(String value) {
		value = value.trim();
		String decimalSeparator = value.contains(",") ? "," : value.contains(".") ? "." : null;
		String[] splitNumber 	= decimalSeparator == null ? null : value.split(decimalSeparator);
		int decimalDigit 		= splitNumber != null ? splitNumber.length > 1 ? splitNumber[1].length() : 0 : 0;
		
		return new BigDecimal(value.replace(",", ".")).setScale(decimalDigit);
	}
	
	private static String Numberformat (double amount) {
		DecimalFormat df = new DecimalFormat("#.##");
		amount = Double.parseDouble(df.format(amount).replace(",", "."));
		
		NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
		String number = formatter.format(amount).replace(",", "*").replace(".", ",").replace("*", ".");
		return (number.split("[,]").length > 1) ? number : number + ",00";
	}
	/*Format Angka Puluhan*/
	
	public static String SpaceOrNol(String data,int panjang) {
		int tot=panjang-data.length();
		String tmpNol = "";
		for (int i=1; i<=tot; i++) {
			tmpNol+="0";
		}
		data=tmpNol + data;
		return data;
	}
	public static String replaceZeroDecimal(String formatedNumber) {
		return formatedNumber.replace(",00", "").replace(",0", "");
	}
	
	public static void main(String[] args) {
		System.out.println(deFormatVB("25000"));
		System.out.println(deFormatVB("25000,999"));
		System.out.println(deFormatVB("25000,2"));
		System.out.println(deFormatVB("25000,80"));
	}
}
