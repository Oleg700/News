package com.epam.news.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Provides initialization of {@link AppConfig} and {@link WebConfig}.
 *
 * @author Oleg Aliyev
 */
public class NewsWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * returns JavaConfig class with beans instructions.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * returns instructions for web services.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * setting servlet mapping.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
