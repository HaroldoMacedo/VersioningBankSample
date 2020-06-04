package haroldo.samplebank.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import haroldo.samplebank.currentaccount.CurrentAccountAPI;
import haroldo.samplebank.entity.CurrentAccount_v1;
import haroldo.samplebank.entity.CurrentAccount_v2;

@RestController
public class CurrentAccountController {
  private CurrentAccountAPI currentAccountAPI;

  /**
   * Injection constructor.
   * 
   * @param currentAccountAPI
   */
  @Autowired
  public CurrentAccountController(CurrentAccountAPI currentAccountAPI) {
    this.currentAccountAPI = currentAccountAPI;
  }

  /**
   * GET method NO VERSION
   * 
   * @return
   */
  @GetMapping(path = "/current-accounts", produces = "application/json")
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // 507
  public void accounts() {
System.out.println("--- GET NO VERSION!! ---");    
  }
  
  /**
   * GET method v1
   * 
   * @return
   */
  @GetMapping(path = "/current-accounts", produces = "application/vnd.si.v1+json")
  public @ResponseBody List<CurrentAccount_v1> accounts_v1() {
    CurrentAccount_v1 account = currentAccountAPI.getAccount_v1(1000L);
    if (account == null) {
      throw new IllegalArgumentException("No such account with id ");
    }
System.out.println("GET V1 - " + account.toString());    
    ArrayList<CurrentAccount_v1> list = new ArrayList<CurrentAccount_v1>();
    list.add(account);
    return list;
  }

  /**
   * GET method v2
   * 
   * @return
   */
  @GetMapping(path = "/current-accounts", produces = "application/vnd.si.v2+json")
  public @ResponseBody List<CurrentAccount_v2> accounts_v2() {
    CurrentAccount_v2 account = currentAccountAPI.getAccount_v2(2000L);
    if (account == null) {
      throw new IllegalArgumentException("No such account with id ");
    }
System.out.println("GET V2 - " + account.toString());    
    ArrayList<CurrentAccount_v2> list = new ArrayList<CurrentAccount_v2>();
    list.add(account);
    return list;
  }

  /**
   * GET method v1
   * 
   * @param id
   * @return
   */
  @GetMapping(path = "/current-accounts/{id}", produces = "application/vnd.si.v1+json")
  public ResponseEntity<CurrentAccount_v1> accountDetails(@PathVariable long id) {
    CurrentAccount_v1 account = currentAccountAPI.getAccount_v1(id);
    if (account == null)
      return ResponseEntity.badRequest().build();
System.out.println("GET id V1 - " + account.toString());    

    return ResponseEntity.accepted().body(account);
  }

  /**
   * GET method v2
   * 
   * @param id
   * @return
   */
  @GetMapping(path = "/current-accounts/{id}", produces = "application/vnd.si.v2+json")
  public ResponseEntity<CurrentAccount_v2> accountDetails_v2(@PathVariable long id) {
    CurrentAccount_v2 account = currentAccountAPI.getAccount_v2(id);
    if (account == null)
      return ResponseEntity.badRequest().build();
System.out.println("GET id V2 - " + account.toString());    

    return ResponseEntity.accepted().body(account);
  }

  /**
   * POST method v1 
   * Includes a current account.
   * 
   * @param newAccount
   * @return
   */
  @PostMapping(path = "/current-accounts", consumes = {"application/json", "application/vnd.si.v1+json"})
  @ResponseStatus(HttpStatus.CREATED) // 201
  public ResponseEntity<Void> registerAccount_v1(@RequestBody CurrentAccount_v1 newAccount) {
System.out.println("POST V1 - " + newAccount.toString());    
    CurrentAccount_v1 account = currentAccountAPI.register_v1(newAccount);
    return entityWithLocation(account.getId());
  }

  /**
   * POST method v2 
   * Includes a current account.
   * 
   * @param newAccount
   * @return
   */
  @PostMapping(path = "/current-accounts", consumes = "application/vnd.si.v2+json")
  @ResponseStatus(HttpStatus.CREATED) // 201
  public ResponseEntity<Void> registerAccount_v2(@RequestBody CurrentAccount_v2 newAccount) {
System.out.println("POST V2 - " + newAccount.toString());    
    CurrentAccount_v2 account = currentAccountAPI.register_v2(newAccount);
    return entityWithLocation(account.getId());
  }

  /**
   * Prepare the URI of the created resource.
   * 
   * @param resourceId
   * @return
   */
  private ResponseEntity<Void> entityWithLocation(long resourceId) {
    // Determines URL of child resource based on the full URL of the given
    // request, appending the path info with the given resource Identifier
    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{childId}").buildAndExpand(resourceId).toUri();

    // Return an HttpEntity object - it will be used to build the
    // HttpServletResponse
    return ResponseEntity.created(location).build();
  }
  
	@GetMapping(path = "/alo" )
	public @ResponseBody String aloMundo() {
		System.out.println("Alo Mundo!");
		return "Alo Mundo!";
	}
  
}
