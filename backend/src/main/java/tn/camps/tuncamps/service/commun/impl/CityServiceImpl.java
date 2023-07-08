package tn.camps.tuncamps.service.commun.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.camps.tuncamps.persistence.entity.commun.City;
import tn.camps.tuncamps.persistence.entity.commun.Location;
import tn.camps.tuncamps.persistence.repository.commun.CityRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements ApplicationRunner {


    private final CityRepository cityRepository;


    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Autowired
    public CityServiceImpl (CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public void loadCityDataFromFile() throws IOException {
        System.out.println("loadCityDataFromFile");
//        Path filePath = Paths.get("src/main/resources/cities.txt");
//        List<String> lines = Files.readAllLines(filePath);
//
//        cityRepository.deleteAll();
//        cityRepository.resetIdValue();
//
//        for (String line : lines) {
//            String[] data = line.split(",");
//            String country = data[0].trim();
//            String cityName = data[1].trim();
//            String postalCode = data[2].trim();
//
//            City city = new City(cityName, country, postalCode);
//            cityRepository.save(city);
//        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadCityDataFromFile();
    }


}
