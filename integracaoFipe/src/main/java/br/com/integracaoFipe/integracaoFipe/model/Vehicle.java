package br.com.integracaoFipe.integracaoFipe.model;

import br.com.integracaoFipe.integracaoFipe.utils.MoneyDeserializer;
import br.com.integracaoFipe.integracaoFipe.utils.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "modelYears")
public class Vehicle implements Serializable {

    @Id
    @JsonProperty("codigo")
    private String _id;
    @JsonProperty("TipoVeiculo")
    private String type;
    @JsonProperty("Valor")
    @JsonSerialize(using = MoneySerializer.class)
    @JsonDeserialize(using = MoneyDeserializer.class)
    private BigDecimal value;
    @JsonProperty("Marca")
    private String brand;
    @JsonProperty("Modelo")
    private String model;
    @JsonProperty("AnoModelo")
    private Integer year;
    @JsonProperty("Combustivel")
    private String gas;
    @JsonProperty("SiglaCombustivel")
    private String gasType;
    @JsonProperty("CodigoFipe")
    private String fipeCode;
    @JsonProperty("MesReferencia")
    private String refMonth;
}