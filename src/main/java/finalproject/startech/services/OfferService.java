package finalproject.startech.services;

import finalproject.startech.dtos.offerdtos.OfferCreateDto;
import finalproject.startech.dtos.offerdtos.OfferDto;

import java.util.List;

public interface OfferService {
    List<OfferDto> getAllOffers();
    void createOffer(OfferCreateDto offerCreateDto);
}
