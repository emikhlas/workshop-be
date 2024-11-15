package ogya.workshop.performance_appraisal.controller;

import ogya.workshop.performance_appraisal.dto.ManagerDto;
import ogya.workshop.performance_appraisal.dto.techskill.TechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.techskill.TechSkillDto;
import ogya.workshop.performance_appraisal.service.TechSkillServ;
import ogya.workshop.performance_appraisal.util.ServerResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tech-skill")
public class TechSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(TechSkillController.class);

    @Autowired
    private TechSkillServ techSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<TechSkillDto>>> getAll() {
        Log.info("Start getAll in TechSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<TechSkillDto>> response = new ManagerDto<>();
        List<TechSkillDto> content = techSkillServ.findAll();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success get data", endTime - startTime));
        Log.info("End getAll in TechSkillController, time: " + (endTime - startTime));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ManagerDto<TechSkillDto>> save(@RequestBody TechSkillCreateDto techSkillDto) {
        Log.info("Start save in TechSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<TechSkillDto> response = new ManagerDto<>();
        TechSkillDto content = techSkillServ.save(techSkillDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success save data", endTime - startTime));
        Log.info("End save in TechSkillController, time: " + (endTime - startTime));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<TechSkillDto>> update(@PathVariable("id") UUID id, @RequestBody TechSkillCreateDto techSkillDto) {
        Log.info("Start update in TechSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<TechSkillDto> response = new ManagerDto<>();
        TechSkillDto content = techSkillServ.update(id, techSkillDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success update data", endTime - startTime));
        Log.info("End update in TechSkillController, time: " + (endTime - startTime));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> delete(@PathVariable("id") UUID id) {
        Log.info("Start delete in TechSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = techSkillServ.deleteById(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success delete data", endTime - startTime));
        Log.info("End delete in TechSkillController, time: " + (endTime - startTime));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<TechSkillDto>> getDetail(@PathVariable("id") UUID id) {
        Log.info("Start getDetail in TechSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<TechSkillDto> response = new ManagerDto<>();
        TechSkillDto content = techSkillServ.findById(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        response.setInfo(getInfoOk("Success get data", endTime - startTime));
        Log.info("End getDetail in TechSkillController, time: " + (endTime - startTime));
        return ResponseEntity.ok(response);
    }
}
