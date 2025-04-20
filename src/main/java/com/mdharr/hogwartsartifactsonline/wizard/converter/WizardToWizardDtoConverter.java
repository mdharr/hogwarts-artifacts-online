package com.mdharr.hogwartsartifactsonline.wizard.converter;

import com.mdharr.hogwartsartifactsonline.wizard.Wizard;
import com.mdharr.hogwartsartifactsonline.wizard.dto.WizardDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WizardToWizardDtoConverter implements Converter<Wizard, WizardDto> {

    @Override
    public WizardDto convert(Wizard source) {
        return new WizardDto(source.getId(), source.getName(), source.getNumberOfArtifacts());
    }
}
