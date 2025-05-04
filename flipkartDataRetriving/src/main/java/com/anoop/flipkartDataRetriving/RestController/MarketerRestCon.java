package com.anoop.flipkartDataRetriving.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anoop.flipkartDataRetriving.DTO.MarketerDTO;
import com.anoop.flipkartDataRetriving.JPAService.MarketerMapper;

@RestController
@RequestMapping("/marketer")
public class MarketerRestCon {
    

    private MarketerMapper marketerMapper;

    public MarketerRestCon(MarketerMapper marketerMapper){
        this.marketerMapper = marketerMapper;
    }

    @GetMapping
    public List<MarketerDTO> getAllMarketers(){
        return marketerMapper.getAllMarketer();
    }

    @PostMapping
    public MarketerDTO createMarketer(@RequestBody MarketerDTO marketerDTO){
        return marketerMapper.saveMarketer(marketerDTO);
    }

}
