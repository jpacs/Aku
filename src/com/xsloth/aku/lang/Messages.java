package com.xsloth.aku.lang;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static String BUNDLE_NAME = "com.xsloth.aku.lang.messages"; //$NON-NLS-1$

	private static String CURRENT_LANG = "pt";
	
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME.concat("_"+CURRENT_LANG));

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getCurrentLang() {
			return CURRENT_LANG;
	}
	
	public static boolean setLocale(String locale){
		if(isCountryCode(locale)){
			CURRENT_LANG = locale;
			RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME.concat("_"+CURRENT_LANG));
			return true;
		}
		return false;
	}
	
	public static boolean isCountryCode(String code){
		if(code.contentEquals("en") || code.contentEquals("pt") || code.contentEquals("fr")){
			return true;
		}
		return false;
	}
}
