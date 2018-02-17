package guru.springframework;

import org.junit.Test;

import guru.springframework.domain.Product;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class SpringBootMongodbApplicationTests {

	
	@Test
	public void mono() {
		Product p = new Product();
		p.setDescription("Description");
		
		Mono<Product> mono = Mono.just(p);
		Product pr = mono.block();
		log.info(pr.getDescription());
	}
	
	
	@Test
	public void monoTransformation() {
		Product p = new Product();
		p.setDescription("Description");
		
		Mono<Product> mono = Mono.just(p);
		String text = mono.map(pp -> {return "TRANSFORMED " + pp.getDescription();}).block();
		log.info(text);
	}
	
	
	@Test
	public void flux() {
		Product p = new Product();
		p.setDescription("Description1");
		
		Product p2 = new Product();
		p2.setDescription("Description2");
		
		Flux<Product> flux = Flux.just(p, p2);
		flux.subscribe(t->log.info(t.getDescription()));

	}
	
	
	@Test
	public void fluxFilter() {
		Product p = new Product();
		p.setDescription("Description1");
		
		Product p2 = new Product();
		p2.setDescription("Description2");
		
		Flux<Product> flux = Flux.just(p, p2);
		flux.filter(t->t.getDescription().contains("2")).subscribe(t->log.info(t.getDescription()));

	}

}
