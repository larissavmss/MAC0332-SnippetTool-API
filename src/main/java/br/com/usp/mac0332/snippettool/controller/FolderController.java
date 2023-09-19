package br.com.usp.mac0332.snippettool.controller;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("folder")
public class FolderController {
    @Autowired
    FolderService folderService;
    @PostMapping("/add")
    public void addFolder(@RequestBody Folder folder){
        folderService.addFolder(folder);
    }

    @GetMapping("/findByName")
    public Folder findByName(@RequestParam("name") String name){
        return folderService.findByName(name);
    }
}
