package spring4.context.lazy;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class Bean1 {
	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct " + this.getClass().getSimpleName());
	}
}
