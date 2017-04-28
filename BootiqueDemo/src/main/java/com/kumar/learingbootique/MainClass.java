package com.kumar.learingbootique;

import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.configuration.server.ServerRuntimeBuilder;
import org.apache.cayenne.query.ObjectSelect;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.kumar.learingbootique.model.cayenne.persistent.Constants;

import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;

/**
 * @author Saravana Kumar M
 *
 */
public class MainClass implements Module
{
	public static void main(final String[] args)
	{
		//Bootique.app(args).run();
		//Bootique.app(args).module(MainClass.class).run();
		Bootique.app(args).module(MainClass.class).autoLoadModules().run();
	}

	@Override
	public void configure(final Binder binder)
	{
		//JerseyModule.contributeResources(binder).addBinding().to(API.class);
		JerseyModule.extend(binder).addResource(API.class);
	}

	@Path("/")
	public static class API
	{
		//@Inject
		//ServerRuntime serverRuntime;
		private final ServerRuntime serverRuntime = ServerRuntimeBuilder.builder().addConfig("cayenne/cayenne-BootiqueDemo.xml").build();

		@Context
		Configuration configuration;

		@GET
		@Produces(MediaType.TEXT_PLAIN)
		//@Produces(MediaType.APPLICATION_JSON)
		public String get()
		//public DataResponse<Constants> get(@Context final UriInfo uri)
		{
			//return "Hello Bootique...";
			return ObjectSelect.query(Constants.class).select(this.serverRuntime.newContext()).stream().map(Constants::getAttributeName).collect(Collectors.joining("\n"));
			//return LinkRest.select(Constants.class, this.configuration).uri(uri).select();
		}
	}
}