package finalproject.startech.services.Impls;

import finalproject.startech.dtos.servicedtos.ServiceDto;
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


    @Override
    public List<ServiceDto> getAllServices() {
        List<ServiceDto> services = serviceRepository.findAll().stream()
                .filter(service -> service.getIsDeleted() == false)
                .map(service -> modelMapper.map(service,ServiceDto.class))
                .collect(Collectors.toList());
        return services;
    }


}
