package spring4.context.lazy;

import javax.annotation.PostConstruct;

//@Service
public class Bean {
	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct " + this.getClass().getSimpleName());
	}
}
