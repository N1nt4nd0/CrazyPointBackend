package ru.feodorkek.dev.crazypoint.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("crazypoint.exception-generator")
public class ExceptionGeneratorProperties {

    private int stringUnitExceptionProbabilityPercent;

}
