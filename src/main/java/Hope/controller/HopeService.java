package Hope.controller;

import Hope.model.Tool;
import Hope.model.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HopeService {

    private final ToolRepository toolRepository;

    @Autowired
    public HopeService(ToolRepository toolRepository){
        this.toolRepository = toolRepository;
    }

    public List<Tool> getAllMainData(){
        return toolRepository.findAll();
    }

    public Optional<Tool> getData(int id){ return toolRepository.findById(id); }
}
