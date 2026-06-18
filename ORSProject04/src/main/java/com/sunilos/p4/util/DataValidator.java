package com.sunilos.p4.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * This class validates input data
 *
 * @author Rays EdTech
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */

public class DataValidator {

	// Compiled once; handles user+tag@sub.example.com and standard addresses
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[_A-Za-z0-9+\\-]+(\\.[_A-Za-z0-9+\\-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	/**
	 * Checks if value is Null
	 */
	public static boolean isNull(String val) {
		return val == null || val.trim().isEmpty();
	}

	/**
	 * Checks if value is NOT Null
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 */
	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Checks if value is Long
	 */
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				Long.parseLong(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Checks if value is a valid Email ID
	 */
	public static boolean isEmail(String val) {
		return isNotNull(val) && EMAIL_PATTERN.matcher(val).matches();
	}

	/**
	 * Validates email address format using a regular expression.
	 *
	 * @param val email string
	 * @return true if valid email, otherwise false
	 */
//    public static boolean isEmail(String val) {
//
//        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        if (isNotNull(val)) {
//            try {
//                return val.matches(emailreg);
//            } catch (NumberFormatException e) {
//                return false;
//            }
//
//        } else {
//            return false;
//        }
//    }

	/**
	 * Validates a name. Allows alphabets, spaces, dots, and hyphens.
	 *
	 * @param val name string
	 * @return true if valid name, otherwise false
	 */
	public static boolean isName(String val) {

		String namereg = "^[^-\\s][\\p{L} .'-]+$";

		if (isNotNull(val)) {
			try {
				return val.matches(namereg);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Validates roll number format: Two alphabets followed by three digits (e.g.,
	 * AB123)
	 *
	 * @param val roll number string
	 * @return true if valid roll number, otherwise false
	 */
	public static boolean isRollNo(String val) {

		String rollreg = "[a-zA-Z]{2}[0-9]{3}";

		if (isNotNull(val)) {
			try {
				return val.matches(rollreg);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Validates password based on the following rules:
	 * <ul>
	 * <li>At least one digit</li>
	 * <li>At least one lowercase letter</li>
	 * <li>At least one uppercase letter</li>
	 * <li>At least one special character</li>
	 * <li>No whitespace</li>
	 * <li>Length between 8 and 12</li>
	 * </ul>
	 *
	 * @param val password string
	 * @return true if password is strong, otherwise false
	 */
	public static boolean isPassword(String val) {

		String passreg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}";

		if (isNotNull(val)) {
			try {
				return val.matches(passreg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks password length (8 to 12 characters).
	 *
	 * @param val input password string
	 * @return true if length is valid, otherwise false
	 */
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Validates a 10-digit phone number starting with digits 6-9.
	 *
	 * @param val phone number string
	 * @return true if valid phone number, otherwise false
	 */
	public static boolean isPhoneNo(String val) {

		String phonereg = "^[6-9][0-9]{9}$";

		if (isNotNull(val)) {
			try {
				return val.matches(phonereg);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if phone number length is exactly 10.
	 *
	 * @param val phone string
	 * @return true if 10 digits long, otherwise false
	 */
	public static boolean isPhoneLength(String val) {

		if (isNotNull(val) && val.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is a valid Date
	 */
	public static boolean isDate(String val) {
		if (isNotNull(val)) {
			Date d = DataUtility.getDate(val);
			return d != null;
		}
		return false;
	}

	/**
	 * Validates whether a string represents a valid date using DataUtility format.
	 *
	 * @param val date string
	 * @return true if valid date, otherwise false
	 */
//    public static boolean isDate(String val) {
//
//        Date d = null;
//        if (isNotNull(val)) {
//            d = DataUtility.getDate(val);
//        }
//        return d != null;
//    }

	/**
	 * Checks whether the given date string falls on Sunday.
	 *
	 * @param val date string in format yyyy-MM-dd
	 * @return true if Sunday, otherwise false
	 */
	public static boolean isSunday(String val) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(DataUtility.getDate(val));
		int i = cal.get(Calendar.DAY_OF_WEEK);

		if (i == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}
}
