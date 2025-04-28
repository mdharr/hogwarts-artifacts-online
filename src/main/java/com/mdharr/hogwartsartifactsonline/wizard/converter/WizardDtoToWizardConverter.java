package com.mdharr.hogwartsartifactsonline.wizard.converter;

import com.mdharr.hogwartsartifactsonline.wizard.Wizard;
import com.mdharr.hogwartsartifactsonline.wizard.dto.WizardDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WizardDtoToWizardConverter implements Converter<WizardDto, Wizard> {

    @Override
    public Wizard convert(WizardDto source) {
        Wizard wizard = new Wizard();
        wizard.setName(source.name());
        return wizard;
    }
}
