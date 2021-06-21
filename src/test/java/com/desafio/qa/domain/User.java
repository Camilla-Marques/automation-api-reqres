package com.desafio.qa.domain;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonAlias("first_name")
    private String name;
    private String job;
    private String email;
    private String password;
    @JsonAlias("last_name")
    private String lastname;
}
