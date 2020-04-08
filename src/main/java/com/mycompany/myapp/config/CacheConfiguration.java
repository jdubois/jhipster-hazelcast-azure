package com.mycompany.myapp.config;

import io.github.jhipster.config.JHipsterConstants;
import io.github.jhipster.config.JHipsterProperties;

import com.hazelcast.config.*;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.annotation.PreDestroy;

@Configuration
@EnableCaching
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
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        log.debug("Starting HazelcastCacheManager");
        return new com.hazelcast.spring.cache.HazelcastCacheManager(hazelcastInstance);
    }

    @Bean
    public HazelcastInstance hazelcastInstance(JHipsterProperties jHipsterProperties) {
        log.debug("Configuring Hazelcast");
        HazelcastInstance hazelCastInstance = Hazelcast.getHazelcastInstanceByName("hazelcasttest");
        if (hazelCastInstance != null) {
            log.debug("Hazelcast already initialized");
            return hazelCastInstance;
        }
        Config config = new Config();
        config.setInstanceName("hazelcasttest");
        config.getNetworkConfig().setPort(5701);
        config.getNetworkConfig().setPortAutoIncrement(true);

        // In development, remove multicast auto-configuration
        if (env.acceptsProfiles(Profiles.of(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT))) {
            System.setProperty("hazelcast.local.localAddress", "127.0.0.1");

            config.getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);
            config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
            config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);
        }

        // If running in Azure, use the Hazelcast Azure plugin
        if (env.acceptsProfiles(Profiles.of("azure"))) {
            log.info("Configuring the Hazelcast Azure plug-in");
            config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
            config.getNetworkConfig().getJoin().getAzureConfig().setEnabled(true);
        }

        config.getMapConfigs().put("default", initializeDefaultMapConfig(jHipsterProperties));

        // Full reference is available at: https://docs.hazelcast.org/docs/management-center/3.9/manual/html/Deploying_and_Starting.html
        return Hazelcast.newHazelcastInstance(config);
    }

    private MapConfig initializeDefaultMapConfig(JHipsterProperties jHipsterProperties) {
        MapConfig mapConfig = new MapConfig();

        /*
        Number of backups. If 1 is set as the backup-count for example,
        then all entries of the map will be copied to another JVM for
        fail-safety. Valid numbers are 0 (no backup), 1, 2, 3.
        */
        mapConfig.setBackupCount(jHipsterProperties.getCache().getHazelcast().getBackupCount());

        return mapConfig;
    }
}
