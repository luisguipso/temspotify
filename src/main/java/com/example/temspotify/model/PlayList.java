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
public class PlayList implements Serializable {
    private int id;
    private String titulo;
    private Usuario usuario;
    private List<Musica> musicas;
}
