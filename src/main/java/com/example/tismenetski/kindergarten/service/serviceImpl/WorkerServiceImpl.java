package com.example.tismenetski.kindergarten.service.serviceImpl;

import com.example.tismenetski.kindergarten.entities.Worker;
import com.example.tismenetski.kindergarten.exceptions.WorkerNotFoundException;
import com.example.tismenetski.kindergarten.repositories.WorkerRepository;
import com.example.tismenetski.kindergarten.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;


    @Override
    public Worker saveOrUpdateWorker(Worker worker ) {
        return workerRepository.save(worker);
    }

    @Override
    public Iterable<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker findWorkerById(String workerId) {
        Optional<Worker> workerOptional = workerRepository.findById(new Long(workerId));
        if (workerOptional.isEmpty()) throw new WorkerNotFoundException("Student with id: " + workerId + " not found");
        Worker worker  = workerOptional.get();
        return worker;
    }
}
