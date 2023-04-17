package com.uptimesoftware.business.validation.oval;

import net.sf.oval.Validator;
import net.sf.oval.logging.LoggerFactorySLF4JImpl;

public class ValidatorFactory {

	static {
		Validator.setLoggerFactory(new LoggerFactorySLF4JImpl());
	}

	public static Validator getValidator() {
		// TODO Validator is a thread safe object. We don't need to create one all the time.
		Validator validator = new Validator();
		validator.getExpressionLanguageRegistry().registerExpressionLanguage("jrejs", new ExpressionLanguageJreJavaScriptImpl());
		return validator;
	}

}
