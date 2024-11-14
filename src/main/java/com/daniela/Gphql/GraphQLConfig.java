package com.daniela.Gphql;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.daniela.Resolver.PlanMutationResolver;
import com.daniela.Resolver.PlanQueryResolver;
import com.daniela.Resolver.UserMutationResolver;
import com.daniela.Resolver.UserQueryResolver;





@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLScalarType doubleScalar() {
        return GraphQLScalarType.newScalar()
            .name("Double")
            .description("A custom scalar that handles double values")
            .coercing(new Coercing<Double, Double>() {
                @Override
                public Double serialize(Object dataFetcherResult) {
                    return (Double) dataFetcherResult;
                }

                @Override
                public Double parseValue(Object input) {
                    return Double.valueOf(input.toString());
                }

                @Override
                public Double parseLiteral(Object input) {
                    if (input instanceof StringValue) {
                        return Double.valueOf(((StringValue) input).getValue());
                    }
                    return null;
                }
            })
            .build();
    }

  
 

    @Bean
    UserMutationResolver userMutationResolver() {
  		return new UserMutationResolver();
  	}

    @Bean
    UserQueryResolver userQueryResolver() {
  		return new UserQueryResolver();
  	}
    
    @Bean
    PlanMutationResolver planMutationResolver() {
  		return new PlanMutationResolver();
  	}

    @Bean
    PlanQueryResolver planQueryResolver() {
  		return new PlanQueryResolver();
  	}
 
    
   
}
