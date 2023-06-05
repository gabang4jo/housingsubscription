package io.clroot.boilerplate.common.model;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.*;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class HouseInfo {



    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "road_address", nullable = false)
    private String roadAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "latitude", column = @Column(name = "house_latitude",nullable = false)),
        @AttributeOverride(name = "longitude", column = @Column(name = "house_longitude",nullable = false))
    })
    private Coordinate coordinate;

    public HouseInfo(String name, String roadAddress, Coordinate coordinate) {
        checkArgument(name != null && isNotEmpty(name), "name must be provided");
        checkArgument(roadAddress != null && isNotEmpty(roadAddress), "roadAddress must be provided");
        checkArgument(coordinate != null , "coordinate must be provided");

        this.name = name;
        this.roadAddress = roadAddress;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("name", name)
            .append("roadAddress",roadAddress)
            .append("coordinate",coordinate)
            .toString();
    }


}