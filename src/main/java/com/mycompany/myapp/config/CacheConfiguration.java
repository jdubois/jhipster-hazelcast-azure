package com.mycompany.myapp.config;

import io.github.jhipster.config.JHipsterProperties;

import com.hazelcast.config.*;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.annotation.PreDestroy;

@Configuration
public class CacheConfiguration {

    private final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

    private final Environment env;

    public CacheConfiguration(Environment env) {
        this.env = env;
    }

    @PreDestroy
    public void destroy() {
        log.info("Closing Cache Manager");
        Hazelcast.shutdownAll();
    }

    @Bean
    public HazelcastInstance hazelcastInstance(JHipsterProperties jHipsterProperties) {
        log.debug("Configuring Hazelcast");
        Config config = new Config();
        config.setInstanceName("hazelcast-test-cluster");
        // If running in Azure, use the Hazelcast Azure plugin
        if (env.acceptsProfiles(Profiles.of("azure"))) {
            log.info("Configuring the Hazelcast Azure plug-in");
            config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
            config.getNetworkConfig().getJoin().getAzureConfig().setEnabled(true);
        }
        // Full reference is available at: https://docs.hazelcast.org/docs/management-center/3.9/manual/html/Deploying_and_Starting.html
        return Hazelcast.newHazelcastInstance(config);
    }
}
