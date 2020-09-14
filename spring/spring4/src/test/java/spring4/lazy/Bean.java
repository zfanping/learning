package spring4.lazy;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Bean {
	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct " + this.getClass().getSimpleName());
	}
}