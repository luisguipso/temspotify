package com.example.temspotify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private List<PlayList> playLists;

    public String toString(){
        return id + " - " + nome + " - " + email;
    }
}
