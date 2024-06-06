package finalproject.startech.services.Impls;

import finalproject.startech.dtos.servicedtos.ServiceCreateDto;
import finalproject.startech.dtos.servicedtos.ServiceDto;
import finalproject.startech.dtos.servicedtos.ServiceHomeDto;
import finalproject.startech.dtos.servicedtos.ServiceUpdateDto;
import finalproject.startech.models.Category;
import finalproject.startech.models.Feature;
import finalproject.startech.repositories.CategoryRepository;
import finalproject.startech.repositories.ServiceRepository;
import finalproject.startech.services.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private  ServiceRepository serviceRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<ServiceDto> getAllServices() {
        List<ServiceDto> services = serviceRepository.findAll().stream()
                .filter(service -> service.getIsDeleted() == false)
                .map(service -> modelMapper.map(service,ServiceDto.class))
                .collect(Collectors.toList());
        return services;
    }

    @Override
    public void addService(ServiceCreateDto serviceCreateDto) {
        try {

            Feature feature = new Feature();
            feature.setName(serviceCreateDto.getName());
            feature.setDescription(serviceCreateDto.getDescription());
            feature.setIcon(serviceCreateDto.getIcon());
            Category category = categoryRepository.findById(serviceCreateDto.getId()).get();
            feature.setCategory(category);
            serviceRepository.save(feature);
        }catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeService(Long serviceId) {

    }

    @Override
    public void updateService(ServiceUpdateDto serviceUpdateDto) {

    }

    @Override
    public ServiceUpdateDto findUpdateService(Long id) {
        return null;
    }

    @Override
    public List<ServiceHomeDto> getAllHomeServices() {
        List<ServiceHomeDto> services = serviceRepository.findAll().stream()
                .filter(service -> service.getIsDeleted() == false)
                .map(service -> modelMapper.map(service,ServiceHomeDto.class))
                .collect(Collectors.toList());
        return services;
    }


}
