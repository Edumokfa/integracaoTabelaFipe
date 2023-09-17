package br.com.integracaoFipe.integracaoFipe.dao;

import br.com.integracaoFipe.integracaoFipe.model.Model;
import br.com.integracaoFipe.integracaoFipe.model.ModelYear;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelsYearRepository extends MongoRepository<ModelYear, String> {

}
