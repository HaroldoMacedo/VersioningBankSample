package haroldo.samplebank.entity;

public class CurrentAccount_v1 {

  private long id;
  private String accountNumber;
  private float balance;
  private long overdraft;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public long getOverdraft() {
    return overdraft;
  }

  public void setOverdraft(long overdraft) {
    this.overdraft = overdraft;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }
  
  @Override
  public String toString() {
    return String.format("CurrentAccount_v1: Id=%4d, Account Number=%s, Balance=%5.2f, Overdraft=%d", id, accountNumber, balance, overdraft);
  }
}

