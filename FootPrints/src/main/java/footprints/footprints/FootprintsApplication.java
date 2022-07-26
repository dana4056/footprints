package footprints.footprints;

import footprints.footprints.domain.notice.Notice;
import footprints.footprints.repository.notice.NoticeRepository;
import footprints.footprints.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@EnableJpaAuditing
@SpringBootApplication
public class FootprintsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FootprintsApplication.class, args);
	}
}
