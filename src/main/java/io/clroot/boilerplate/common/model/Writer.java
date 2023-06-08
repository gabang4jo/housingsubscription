package io.clroot.boilerplate.common.model;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Optional.ofNullable;
import static java.util.regex.Pattern.matches;

import java.util.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Writer {
    private final String email;

    private final String name;


    public Writer(String email, String name) {
        checkArgument(email != null && checkAddress(email), "email must be provided.");
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Optional<String> getName() {
        return ofNullable(name);
    }

    private static boolean checkAddress(String address){
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+",address);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("email", email)
            .append("name", name)
            .toString();
    }

}
