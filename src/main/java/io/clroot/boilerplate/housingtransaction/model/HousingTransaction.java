package io.clroot.boilerplate.housingtransaction.model;
import io.clroot.boilerplate.common.model.BaseEntity;
import io.clroot.boilerplate.common.model.HouseInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Entity
@Table(name = "housing_transaction")
public class HousingTransaction extends BaseEntity {

    @Embedded
    private HouseInfo houseInfo;


    @Column(name = "city_district", nullable = false)
    private String cityDistrict;

    @Embedded
    private TransactionDetails transactionDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HousingTransaction that = (HousingTransaction) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id",getId())
            .append("houseInfo",houseInfo)
            .append("cityDistrict",cityDistrict)
            .append("transactionDetails",transactionDetails)
            .toString();
    }

}