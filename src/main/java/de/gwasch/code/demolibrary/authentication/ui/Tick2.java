package de.gwasch.code.demolibrary.authentication.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.gwasch.code.escframework.components.annotations.Generate;
import de.gwasch.code.escframework.components.annotations.Rule;

@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD) 

@Rule
public @interface Tick2 {
	int interval() default 1000; 
	double maxDeviationFactor() default 0.0;
	int invocations() default -1;
	@Generate String activateMethod() default "";
	@Generate String deactivateMethod() default "";
	@Generate String suspendMethod() default "";
	@Generate String resumeMethod() default "";
}
