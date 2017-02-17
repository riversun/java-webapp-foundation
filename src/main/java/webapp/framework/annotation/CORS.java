package webapp.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CORS {

	public String allowFrom();

	public boolean allowCredentials();

}