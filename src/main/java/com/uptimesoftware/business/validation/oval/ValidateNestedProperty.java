package com.uptimesoftware.business.validation.oval;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.oval.configuration.annotation.Constraint;

/**
 * Check if the value passes a validation by Validator.validate(), retaining the nested constraint violations. See
 * http://bijubnair.blogspot.com/2011/03/validation-of-nested-properties-with.html
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE })
@Constraint(checkWith = ValidateNestedPropertyCheck.class)
public @interface ValidateNestedProperty {
	/**
	 * Formula returning <code>true</code> if this constraint shall be evaluated and <code>false</code> if it shall be ignored for
	 * the current validation.
	 * <p>
	 * <b>Important:</b> The formula must be prefixed with the name of the scripting language that is used. E.g.
	 * <code>groovy:_this.amount > 10</code>
	 * <p>
	 * Available context variables are:<br>
	 * <b>_this</b> -&gt; the validated bean<br>
	 * <b>_value</b> -&gt; the value to validate (e.g. the field value, parameter value, method return value, or the validated
	 * bean for object level constraints)
	 */
	String when() default "";
}