package haroldo.samplebank.currentaccount;

import java.util.ArrayList;

import haroldo.samplebank.entity.CurrentAccount_v1;
import haroldo.samplebank.entity.CurrentAccount_v2;
import haroldo.samplebank.entity.InvolvedParty_v1;

public interface CurrentAccountAPI {

  // Version 1
  public CurrentAccount_v1 getAccount_v1(Long id);

  public CurrentAccount_v1 register_v1(CurrentAccount_v1 account);

  // Version 2
  public CurrentAccount_v2 getAccount_v2(Long id);

  public CurrentAccount_v2 register_v2(CurrentAccount_v2 account);

  // Involved Party v1
  public InvolvedParty_v1 getInvolvedParty_v1(Long id);

  public ArrayList<InvolvedParty_v1> findInvolvedParties_v1(InvolvedParty_v1 involvedParty);

  public InvolvedParty_v1 register_v1(InvolvedParty_v1 involvedParty);
}
