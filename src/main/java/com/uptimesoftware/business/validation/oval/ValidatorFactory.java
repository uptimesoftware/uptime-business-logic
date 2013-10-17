package com.uptimesoftware.business.validation.oval;

import net.sf.oval.Validator;
import net.sf.oval.logging.LoggerFactoryL4JImpl;


public class ValidatorFactory {

	static {
		Validator.setLoggerFactory(new LoggerFactoryL4JImpl());
	}

	public static Validator getValidator() {
		Validator validator = new Validator();
		validator.getExpressionLanguageRegistry().registerExpressionLanguage("jrejs", new ExpressionLanguageJreJavaScriptImpl());
		return validator;
	}

}
