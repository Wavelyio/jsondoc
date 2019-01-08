package com.axonmobileiot.jsondoc.core.pojo;

public enum ApiVisibility {
    UNDEFINED,

    /**
     * Indicates that the API is not for public consumption.
     */
    PRIVATE,

    /**
     * Indicates that the API is freely available (general authentication / authorization schemes apply).
     */
    PUBLIC,

    /**
     * Indicates that the API requires special privilege to access.
     */
    RESTRICTED
}