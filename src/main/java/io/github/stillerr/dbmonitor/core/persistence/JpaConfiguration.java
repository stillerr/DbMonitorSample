package io.github.stillerr.dbmonitor.core.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaRepositories(value = {
        "io.github.stillerr.dbmonitor.**.repository",
        "io.github.stillerr.dbmonitor.**.service"
})
public class JpaConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("io.github.stillerr.dbmonitor.**.domain");
        entityManagerFactoryBean.getJpaPropertyMap().put("jadira.usertype.databaseZone", "jvm");
        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.id.optimizer.pooled.prefer_lo", "true");
        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create-drop"); // migration tool required

        return entityManagerFactoryBean;
    }

}
