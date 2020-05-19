package haroldo.samplebank.currentaccount;

import java.util.ArrayList;
import java.util.Date;

import haroldo.samplebank.entity.CurrentAccount_v1;
import haroldo.samplebank.entity.CurrentAccount_v2;
import haroldo.samplebank.entity.InvolvedParty_v1;

public class CurrentAccountAPIImpl implements CurrentAccountAPI {

  @Override
  public CurrentAccount_v1 getAccount_v1(Long id) {
    if (id == 0)
      return null;
    CurrentAccount_v1 account = new CurrentAccount_v1();
    account.setId(id);
    account.setBalance(100);
    account.setAccountNumber("1235678");
    account.setOverdraft(0);
    return account;
  }

  @Override
  public CurrentAccount_v2 getAccount_v2(Long id) {
    if (id == 0)
      return null;
    CurrentAccount_v2 account = new CurrentAccount_v2();
    account.setId(id);
    account.setBalance(100);
    account.setAccountNumber("1235678");
    account.setOverdraft(0);
    account.setType("FLEX");
    return account;
  }

  @Override
  public CurrentAccount_v1 register_v1(CurrentAccount_v1 account) {
    return account;
  }

  @Override
  public CurrentAccount_v2 register_v2(CurrentAccount_v2 account) {
    return account;
  }

  @Override
  @SuppressWarnings("deprecation")
  public InvolvedParty_v1 getInvolvedParty_v1(Long id) {
    InvolvedParty_v1 involvedParty = new InvolvedParty_v1();
    involvedParty.setId(id);
    involvedParty.setName("John Doe");
    involvedParty.setDateOfBirth(new Date(2000, 07, 25));
    involvedParty.setEmail("john.doe@email.com");
    involvedParty.setPhone("+44 (0)1793-123456");

    return involvedParty;
  }

  @Override
  public ArrayList<InvolvedParty_v1> findInvolvedParties_v1(InvolvedParty_v1 involvedParty) {
    ArrayList<InvolvedParty_v1> involvedParties = new ArrayList<InvolvedParty_v1>();
    involvedParties.add(getInvolvedParty_v1(1L));
    return involvedParties;
  }

  @Override
  public InvolvedParty_v1 register_v1(InvolvedParty_v1 involvedParty) {
    return involvedParty;
  }

}
