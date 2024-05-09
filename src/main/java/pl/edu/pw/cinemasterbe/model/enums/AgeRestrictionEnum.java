package pl.edu.pw.cinemasterbe.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AgeRestrictionEnum {
    @JsonProperty("g")
    G,
    @JsonProperty("pg")
    PG,
    @JsonProperty("pg-13")
    PG13,
    @JsonProperty("r")
    R,
    @JsonProperty("nc-17")
    NC17
}
