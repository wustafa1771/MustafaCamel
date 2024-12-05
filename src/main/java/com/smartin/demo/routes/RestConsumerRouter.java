package com.smartin.demo.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestConsumerRouter extends RouteBuilder {
	/*
	 * @Override public void configure() throws Exception {
	 * from("timer:test-rest-api?period=4000").log("rest api called").to(
	 * "http://localhost:8080/api/inventory") .process(new Processor() {
	 * 
	 * @Override public void process(Exchange exchange) throws Exception { String
	 * output =exchange.getIn().getBody(String.class);
	 * System.out.println("output is ---"+ output); } }); }
	 */

	@Autowired
	InventoryService invService;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

		rest().path("/inv")

				.get().to("direct:getInventories")

				.post()
					.type(Inventory.class)
					.outType(InventoryResponse.class)
					.to("direct:postInventory")
				.get("/hello")
					.produces("text/plain")
					.to("language:constant:Hello world")
				.delete().to("direct:deleteInventories")

		;

		from("direct:getInventories")
				// Camel will marshal to JSON automatically!
				.bean(invService, "getAllServices");
		
		from("direct:deleteInventories").to("log:mylogger?showAll=true").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String deneme = exchange.toString();
				long idForDelete = exchange.getIn().getHeader("idfordelete", Long.class);
				invService.deleteInventory(idForDelete);

				InventoryResponse response = new InventoryResponse();
				response.setMessage("Inventory with following id is deleted " + idForDelete);

				// Pop the response back in the body
				// Camel will marshal to JSON automatically!
				exchange.getMessage().setBody(response);

			}
		});

		from("direct:postInventory").to("log:mylogger?showAll=true")

				// An inline processor to generate the response
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						Inventory inv = exchange.getMessage().getBody(Inventory.class);

						invService.saveInventory(inv);

						InventoryResponse response = new InventoryResponse();
						response.setMessage("Thanks for submitting " + inv.getinventory_name());

						// Pop the response back in the body
						// Camel will marshal to JSON automatically!
						exchange.getMessage().setBody(response);
					}
				});
	}
}
