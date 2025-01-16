package Hope.controller.tool;

import Hope.exceptions.ResourceNotFoundException;
import Hope.model.Tool;
import Hope.model.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository){
        this.toolRepository = toolRepository;
    }

    public List<Tool> getAllMainTool(){
        return toolRepository.findAll();
    }

    public Tool getTool(int id) {
        return toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tool", "id", id));
    }
    public void deleteTool(int id){ toolRepository.deleteById(id); }

    public void updateTool(Tool data){
        if (data.getDescriptionSimple().isEmpty()) {data.setDescriptionSimple(null);}
        if (data.getDescriptionDetaillee().isEmpty()) {data.setDescriptionDetaillee(null);}
        if (data.getAcces().isEmpty()) {data.setAcces(null);}
        toolRepository.save(data); }

    public void addTool(Tool data){ toolRepository.save(data); }
}
