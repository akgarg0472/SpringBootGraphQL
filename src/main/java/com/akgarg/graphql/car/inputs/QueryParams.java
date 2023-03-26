package com.akgarg.graphql.car.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Akhilesh Garg
 * @since 26-03-2023
 */
@Getter
@Setter
@ToString
public class QueryParams {

    private String key;
    private String value;

}
