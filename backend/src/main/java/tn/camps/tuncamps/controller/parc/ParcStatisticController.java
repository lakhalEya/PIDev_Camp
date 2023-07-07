package tn.camps.tuncamps.controller.parc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.camps.tuncamps.service.parc.ParcStatisticService;

@RestController
@RequestMapping("/statistics")
public class ParcStatisticController {

    private final ParcStatisticService parcStatisticService;

    @Autowired
    public ParcStatisticController(ParcStatisticService parcStatisticService) {
        this.parcStatisticService = parcStatisticService;
    }

    @GetMapping("/count/enabled")
    public long getEnabledParcCount() {
        return parcStatisticService.getEnabledParcCount();
    }


    @GetMapping("/count")
    public long getParcCount() {
        return parcStatisticService.getParcCount();
    }


    @GetMapping("/category/most-used")
    public String getMostUsedCategory() {
        return parcStatisticService.getMostUsedCategory();
    }
}
