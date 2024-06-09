package car.database.service;

import car.database.entity.EmailTemplate;
import car.database.repository.EntityRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService extends AbstractEntityService<EmailTemplate> {

    public EmailTemplateService(EntityRepository<EmailTemplate> repository) {
        super(repository);
    }

    @Override
    protected Class<EmailTemplate> getEntityClass() {
        return EmailTemplate.class;
    }
}
