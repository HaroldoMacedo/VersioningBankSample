package haroldo.samplebank.entity;

public class CurrentAccount_v2 {

  private long id;
  private String accountNumber;
  private float balance;
  private long overdraft;
  private String type;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return String.format("CurrentAccount_v2: Id=%4d, Type=%s, Account Number=%s, Balance=%5.2f, Overdraft=%d", id, type, accountNumber, balance, overdraft);
  }
}

