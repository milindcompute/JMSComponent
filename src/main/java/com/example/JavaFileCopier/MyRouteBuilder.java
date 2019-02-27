package com.example.JavaFileCopier;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:F:/data/Splitter1/input?noop=true")
		.split(body().tokenize(",")).streaming()
		.to("jms:topic:testing");
		from("jms:topic:testing").to("direct:one");
		from("jms:topic:testing").to("direct:two");
		from("direct:one").to("stream:out");
		from("direct:two").to("stream:out");
	}
}
