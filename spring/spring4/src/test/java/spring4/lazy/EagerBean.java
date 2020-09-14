package spring4.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Lazy(false)
public class EagerBean {
	@PostConstruct
	public void postConstruct() {
		System.out.println("postConstruct " + this.getClass().getSimpleName());
	}
}
