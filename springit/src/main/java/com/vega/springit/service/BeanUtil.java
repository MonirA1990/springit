package com.vega.springit.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtil implements ApplicationContextAware{

	public static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;
	}
	
	public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

}
