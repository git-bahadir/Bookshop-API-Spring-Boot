package com.bahadir.bookshopAPI.repository.model;

import com.bahadir.bookshopAPI.model.enums.Currency;
import com.bahadir.bookshopAPI.model.enums.Tag;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name="Book")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookEntity {

    @NonNull
    @Id
    @Column(nullable= false)
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NonNull
    @Column(nullable = false)
    private String isbn;

    @NonNull
    @Column(nullable = false)
    private String title;

    @NonNull
    @Column(nullable = false)
    private String author;

    @NonNull
    @Column(nullable = false)
    private String description;

    @NonNull
    @Column(nullable = false)
    private Date datePublished;

    @NonNull
    @Column(nullable = false)
    private String publisher;

    @NonNull
    @Column(nullable = false)
    private Double price;

    @NonNull
    @Column(nullable = false)
    private Currency currency;

    @NonNull
    @Column(nullable = false)
    private Integer stock;

    @NonNull
    @CreatedDate
    @Column(nullable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();

    @NonNull
    @Column(nullable = false)
    @Builder.Default
    @ElementCollection
    private List<Tag> tags = new ArrayList<>();

    @NonNull
    @Column(nullable = false)
    private String imageUrl;

    @NonNull
    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    @NonNull
    @Column(nullable = false)
    private Boolean isActive;
}
