package Hope.controller;

import Hope.model.DataHope;
import Hope.model.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HopeService {

    private final DataRepository dataRepository;

    @Autowired
    public HopeService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    public List<DataHope> getAllMainData(){
        return dataRepository.findAll();
    }

    public Optional<DataHope> getData(int id){ return dataRepository.findById(id); }
}
