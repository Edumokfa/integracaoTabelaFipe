package br.com.integracaoFipe.integracaoFipe.service;

import br.com.integracaoFipe.integracaoFipe.dao.ModelsRepository;
import br.com.integracaoFipe.integracaoFipe.dao.ModelsYearRepository;
import br.com.integracaoFipe.integracaoFipe.dao.VehicleRepository;
import br.com.integracaoFipe.integracaoFipe.model.Model;
import br.com.integracaoFipe.integracaoFipe.model.ModelYear;
import br.com.integracaoFipe.integracaoFipe.model.Vehicle;
import br.com.integracaoFipe.integracaoFipe.utils.BaseApiCommunication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ModelService extends BaseApiCommunication {

    private final ModelsRepository modelsRepository;
    private final ModelsYearRepository modelsYearRepository;
    private final VehicleRepository vehicleRepository;

    public ModelService(ModelsRepository modelsRepository, ModelsYearRepository modelsYearRepository, VehicleRepository vehicleRepository) {
        this.modelsRepository = modelsRepository;
        this.modelsYearRepository = modelsYearRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseEntity<List<Model>> getModelsFromApi(Integer brandId) {
        ParameterizedTypeReference responseModelType = new ParameterizedTypeReference<Map<String, List<Model>>>() {};
        Map<String, List<Model>> modelResponseBody = getMapListDataFromUrl(getModelsApiUrl(brandId), responseModelType);
        if (modelResponseBody != null && modelResponseBody.containsKey("modelos")) {
            List<Model> modelsList = modelResponseBody.get("modelos");
            modelsList.forEach(m -> {
                m.setBrandId(brandId);
                List<ModelYear> modelYears = getModelYearsFromBrand(brandId, m.get_id());
                modelsYearRepository.saveAll(modelYears);
                m.setModelYears(modelYears);
            });
            modelsRepository.saveAll(modelsList);
            return ResponseEntity.ok(modelsList);
        }
        return ResponseEntity.noContent().build();
    }

    private List<ModelYear> getModelYearsFromBrand(Integer brandId, String modelId) {
        ParameterizedTypeReference responseModelYearType = new ParameterizedTypeReference<List<ModelYear>>() {};
        List<ModelYear> modelYears = getListDataFromUrl(getModelsApiUrl(brandId) + "/" + modelId + "/anos", responseModelYearType);
        modelYears.forEach(my -> {
            Vehicle vehicle = (Vehicle) getDataFromUrl(getModelsApiUrl(brandId) + "/" + modelId + "/anos/" + my.get_id(), Vehicle.class);
            vehicle.set_id(my.get_id());
            vehicleRepository.save(vehicle);
            my.setVehicle(vehicle);
        });
        return modelYears;
    }

    public ResponseEntity<List<Model>> getFilteredModelsFromApi(Integer brandId, Integer startYear, Integer endYear, Double minValue, Double maxValue, Character gasType, String vehicleType) {
        return ResponseEntity.ok(modelsRepository.findAll());
    }

    private String getModelsApiUrl(Integer brandId) {
        return "/carros/marcas/" + brandId + "/modelos";
    }
}