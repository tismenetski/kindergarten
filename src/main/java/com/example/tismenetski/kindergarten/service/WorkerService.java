package com.example.tismenetski.kindergarten.service;

import com.example.tismenetski.kindergarten.entities.Worker;

public interface WorkerService {

    Iterable<Worker> getAllWorkers();
    Worker saveOrUpdateWorker(Worker worker);
    Worker findWorkerById(String worker_id);
}
