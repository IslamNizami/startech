package finalproject.startech.services.Impls;

import finalproject.startech.Helpers.SeoHelper;
import finalproject.startech.dtos.blogdtos.*;
import finalproject.startech.dtos.servicedtos.ServiceDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import finalproject.startech.models.Blog;
import finalproject.startech.models.Category;
import finalproject.startech.models.Tag;
import finalproject.startech.repositories.BlogRepository;
import finalproject.startech.repositories.CategoryRepository;
import finalproject.startech.repositories.TagRepository;
import finalproject.startech.services.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<BlogDto> getBlogs() {
        List<BlogDto> blogs = blogRepository.findAll().stream()
                .filter(blog -> blog.getIsDeleted() == false)
                .map(blog -> modelMapper.map(blog,BlogDto.class))
                .collect(Collectors.toList());
        return blogs;
    }

    @Override
    public List<BlogHomeDto> getHomeBlogs() {
        List<BlogHomeDto> blogs = blogRepository.findAll().stream()
                .filter(blog -> blog.getIsDeleted() == false)
                .map(blog -> modelMapper.map(blog,BlogHomeDto.class))
                .collect(Collectors.toList());
        return blogs;
    }

    @Override
    public void createBlog(BlogCreateDto blogCreateDto) {
        try {
            Blog blog = new Blog();
            blog.setUpdatedDate(new Date());
            blog.setCreatedDate(new Date());
            blog.setTitle(blogCreateDto.getTitle());
            blog.setDescription(blogCreateDto.getDescription());
            blog.setAuthor(blogCreateDto.getAuthor());
            blog.setSubTitle(blogCreateDto.getSubTitle());
            blog.setPhotoUrl(blogCreateDto.getPhotoUrl());
            Category category = categoryRepository.findById(blogCreateDto.getCategoryId()).get();
            blog.setCategory(category);
            SeoHelper seoHelper = new SeoHelper();
            blog.setSeoUrl(seoHelper.seoUrlHelper(blogCreateDto.getTitle()));
            Set<Long> tagIds = blogCreateDto.getTagIds();
            List<Tag> tags = tagRepository.findAllById(tagIds);
            blog.setTag(new HashSet<>(tags));
            blogRepository.saveAndFlush(blog);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow();
        blog.setIsDeleted(true);
        blogRepository.save(blog);
    }

    @Override
    public void updateBlog(BlogUpdateDto blogUpdateDto) {
        Blog findBlog = blogRepository.findById(blogUpdateDto.getId()).orElseThrow();
        Category category = categoryRepository.findById(blogUpdateDto.getCategoryId()).orElseThrow();

        findBlog.setTitle(blogUpdateDto.getTitle());
        findBlog.setDescription(blogUpdateDto.getDescription());
        findBlog.setAuthor(blogUpdateDto.getAuthor());
        findBlog.setUpdatedDate(new Date());
        findBlog.setSubTitle(blogUpdateDto.getSubTitle());
        findBlog.setPhotoUrl(blogUpdateDto.getPhotoUrl());
        findBlog.setCategory(category);
        SeoHelper seoHelper = new SeoHelper();
        findBlog.setSeoUrl(seoHelper.seoUrlHelper(blogUpdateDto.getTitle()));
        blogRepository.saveAndFlush(findBlog);
    }

    @Override
    public BlogUpdateDto findUpdateBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow();
        BlogUpdateDto blogUpdateDto = modelMapper.map(blog,BlogUpdateDto.class);
        return blogUpdateDto;
    }

    @Override
    public BlogDetailDto blogDetail(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow();
        BlogDetailDto blogDetailDto = modelMapper.map(blog,BlogDetailDto.class);
        return blogDetailDto;
    }

    @Override
    public List<BlogHomeDto> findByTitle(String name) {
        List<Blog> blogs = blogRepository.findByTitle(name);
        List<BlogHomeDto> result = blogs.stream().map(blog->modelMapper.map(blog,BlogHomeDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<BlogHomeDto> getLatestBlogs() {
        return blogRepository.findLatest3Blogs().subList(0, 3).stream()
                .filter(blog->blog.getIsDeleted() == false)
                .map(blog->modelMapper.map(blog,BlogHomeDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<BlogRecentDto> getLatest5Blogs() {
        List<BlogRecentDto> blogs = blogRepository.findLatest5Blogs().stream()
                .filter(blog -> blog.getIsDeleted() == false)
                .map(blog -> modelMapper.map(blog,BlogRecentDto.class))
                .collect(Collectors.toList());
        return blogs;
    }

}
