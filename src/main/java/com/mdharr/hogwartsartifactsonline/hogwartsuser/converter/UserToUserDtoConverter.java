package com.mdharr.hogwartsartifactsonline.hogwartsuser.converter;

import com.mdharr.hogwartsartifactsonline.hogwartsuser.HogwartsUser;
import com.mdharr.hogwartsartifactsonline.hogwartsuser.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<HogwartsUser, UserDto> {

    @Override
    public UserDto convert(HogwartsUser source) {
        // We are not setting password in DTO.
        final UserDto userDto = new UserDto(
                source.getId(),
                source.getUsername(),
                source.isEnabled(),
                source.getRoles()
        );
        return userDto;
    }
}
