package haroldo.samplebank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import haroldo.samplebank.currentaccount.CurrentAccountAPI;
import haroldo.samplebank.currentaccount.CurrentAccountAPIImpl;

@Configuration
public class SampleBankAppConfig {

	@Bean
	public CurrentAccountAPI accountApi() {
		return new CurrentAccountAPIImpl();
	}
}
