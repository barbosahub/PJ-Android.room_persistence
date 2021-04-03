package br.com.alura.agenda.database.converter;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Calendar;


public class ConversorCalendar {

    @TypeConverter
    public Long toLong(Calendar value){
        if (value != null)
            return value.getTimeInMillis();

        return null;
    }
    @TypeConverter
    public Calendar toCalendar(Long value){
        Calendar atual = Calendar.getInstance();
        if (value != null)
            atual.setTimeInMillis(value);

        return atual;
    }

}
