package com.anil.springboot_multiauth.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityPK implements Serializable {

    @Serial
    private static final long serialVersionUID = -6144939934993746536L;
    private String authority;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "username") } )
    private User user;
}
