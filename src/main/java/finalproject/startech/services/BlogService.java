package finalproject.startech.services;

import finalproject.startech.dtos.blogdtos.*;

import java.util.List;

public interface BlogService{

    List<BlogDto> getBlogs();
    List<BlogHomeDto> getHomeBlogs();
    void createBlog(BlogCreateDto blogCreateDto);
    void removeBlog(Long id);
    void updateBlog(BlogUpdateDto blogUpdateDto);
    BlogUpdateDto findUpdateBlog(Long id);
    BlogDetailDto blogDetail(Long id);
}
