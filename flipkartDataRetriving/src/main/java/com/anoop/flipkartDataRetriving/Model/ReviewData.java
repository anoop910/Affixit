package com.anoop.flipkartDataRetriving.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull(message = "Some thing wrong fetching the Author Name of Review")
    String author;
    @NotNull(message = "Some thing wrong fetching the text of Review")
    @Lob
    String text;
    @NotNull(message = "Some thing wrong fetching the created date of Review")
    String created;
    List<String> reviewImgLink = new ArrayList<>();
    @NotNull(message = "Some thing wrong fetching the Like of Review")
    int helpfulCount;
    @NotNull(message = "Some thing wrong fetching the total like and dislike of Review")
    int totalCount;
    @NotNull(message = "Some thing wrong fetching the rating of Review")
    int rating;
    @NotNull(message = "Some thing wrong fetching the certified buyer of Review")
    boolean certifiedBuyer;
    String city;
    String state;
    @Override
    public String toString() {
        return "ReviewData [id=" + id + ", author=" + author + ", text=" + text + ", created=" + created
                + ", reviewImgLink=" + reviewImgLink + ", helpfulCount=" + helpfulCount + ", totalCount=" + totalCount
                + ", rating=" + rating + ", certifiedBuyer=" + certifiedBuyer + ", city=" + city + ", state=" + state
                + "]";
    }
    
}
