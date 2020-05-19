package haroldo.samplebank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import haroldo.samplebank.entity.CurrentAccount_v2;

@SpringBootTest
class SampleBankApplicationTests_v2 {
  private static final String BASE_URL = "http://localhost:8080/current-accounts";
  private HttpHeaders headers = new HttpHeaders();
  private HttpHeaders unsupportedHeaders = new HttpHeaders();
  private RestTemplate restTemplate = new RestTemplate();

  private SampleBankApplicationTests_v2() {
    MediaType mt = new MediaType("application", "nbs.si.v2+json");
    headers.setContentType(mt);
    headers.setAccept(Arrays.asList(mt));
    
    MediaType mtu = new MediaType("application", "nbs.si.v+json");
    unsupportedHeaders.setContentType(mtu);
    unsupportedHeaders.setAccept(Arrays.asList(mtu));
  }

  @Test
  public void getAccounts_Ok() {
    String url = BASE_URL;
    ResponseEntity<CurrentAccount_v2[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headers), CurrentAccount_v2[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(2000L, response.getBody()[0].getId());
  }

  @Test
  public void getAccount_Ok() {
    getAccount(2);
  }
  @Test
  public void getAccount_Bad() {
    try {
      getAccount(0);
      assertTrue(false);
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }
  }
  public CurrentAccount_v2 getAccount(long accountId) {
    String url = BASE_URL + "/{accountId}";
    ResponseEntity<CurrentAccount_v2> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headers), CurrentAccount_v2.class,
        accountId);
    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    assertEquals(2L, response.getBody().getId());

    return response.getBody();
  }

  @Test
  void postAccount_Ok() {
    String url = BASE_URL;

    CurrentAccount_v2 account = new CurrentAccount_v2();
    account.setId(200);
    account.setBalance(223);
    account.setAccountNumber("2235678");
    account.setOverdraft(2000);

    ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(account, headers), Void.class);
    assertEquals(url + "/" + account.getId(), response.getHeaders().getLocation().toString());
  }
  
  @Test
  void postAccount_Bad() {
    String url = BASE_URL;

    try {
      restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>("String", headers), Void.class);
      assertTrue(false);
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }
  }
  
  @Test
  void postAccount_UnsupportedMedia() {
    String url = BASE_URL;

    CurrentAccount_v2 account = new CurrentAccount_v2();
    account.setId(200);
    account.setBalance(223);
    account.setAccountNumber("2235678");
    account.setOverdraft(2000);

    try {
      restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(account, unsupportedHeaders), Void.class);
      assertTrue(false);
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e.getStatusCode());
    }
  }
}
