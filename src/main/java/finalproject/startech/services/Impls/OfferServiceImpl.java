package finalproject.startech.services.Impls;

import finalproject.startech.dtos.offerdtos.OfferCreateDto;
import finalproject.startech.dtos.offerdtos.OfferDto;
import finalproject.startech.dtos.tagdtos.TagCreateDto;
import finalproject.startech.dtos.tagdtos.TagDto;
import finalproject.startech.models.Offer;
import finalproject.startech.models.Tag;
import finalproject.startech.repositories.OfferRepository;
import finalproject.startech.repositories.TagRepository;
import finalproject.startech.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OfferDto> getAllOffers() {
        List<OfferDto> offers = offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
        return offers;
    }

    @Override
    public void createOffer(OfferCreateDto offerCreateDto) {
        Offer offer = modelMapper.map(offerCreateDto,Offer.class);
        offerRepository.save(offer);
    }
}
