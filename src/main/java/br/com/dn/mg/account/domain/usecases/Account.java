package br.com.dn.mg.account.domain.usecases;

class Account {
    private String document ;

    private String fullName;

    private Double amount;

    public Account(Double amount) {
        this.amount = amount;
    }

    public Account(String document, String fullName, Double amount) {
        this.document = document;
        this.fullName = fullName;
        this.amount = amount;
    }

    public Double sumAmount(Double value ) {
       return amount + value;
    }
}
