package site.metacoding.blogv3.post;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import site.metacoding.blogv3.category.Category;
import site.metacoding.blogv3.user.User;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@Table(name = "post_tb")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private String thumbnailFile; //썸네일

    @Transient
    private Boolean isPostOwner;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Post(Integer id, String title, String content, User user, Category category, String thumbnailFile, Boolean isPostOwner, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
        this.thumbnailFile = thumbnailFile;
        this.isPostOwner = isPostOwner;
        this.createdAt = createdAt;
    }
}
