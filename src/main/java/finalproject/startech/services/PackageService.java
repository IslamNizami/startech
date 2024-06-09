package finalproject.startech.services;

import finalproject.startech.dtos.blogdtos.BlogDto;
import finalproject.startech.dtos.packagedtos.PackageCreateDto;
import finalproject.startech.dtos.packagedtos.PackageDto;
import finalproject.startech.dtos.packagedtos.PackageHomeDto;
import finalproject.startech.dtos.packagedtos.PackageUpdateDto;
import finalproject.startech.models.Package;

import java.util.List;

public interface PackageService {

   List<PackageDto> getAllPackages();
   void createPackage(PackageCreateDto packageCreateDto);
   List<PackageHomeDto> getHomePackages();
   void removePackage(Long id);
   void updatePackage(PackageUpdateDto packageUpdateDto);
   PackageUpdateDto findUpdatePackage(Long id);
}
