package system.insurance.backend.resource.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class InsuranceInfoDTO {
    private final Map<String, String> companyList;
    private final Map<String, String> typeList;
    private final List<InsuranceDTO> productList;

    @Builder
    public InsuranceInfoDTO(Map<String, String> companyList, Map<String, String> typeList, List<InsuranceDTO> productList){
        this.companyList = companyList;
        this.typeList = typeList;
        this.productList = productList;
    }
}
