package com.epam.jmp.springadvancepet01.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import no.api.freemarker.java8.Java8ObjectWrapper;

@Configuration
public class FreemarkerConfig implements BeanPostProcessor
{

	@Override
	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException
	{
		return o;
	}

	@Override
	public Object postProcessAfterInitialization(Object o, String s)
			throws BeansException
	{
		if (o instanceof FreeMarkerConfigurer)
		{
			FreeMarkerConfigurer configurer = (FreeMarkerConfigurer) o;
			configurer.getConfiguration().setObjectWrapper(new Java8ObjectWrapper(freemarker.template.Configuration.getVersion()));
		}
		return o;
	}
}
