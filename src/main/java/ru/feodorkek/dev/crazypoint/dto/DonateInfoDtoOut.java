package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

@Data
public class DonateInfoDtoOut {

    private final String donateLink;

    private final String belarusBankTitle;
    private final String belarusBankCard;
    private final String belarusBankIban;

    private final String sberBankTitle;
    private final String sberBankCard;
    private final String sberBankIban;

    private final String pkoBankTitle;
    private final String pkoBankCard;
    private final String pkoBankAccountNumber;
    private final String pkoBankIban;

    private final String yooMoneyTitle;
    private final String yooMoneyWallet;

    private final String usdtTitle;
    private final String usdtAddress;

}
