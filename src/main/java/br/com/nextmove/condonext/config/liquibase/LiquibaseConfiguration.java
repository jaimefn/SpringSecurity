package br.com.nextmove.condonext.config.liquibase;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport
public class LiquibaseConfiguration {
/*    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/liquibase/master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }*/
}
