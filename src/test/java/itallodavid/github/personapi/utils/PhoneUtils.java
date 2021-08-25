package itallodavid.github.personapi.utils;

import itallodavid.github.personapi.dto.PhoneDTO;
import itallodavid.github.personapi.enums.PhoneType;
import itallodavid.github.personapi.model.Phone;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "11999999999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
            .number(PHONE_NUMBER)
            .type(PHONE_TYPE)
            .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
            .number(PHONE_NUMBER)
            .type(PHONE_TYPE)
            .build();
    }
}
