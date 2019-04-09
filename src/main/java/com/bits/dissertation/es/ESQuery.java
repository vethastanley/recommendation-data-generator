package com.bits.dissertation.es;

public class ESQuery {

    public static final String DELETE_ALL = "{\n" +
            "    \"query\" : { \n" +
            "        \"match_all\" : {}\n" +
            "    }\n" +
            "}";
}
