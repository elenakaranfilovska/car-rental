package mk.ukim.finki.emt.lab.carrental.sharedkernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link ProcessedRemoteEvent}.
 */
interface ProcessedRemoteEventRepository extends JpaRepository<ProcessedRemoteEvent, String> {
}
