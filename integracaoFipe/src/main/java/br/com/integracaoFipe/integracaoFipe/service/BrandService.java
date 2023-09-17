package br.com.integracaoFipe.integracaoFipe.service;

import br.com.integracaoFipe.integracaoFipe.dao.BrandsRepository;
import br.com.integracaoFipe.integracaoFipe.model.Brand;
import br.com.integracaoFipe.integracaoFipe.model.Model;
import br.com.integracaoFipe.integracaoFipe.utils.BaseApiCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService extends BaseApiCommunication {

    private final BrandsRepository brandsRepository;

    public BrandService(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    public ResponseEntity<List<Brand>> getBrandsFromApi() {
        List<Brand> brands = new ArrayList<>();
        ParameterizedTypeReference responseType = new ParameterizedTypeReference<List<Brand>>() {};
        brands.addAll(getListDataFromUrl("/carros/marcas", responseType));
        brands.addAll(getListDataFromUrl("/motos/marcas", responseType));
        brands.addAll(getListDataFromUrl("/caminhoes/marcas", responseType));
        brandsRepository.saveAll(brands);
        return ResponseEntity.ok(brands);
    }
}