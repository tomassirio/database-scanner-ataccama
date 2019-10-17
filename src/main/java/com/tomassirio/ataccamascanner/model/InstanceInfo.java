package com.tomassirio.ataccamascanner.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "instance_info")
public class InstanceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "instance_info")
    @TableGenerator(name="instance_info", table="ataccama_scanner_sequence", initialValue = 100, allocationSize = 1)
    private Long id;
    @NonNull
    @Column(name="instance_name")
    private String instanceName;
    @NonNull
    @Column(name="host")
    private String host;
    @NonNull
    @Column(name="port")
    private String port;
    @Column(name="user")
    private String user;
    @Column(name="password")
    private String password;
    @Column(name="data_base")
    private String database;
}