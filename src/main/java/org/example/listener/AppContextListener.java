package org.example.listener;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.example.config.FlywayConfig;
import org.example.config.HikariCPConfig;
import org.example.dao.BouquetDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        FlywayConfig.setupFlyway().migrate();

        HikariDataSource hikariDataSource = new HikariCPConfig().setupDataSource();
        BouquetDAO bouquetDAO = new BouquetDAO(hikariDataSource);

        ctx.setAttribute("hikariDataSource", hikariDataSource);
        ctx.setAttribute("bouquetDAO", bouquetDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HikariDataSource ds = (HikariDataSource) servletContextEvent
                .getServletContext()
                .getAttribute("hikariDataSource");
        ds.close();
    }
}