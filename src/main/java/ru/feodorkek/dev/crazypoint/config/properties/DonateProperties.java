package ru.feodorkek.dev.crazypoint.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("crazypoint.donate-properties")
public class DonateProperties {

    private SberBankBelarus sberBankBelarus;
    private BelarusBank belarusBank;
    private String donateLink;
    private YooMoney yooMoney;
    private PkoBank pkoBank;
    private Usdt usdt;

    @Data
    public static class BelarusBank {

        private String title;
        private String card;
        private String iban;

    }

    @Data
    public static class SberBankBelarus {

        private String title;
        private String card;
        private String iban;

    }

    @Data
    public static class PkoBank {

        private String title;
        private String card;
        private String accountNumber;
        private String iban;

    }

    @Data
    public static class YooMoney {

        private String title;
        private String wallet;

    }

    @Data
    public static class Usdt {

        private String title;
        private String address;

    }

}
