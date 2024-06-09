package finalproject.startech.services.Impls;

import finalproject.startech.dtos.packagedtos.PackageCreateDto;
import finalproject.startech.dtos.packagedtos.PackageDto;
import finalproject.startech.dtos.packagedtos.PackageHomeDto;
import finalproject.startech.dtos.packagedtos.PackageUpdateDto;
import finalproject.startech.models.Package;
import finalproject.startech.repositories.PackageRepository;
import finalproject.startech.services.PackageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PackageDto> getAllPackages() {
        List<PackageDto> pakets = packageRepository.findAll().stream()
                .filter(paket -> paket.getIsDeleted() == false)
                .map(paket -> modelMapper.map(paket, PackageDto.class))
                .collect(Collectors.toList());
        return pakets;
    }

    @Override
    public void createPackage(PackageCreateDto packageCreateDto) {
        try {
            Package paket = new Package();
            paket.setTitle(packageCreateDto.getTitle());
            paket.setSubTitle(packageCreateDto.getSubTitle());
            paket.setPrice(packageCreateDto.getPrice());
            paket.setFeatures(packageCreateDto.getFeatures());
            packageRepository.save(paket);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<PackageHomeDto> getHomePackages() {
        List<PackageHomeDto>  pakets = packageRepository.findAll().stream()
                .filter(paket -> paket.getIsDeleted() == false)
                .map(paket->modelMapper.map(paket, PackageHomeDto.class))
                .collect(Collectors.toList());
        return pakets;
    }

    @Override
    public void removePackage(Long id) {
        Package paket = packageRepository.findById(id).orElseThrow();
        paket.setIsDeleted(true);
        packageRepository.save(paket);
    }

    @Override
    public void updatePackage(PackageUpdateDto packageUpdateDto) {
        Package findPaket = packageRepository.findById(packageUpdateDto.getId()).orElseThrow();
        findPaket.setTitle(packageUpdateDto.getTitle());
        findPaket.setSubTitle(packageUpdateDto.getSubTitle());
        findPaket.setPrice(packageUpdateDto.getPrice());
        findPaket.setFeatures(packageUpdateDto.getFeatures());
        packageRepository.saveAndFlush(findPaket);
    }

    @Override
    public PackageUpdateDto findUpdatePackage(Long id) {
        Package paket = packageRepository.findById(id).orElseThrow();
        PackageUpdateDto packageUpdateDto = modelMapper.map(paket, PackageUpdateDto.class);
        return packageUpdateDto;
    }
}
