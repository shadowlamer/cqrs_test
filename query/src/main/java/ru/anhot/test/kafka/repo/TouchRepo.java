package ru.anhot.test.kafka.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.anhot.test.kafka.domain.TouchSummary;

import java.util.List;
import java.util.UUID;

public interface TouchRepo extends PagingAndSortingRepository<TouchSummary, UUID> {
}
