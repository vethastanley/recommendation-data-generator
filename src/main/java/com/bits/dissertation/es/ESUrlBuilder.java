package com.bits.dissertation.es;

import static com.bits.dissertation.data.Configuration.*;

public class ESUrlBuilder {

    public static final String USER_PROFILE_EVENT_URL = "http://" + ES_HOST + ":" + ES_PORT + "/" + ES_AUDIT_INDEX + "/" + ES_EVENTS_TYPE;

    public static final String ACTION_WEIGHTAGE_URL = "http://" + ES_HOST + ":" + ES_PORT + "/" + ES_ANALYTICS_INDEX + "/" + ES_WEIGHT_TYPE;

    public static final String API_DEFINITION_URL = "http://" + ES_HOST + ":" + ES_PORT + "/" + ES_ANALYTICS_INDEX + "/" + ES_DEFINITION_TYPE;

    public static final String DELETE_WITH_QUERY = "/_delete_by_query";
}
