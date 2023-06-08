package com.example.temspotify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Musica implements Serializable {
    private int id;
    private String titulo;
    private String artista;
    private String album;
    private int estilo;
    private String linkMP3;
}
