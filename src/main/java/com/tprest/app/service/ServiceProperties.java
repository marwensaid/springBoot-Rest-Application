package com.tprest.app.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by marwen on 17/03/16.
 */

@ConfigurationProperties(prefix = "hotel.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {

    @NotNull // you can also create configurationPropertiesValidator
    private String name = "Empty";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
