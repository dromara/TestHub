package org.dromara.testhub.server.core.config;

import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.parser.RuleConfigBuilder;
import org.dromara.testhub.server.core.rule.DbRuleConfigBuilder;
import org.dromara.testhub.server.core.rule.DbRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Configuration
public class RuleConfiguration {
    @Autowired
    private DbRuleManager dbRuleManager;

    @Bean
    public RuleConfigBuilder getRuleConfigBuilder() {
        return new DbRuleConfigBuilder();
    }

    @Bean
    public FlywayMigrationInitializer flywayMigrationInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway);
    }

    @Bean
    @DependsOn({"pluginFactory","flywayMigrationInitializer"})
    public RuleConfig getRuleConfig() {
        log.info("=================开始初始化RuleConfig");
        RuleConfig ruleConfig = null;
        try {
            ruleConfig = new DbRuleConfigBuilder().build(dbRuleManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("=================结束初始化RuleConfig");
        return ruleConfig;
    }

}
