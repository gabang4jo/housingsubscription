package io.clroot.boilerplate.housingtransaction.dto;
import io.clroot.boilerplate.common.model.HouseInfo;
import io.clroot.boilerplate.housingtransaction.model.HousingTransaction;
import io.clroot.boilerplate.housingtransaction.model.TransactionDetails;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class HousingTransactionDto {


    private Long id;

    private HouseInfo houseInfo;

    private String cityDistrict;

    private TransactionDetails transactionDetails;


    public HousingTransactionDto(HousingTransaction source){
        this.id = source.getId();
        this.houseInfo = source.getHouseInfo();
        this.cityDistrict = source.getCityDistrict();
        this.transactionDetails = source.getTransactionDetails();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id",id)
            .append("houseInfo",houseInfo)
            .append("cityDistrict",cityDistrict)
            .append("transactionDetails",transactionDetails)
            .toString();
    }

}
