package br.com.integracaoFipe.integracaoFipe.controller;

import br.com.integracaoFipe.integracaoFipe.model.Brand;
import br.com.integracaoFipe.integracaoFipe.model.Model;
import br.com.integracaoFipe.integracaoFipe.service.BrandService;
import br.com.integracaoFipe.integracaoFipe.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FipeController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;

    @GetMapping("/marca")
    public ResponseEntity<List<Brand>> getBrands() {
        return brandService.getBrandsFromApi();
    }

    @GetMapping("/marca/{brandId}/modelo")
    public ResponseEntity<List<Model>> getModels(@PathVariable Integer brandId) {
        return modelService.getModelsFromApi(brandId);
    }

    @GetMapping("/marca/{brandId}/filtraModelos")
    public ResponseEntity<List<Model>> getModelsFiltered(@PathVariable Integer brandId, @RequestParam(required = false) Integer startYear,
                                                         @RequestParam(required = false) Integer endYear, @RequestParam(required = false) Double minValue,
                                                         @RequestParam(required = false) Double maxValue, @RequestParam(required = false) Character gasType,
                                                         @RequestParam(required = false) String vehicleType) {
        return modelService.getFilteredModelsFromApi(brandId, startYear, endYear, minValue, maxValue, gasType, vehicleType);
    }
}
