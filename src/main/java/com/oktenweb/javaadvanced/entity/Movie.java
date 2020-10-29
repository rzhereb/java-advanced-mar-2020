package com.oktenweb.javaadvanced.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oktenweb.javaadvanced.validator.UniqueMovieTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 270)
    @NotBlank
    private String title;
    @Positive
    @Max(200)
    private int duration;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Director director;

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", duration=" + duration +
            '}';
    }
}
