package com.tomassirio.ataccamascanner.model.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder
public class InstanceInfoDTO {

    @NonNull
    private Long id;
    private String instanceName;
    private String host;
    private String port;
    private String user;
    private String password;
    private String database;
}
