package com.example.coursework.service.dataServices;

import com.example.coursework.dto.*;
import com.example.coursework.entity.Realty;
import com.example.coursework.repository.RealtyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RealtyService {

    private final RealtyRepository realtyRepository;
    private final ModelMapper modelMapper;

    public List<Realty> findAllOpen() {

        return realtyRepository.findByAdvertisements_Open();
    }

    public List<RealtyMAINTODO> findRealtyDTOByOpenTrue() {

        return realtyRepository.findRealtyMAINTODOByOpenTrueOrderByAdvertisementDesc();
    }

    public Realty findById(Integer realtyId) {

        return realtyRepository.findById(realtyId).orElse(null);
    }

    public Realty findByIdAndAdvertisementsOpenTrue(Integer realtyId) {

        return realtyRepository.findByIdAndAdvertisements_OpenTrue(realtyId).orElse(null);
    }

    public List<Realty> findByUserEmail(String userEmail) {

        return realtyRepository.findByAdvertisement_UserEmail_Email(userEmail);
    }

    public Realty oldFiledOfObjectToNewField(Realty fromRealty, Realty toRealty) {

        fromRealty.setPurpose(toRealty.getPurpose());
        fromRealty.setPrice(toRealty.getPrice());
        fromRealty.setHeader(toRealty.getHeader());
        fromRealty.setNumberOfRooms(toRealty.getNumberOfRooms());
        fromRealty.setHousingArea(toRealty.getHousingArea());
        fromRealty.setRepair(toRealty.getRepair());
        fromRealty.setFurnishing(toRealty.getFurnishing());
        //fromRealty.getFlat();

        // TODO: 14.07.2023 доделать

        return fromRealty;
    }

    public void deleteById(Integer realtyId) {

        realtyRepository.deleteById(realtyId);
    }

    public int save(RealtyRequestDTO realty, int addressId) {

           // return realtyRepository.save(new Realty()).getId();
                return realtyRepository.saveRealtyRequest(realty,addressId).getId();
    }

    public List<Realty> findById(List<Integer> bookmarkItems) {

  //      return realtyRepository.findAllByIdAndAdvertisement_Open(bookmarkItems);
        return realtyRepository.findAllById(bookmarkItems);
    }

    public List<Realty> findByFilterOrderByPublicationDateAndTime(HouseFilterDto houseFilter) {
        return realtyRepository.findByFilterOrderByPublicationDateAndTime(houseFilter.getFirstPrice(), houseFilter.getLastPrice(), houseFilter.getFirstHouseArea(), houseFilter.getLastHouseArea(),
                houseFilter.getFirstNumberRooms(), houseFilter.getLastNumberRooms(), houseFilter.getFirstFloors(), houseFilter.getLastFloors(), houseFilter.getFirstLandArea(),
                houseFilter.getLastLandArea(),houseFilter.getRegionTitle(),houseFilter.getLocalityId(),houseFilter.getDistrictId(),houseFilter.getProperty(),houseFilter.getCurrency());
    }

    public List<Realty> findByFilterOrderByPublicationDateAndTime(FlatFilterDto flatFilter) {

        return realtyRepository.findByFilterOrderByPublicationDateAndTime(flatFilter.getFirstPrice(), flatFilter.getLastPrice(), flatFilter.getFirstHouseArea(), flatFilter.getLastHouseArea(),
                flatFilter.getFirstNumberRooms(), flatFilter.getLastNumberRooms(), flatFilter.getFirstFloors(), flatFilter.getLastFloors(),
                flatFilter.getRegionTitle(),flatFilter.getLocalityId(),flatFilter.getDistrictId(),flatFilter.getProperty(),flatFilter.getCurrency());
    }

    public List<Realty> findByUserEmailOrderById(String userEmail) {
        return realtyRepository.findByAdvertisement_UserEmail_EmailOrderById(userEmail);
    }

    public void update(RealtyUpdateDTO realty) {

        realtyRepository.update(realty);
    }

    public List<RealtyMAINTODO> toRealtyMAINTODO(List<Realty> realties) {

        TypeMap<Realty, RealtyMAINTODO> propertyMapper = modelMapper.getTypeMap(Realty.class, RealtyMAINTODO.class);

        if (propertyMapper == null) { // if not  already added

            propertyMapper = modelMapper.createTypeMap(Realty.class, RealtyMAINTODO.class);

            propertyMapper.addMappings(
                    mapper -> {
                        mapper.map(Realty::getAddress, RealtyMAINTODO::setAddress);
                        mapper.map(Realty::getPrice,RealtyMAINTODO::setPriceInFirstCurrency);
                        mapper.map(Realty::getCurrency,RealtyMAINTODO::setFirstCurrency);
                    }
            );
        }

        /*propertyMapper.addMappings(
                mapper -> {
                    mapper.map(src -> src.getAddress().getRegion().getTitle(), RealtyMAINTODO::setRegionTitle);
                    mapper.map(src -> src.getAddress().getLocality().getTitle(), RealtyMAINTODO::setLocalityTitle);
                    mapper.map(src -> src.getAddress().getDistrict().getTitle(), RealtyMAINTODO::setDistrictTitle);
                }
        );*/

        /*propertyMapper.addMappings(
                mapper -> {
                    mapper.map(Realty::getAddress, RealtyMAINTODO::setAddress);
                }
        );*/

       // mapper.map(src -> src.getAddress().getLocality().getTitle(),RealtyMAINTODO::setLocalityTitle)
        List<RealtyMAINTODO> result = realties.stream().map(x -> modelMapper.map(x, RealtyMAINTODO.class)).toList();
        result.forEach(System.out::println);
        return result;
    }

    public RealtyMAINTODO findRealtyDTOByIdAndOpenTrue(int bookmarkItem) {

        return realtyRepository.findRealtyMainTODOByIdAndAdvertisement_Open(bookmarkItem);
      /*  List<Realty> realties = findById(bookmarkItems);

        return realties.stream().filter(x -> x.getAdvertisement().getOpen()).map(x -> modelMapper.map(x, RealtyMAINTODO.class)).toList();*/
    }

    public List<RealtyMAINTODO> findRealtyDTOByIdAndOpenTrue(List<Integer> bookmarkItems) {

        List<RealtyMAINTODO> result = new ArrayList<>();

        for (Integer bookmarkItem : bookmarkItems) {
            result.add(findRealtyDTOByIdAndOpenTrue(bookmarkItem));
        }

        return result;
      /*  List<Realty> realties = findById(bookmarkItems);

        return realties.stream().filter(x -> x.getAdvertisement().getOpen()).map(x -> modelMapper.map(x, RealtyMAINTODO.class)).toList();*/
    }
}
