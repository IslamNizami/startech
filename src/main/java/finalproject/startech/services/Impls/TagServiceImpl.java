package finalproject.startech.services.Impls;

import finalproject.startech.dtos.tagdtos.TagCreateDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import finalproject.startech.models.Tag;
import finalproject.startech.repositories.TagRepository;
import finalproject.startech.services.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TagDto> getAllTags() {
        List<TagDto> tags = tagRepository.findAll().stream().map(tag -> modelMapper.map(tag,TagDto.class)).collect(Collectors.toList());
        return tags;
    }

    @Override
    public void createTag(TagCreateDto tagCreateDto) {
        Tag tag = modelMapper.map(tagCreateDto,Tag.class);
        tagRepository.save(tag);
    }
}
