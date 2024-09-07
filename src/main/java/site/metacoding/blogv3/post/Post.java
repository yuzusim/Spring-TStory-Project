package site.metacoding.blogv3.post;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "post_tb")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String thumbnailFile; //섬네일

    @Transient
    private Boolean isPostOwner;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
