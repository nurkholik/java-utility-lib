package com.github.nurkholik.GeneralJavaLib;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {
	
	public final static int COMPARE_EQUAL=1;
	public final static int COMPARE_NOTEQUAL=2;
		
	public static String rightMostChar(String s,int len,boolean excluded){
		byte[] data = s.getBytes();
		int counter=0;
		int datalength = data.length;
		int n=len;
		char[] buf=new char[len];
		boolean bStart=false;
		for (int i =datalength;i > 0; i--){
			if (excluded){
				if (!bStart){
					bStart=true;
				}
				else {
					buf[--n]=(char)data[i-1];
					counter++;
				}
			}
			else {
				buf[--n]=(char) data[i-1];
				counter++;
			}			
			if (counter == len){
				break;
			}
		}
		return new String(buf);
	}	
    public static String padRight (final String s, final String pad,final int len) {
        StringBuffer d = new StringBuffer(s);
        while (d.length() < len)
            d.append(pad);
        return d.toString();
    }
    public static String left(final String s, final int len){
    	return s==null ? null : s.substring(0,len);
    }
    public static String mid(final String s,final int pos,final int len){
    	return s == null ? null : s.substring(pos-1, pos+len-1);
    }
    public static String mid(final String s,int pos){
    	return s==null ? null : s.substring(pos-1); 
    }
    public static String right(final String s,final int len){
    	return s==null ? null : s.substring(s.length()-len);
    }
    public static int instr(final String s,final String find,final int pos){
    	return s == null ? 0 : s.indexOf(find,pos-1)+1;
    }
    public static int instr(final String s,final char find,final int pos){
    	return s == null ? 0 : s.indexOf(find,pos-1)+1;
    }
    public static int instr(final String s,final String find){
    	return instr(s, find,1);
    }
    public static int instr(final String s,final char find){
    	return instr(s, find,1);
    }
	public static void main(String[] arg){
		String s = "F0F0F0F0F0111111";
		String c = right(s,6);
		System.out.println(c);
		
	}
	public static String[] redim(final String[] source,final int len){
        String[] result = new String[len];
        int srclen = source.length;
        if (srclen > len){
        	System.arraycopy(source, 0, result, 0, len);
        }
        else {
        	System.arraycopy(source, 0, result, 0, source.length);	
        }
        
        return  result;
		
	}
	public static String[] split(final String source,final String delimiter){
		String[] result = new String[0];		
		boolean stop=false;
		int pos=0;
		int start=1;
		int counter =1;
		while (!stop){
			pos = StringUtil.instr(source, delimiter,start);
			
			if (pos==0){
				stop=true;
				if (start >1){
					result=StringUtil.redim(result, counter);
					result[counter-1]=StringUtil.mid(source, start);;
					counter++;
				}
				break;
			}
			else {
				result=StringUtil.redim(result, counter);
				result[counter-1]=StringUtil.mid(source, start,pos-start);
				counter++;
			}
			start = pos + 1;
		}
		if (counter==1){
			result=StringUtil.redim(result, counter);
			result[counter-1]=StringUtil.mid(source, start);
		}
		return result;
	}
	public static String[] split(final String source,final char delimiter){
		String[] result = new String[0];		
		boolean stop=false;
		int pos=0;
		int start=1;
		int counter =1;
		while (!stop){
			pos = StringUtil.instr(source, delimiter,start);
			
			if (pos==0){
				stop=true;
				if (start >1){
					result=StringUtil.redim(result, counter);
					result[counter-1]=StringUtil.mid(source, start);;
					counter++;
				}
				break;
			}
			else {
				result=StringUtil.redim(result, counter);
				result[counter-1]=StringUtil.mid(source, start,pos-start);
				counter++;
			}
			start = pos + 1;
		}
		if (counter==1){
			result=StringUtil.redim(result, counter);
			result[counter-1]=StringUtil.mid(source, start);
		}
		return result;
	}	
	public static int compare(final String src,final String dest){
		int result = COMPARE_NOTEQUAL;
		if (src == null){
			if (dest == null){
				result = COMPARE_EQUAL;
			}
		}
		else if (src.equals(dest)){
			result = COMPARE_EQUAL;
		}
		return result;
	}
    public String strpad(String s, char pad,int len) {
        StringBuffer d = new StringBuffer();
        int n = len - s.length();
        while (n-- > 0)
            d.append(pad);
        d.append(s);
        return d.toString();
    }
    public static String trim(String s){
    	String result="";
    	if (s!=null){
    		result = s.trim();
    	}
    	return result;
    }
    public static double min(double...data){
    	double result =0;
    	boolean init = false;
    	for (double d : data){
    		if (!init){
    			result = d;
    		}
    		else if (d < result){
    			result =d;
    		}
    	}
    	return result;
    }
    
    public static boolean isNullorEmpty(String str) {
    	return str == null ? true : str.trim().equals("");
    }
    
    public static boolean isEmpty(String str) {
    	if (str != null) {
    		if (!str.trim().equals("")) {
    			if (!str.trim().equals("-")) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static boolean isValidPhone(String phone) {
    	if (!isEmpty(phone)) {
    		if (phone.startsWith("08") || phone.startsWith("+628")) {
    			return phone.length() > 9;
    		} else {
    			return false;
    		}
     	} else {
    		return false;
    	}
    }
    
	public static boolean isValidEmail(String email) {
        if (!isEmpty(email)) {
    		Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
            return matcher.find();
        } else {
        	return false;
        }
	}
	
	public static int LEFT_ALIGNMENT 	= 0;
	public static int RIGHT_ALIGNMENT 	= 1;
	public String SpaceOrCut(String data,int panjang) {
		int tot=panjang-data.length();
		for (int i=1;i<=tot;i++){data+=" ";}
		if (tot<0)  data=data.substring(0,panjang);
		return data;
	}
	
	public static String charPadding(String data, String spacer, int maxLenght, int alignment) {
		for (int i = data.length(); i<maxLenght; i++) {
			if (alignment == LEFT_ALIGNMENT) data = data + spacer;
			if (alignment == RIGHT_ALIGNMENT) data = spacer + data;
		}
		return data;
	}
	
	public static String listToString(ArrayList<String> arrList, String delimiter) {
		StringBuffer sb	= new StringBuffer();
		int size		= arrList.size();
		int i			= 1;

		for (Iterator<String> iterator = arrList.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			sb.append(string);
			if(i != size) sb.append(delimiter);
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * Generate Random String
	 * for reset password
	 * @param lenght
	 * @return
	 */
	public static String getRandomText(int lenght) {
		final String upper 	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String digits = "0123456789";
		final String lower 	= upper.toLowerCase();
		String source = upper + lower + digits;
		
		for (int i=0; i<=lenght/source.length(); i++) {
			source = source + upper + lower + digits;
		}
		
		//Suffle arraylist
		List<String> liRandom= Arrays.asList(source.split(""));
		Collections.shuffle(liRandom);
		
		//Get sublist > lenght
		int startIndex = (int) (Math.random() * (liRandom.size() -1 - lenght));
		liRandom = liRandom.subList(startIndex, startIndex+lenght);
		//logger.info("RANDOM TEXT : " + liRandom.toString());
		
		//Merge
		String strRandom = "";
		for (String str : liRandom) strRandom  = strRandom+ str;
		
		//Check random number lenght
		if (strRandom.length() < lenght) strRandom = getRandomText(lenght);
		return strRandom;
	}
	
	/**
	 * Generate Random String
	 * for reset password
	 * @param lenght
	 * @return
	 */
	public static String getRandomNumber(int lenght) {
		final String digits = "0123456789";
		String source = digits;
		
		for (int i=0; i<=lenght/digits.length(); i++) {
			source = source + digits;
		}
		
		//Suffle arraylist
		List<String> liRandom= Arrays.asList(source.split(""));
		Collections.shuffle(liRandom);
		
		//Get sublist > lenght
		int startIndex = (int) (Math.random() * (liRandom.size() -1 - lenght));
		liRandom = liRandom.subList(startIndex, startIndex+lenght);
		//logger.info("RANDOM NUMBER : " + liRandom.toString());
		
		//Merge
		String strRandom = "";
		for (String str : liRandom) strRandom  = strRandom+ str;
		
		//Check random number lenght
		if (strRandom.length() < lenght) strRandom = getRandomNumber(lenght);
		return strRandom;
	}
}
