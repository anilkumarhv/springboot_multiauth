package com.anil.springboot_multiauth.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable {

    @Serial
    private static final long serialVersionUID = -3586847457462233109L;
    @EmbeddedId
    AuthorityPK id;
}
