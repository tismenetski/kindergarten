package com.example.tismenetski.kindergarten.controllers;

import com.example.tismenetski.kindergarten.entities.Worker;
import com.example.tismenetski.kindergarten.service.MapValidationErrorService;
import com.example.tismenetski.kindergarten.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worker")
@CrossOrigin // Enables access from react at port 3000
public class WorkerController {


    @Autowired
    private WorkerService workerService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    //Get All Workers
    @GetMapping("")
    public Iterable<Worker> getAllWorkers()
    {
        return workerService.getAllWorkers();
    }

    //Create a new Worker
    @PostMapping("")
    public ResponseEntity<?> createNewWorker(@Valid @RequestBody Worker worker  , BindingResult result) //BindingResult is an interface that analyzes an object
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap; // Returns the error map with a BAD_REQUEST Status

        Worker returnValue = workerService.saveOrUpdateWorker(worker);

        return new ResponseEntity<Worker>(returnValue, HttpStatus.OK);
    }

    //Find Worker by id
    @GetMapping("/{worker_id}")
    public ResponseEntity<?> getWorkerById(@PathVariable String worker_id)
    {
        Worker worker = workerService.findWorkerById(worker_id);
        return new ResponseEntity<Worker>(worker,HttpStatus.OK);
    }
}
