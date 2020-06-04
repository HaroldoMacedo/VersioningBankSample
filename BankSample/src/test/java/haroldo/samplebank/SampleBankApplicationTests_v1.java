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

import haroldo.samplebank.entity.CurrentAccount_v1;

@SpringBootTest
class SampleBankApplicationTests_v1 {
  private static final String BASE_URL = "http://localhost:8080/current-accounts";
  private HttpHeaders headersAll = new HttpHeaders();
  private HttpHeaders headersNone = new HttpHeaders();
  private HttpHeaders headersCT = new HttpHeaders();
  private HttpHeaders headersAc = new HttpHeaders();
  private HttpHeaders unsupportedHeaders = new HttpHeaders();
  private RestTemplate restTemplate = new RestTemplate();

  private SampleBankApplicationTests_v1() {
    MediaType mt = new MediaType("application", "vnd.si.v1+json");
    headersAll.setContentType(mt);
    headersAll.setAccept(Arrays.asList(mt));
    headersCT.setContentType(mt);
    headersAc.setAccept(Arrays.asList(mt));
    
    MediaType mtu = new MediaType("application", "vnd.si.v+json");
    unsupportedHeaders.setContentType(mtu);
    unsupportedHeaders.setAccept(Arrays.asList(mtu));
  }

  @Test
  public void getAccounts_Ok() {
    String url = BASE_URL;
    ResponseEntity<CurrentAccount_v1[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headersAll), CurrentAccount_v1[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1000L, response.getBody()[0].getId());

    response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headersAc), CurrentAccount_v1[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1000L, response.getBody()[0].getId());

    try {
      response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headersNone), CurrentAccount_v1[].class);
      assertTrue(false);
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode());
    }

    try {
      response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headersCT), CurrentAccount_v1[].class);
      assertTrue(false);
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode());
    }
  }

  @Test
  public void getAccount_Ok() {
    getAccount(1);
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
  public CurrentAccount_v1 getAccount(long accountId) {
    String url = BASE_URL + "/{accountId}";
    ResponseEntity<CurrentAccount_v1> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("body", headersAll), CurrentAccount_v1.class,
        accountId);
    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    assertEquals(1L, response.getBody().getId());

    return response.getBody();
  }

  @Test
  void postAccount_Ok() {
    String url = BASE_URL;

    CurrentAccount_v1 account = new CurrentAccount_v1();
    account.setId(100);
    account.setBalance(123);
    account.setAccountNumber("1235678");
    account.setOverdraft(1000);

    ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(account, headersAll), Void.class);
    assertEquals(url + "/" + account.getId(), response.getHeaders().getLocation().toString());
  }
  
  @Test
  void postAccount_Bad() {
    String url = BASE_URL;

    try {
      restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>("String", headersAll), Void.class);
      assertTrue(false);
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }
  }
  
  @Test
  void postAccount_UnsupportedMedia() {
    String url = BASE_URL;

    CurrentAccount_v1 account = new CurrentAccount_v1();
    account.setId(100);
    account.setBalance(123);
    account.setAccountNumber("1235678");
    account.setOverdraft(1000);

    try {
      restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(account, unsupportedHeaders), Void.class);
      assertTrue(false);
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e.getStatusCode());
    }
  }
}
