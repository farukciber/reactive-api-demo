package com.example.reactivedemo;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;
import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactivedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactivedemoApplication.class, args);
    }

}

@RestController
class ReactiveController {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/foo")
    Publisher<Foo> fooPublisher() {
        return Flux.fromStream(Stream.generate(Foo::new))
                .delayElements(Duration.ofSeconds(1));
    }
}

class Foo {
    private String id;
    private String name = "Foo";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Foo() {
        this.id = UUID.randomUUID().toString();
    }
}
