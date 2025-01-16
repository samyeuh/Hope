package Hope.controller.tool;

import Hope.exceptions.ResourceNotFoundException;
import Hope.model.Tool;
import Hope.model.ToolRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository){
        this.toolRepository = toolRepository;
    }

    public List<Tool> getAllMainTool(){
        return toolRepository.findAllByVisible(true).orElse(null);
    }

    public List<Tool> getProposalTool(){
        return toolRepository.findAllByVisible(false).orElse(null);
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
