package com.srimourya.simpleblog.repo;

import com.srimourya.simpleblog.dto.BlogCardDTO;
import com.srimourya.simpleblog.models.BlogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BlogRepo extends JpaRepository<BlogModel, UUID> {

    @Query("""
                SELECT new com.srimourya.simpleblog.dto.BlogCardDTO
                                (b.blogID
                                 , b.blogTitle
                                 , b.blogSummary
                                 )
                FROM Blogs b order by b.blogTitle asc
    """)
    Page<BlogCardDTO> findAllBlogSummaries(Pageable pageable);

    @Query("""
            SELECT new com.srimourya.simpleblog.dto.BlogCardDTO
                        (b.blogID
                        , b.blogTitle
                        , b.blogSummary
                        )
            FROM Blogs b WHERE b.blogAuthor.userID = :authorID ORDER BY b.blogTitle asc
    """)

    Page<BlogCardDTO> findAllBlogSummariesOfUser(@Param("authorID") UUID authorId, Pageable pageable) ;


}
