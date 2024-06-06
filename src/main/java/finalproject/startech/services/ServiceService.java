package finalproject.startech.services;

import finalproject.startech.dtos.servicedtos.ServiceCreateDto;
import finalproject.startech.dtos.servicedtos.ServiceDto;
import finalproject.startech.dtos.servicedtos.ServiceHomeDto;
import finalproject.startech.dtos.servicedtos.ServiceUpdateDto;

import java.util.List;

public interface ServiceService {

    public List<ServiceDto> getAllServices();
    void addService(ServiceCreateDto serviceCreateDto);
    void removeService(Long serviceId);
    void updateService(ServiceUpdateDto serviceUpdateDto);
    ServiceUpdateDto findUpdateService(Long id);
    public List<ServiceHomeDto> getAllHomeServices();
}
