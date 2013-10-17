package com.uptimesoftware.business.validation.oval;

import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sf.oval.exception.ExpressionEvaluationException;
import net.sf.oval.expression.ExpressionLanguage;

class ExpressionLanguageJreJavaScriptImpl implements ExpressionLanguage {

	private final ScriptEngine engine;

	ExpressionLanguageJreJavaScriptImpl() {
		// this is thread-safe because the built-in Rhino script engine has THREADING=MULTITHREADED
		engine = new ScriptEngineManager().getEngineByName("JavaScript");
	}

	@Override
	public Object evaluate(String expression, Map<String, ?> values) throws ExpressionEvaluationException {
		Bindings bindings = engine.createBindings();
		bindings.putAll(values);
		try {
			return engine.eval(expression, bindings);
		} catch (ScriptException e) {
			throw new ExpressionEvaluationException("Script failed.", e);
		}
	}

	@Override
	public boolean evaluateAsBoolean(final String expression, final Map<String, ?> values) throws ExpressionEvaluationException {
		final Object result = evaluate(expression, values);
		if (!(result instanceof Boolean)) {
			throw new ExpressionEvaluationException("The script must return a boolean value.");
		}
		return (Boolean) result;
	}

}
