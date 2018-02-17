package com.guru.recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.guru.recipeapp.commands.UnitOfMeasureCommand;
import com.guru.recipeapp.domain.UnitOfMessure;

import lombok.Synchronized;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMessure>{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMessure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOfMessure uom = new UnitOfMessure();
        uom.setId(source.getId());
        uom.setUom(source.getUom());
        return uom;
    }
}
