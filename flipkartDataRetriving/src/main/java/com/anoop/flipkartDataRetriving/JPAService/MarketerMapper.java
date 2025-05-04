package com.anoop.flipkartDataRetriving.JPAService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.DTO.MarketerDTO;
import com.anoop.flipkartDataRetriving.Model.Marketer;
import com.anoop.flipkartDataRetriving.Repository.MarketerRepo;

@Service
public class MarketerMapper {

    private MarketerRepo marketerRepo;

    public MarketerMapper(MarketerRepo marketerRepo) {
        this.marketerRepo = marketerRepo;

    }

    public List<MarketerDTO> getAllMarketer() {
        List<Marketer> marketer = marketerRepo.findAll();

        List<MarketerDTO> marketerDTOS = new ArrayList<>();

        marketer.stream().forEach(marketers -> {
            MarketerDTO marketerDTO = new MarketerDTO();
            marketerDTO.setId(marketers.getId());
            marketerDTO.setUserName(marketers.getUserName());
            marketerDTO.setEmail(marketers.getEmail());
            marketerDTO.setPhone(marketers.getPhone());
            marketerDTO.setPassword(marketers.getPassword());
            marketerDTO.setCountry(marketers.getCountry());
            marketerDTO.setCreateProduct(marketers.getCreateProduct());
            marketerDTOS.add(marketerDTO);
        });
        return marketerDTOS;

    }

    public MarketerDTO saveMarketer(MarketerDTO marketerDTO){
         Marketer marketer = new Marketer();

         marketer.setUserName(marketerDTO.getUserName());
         marketer.setEmail(marketerDTO.getEmail());
         marketer.setPhone(marketerDTO.getPhone());
         marketer.setPassword(marketerDTO.getPassword());
         marketer.setCountry(marketerDTO.getCountry());
         marketerRepo.save(marketer);
         return marketerDTO;
    }

}
