package com.sata.hackweek;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvUtil {
    private static CsvMapper csvMapper = new CsvMapper();

    public static <T> List<T> load(InputStream resourceAsStream, Class<T> type) throws IOException {
        try (MappingIterator<T> readValues =
                     csvMapper.readerWithSchemaFor(type).with(CsvSchema.emptySchema().withHeader()).readValues(resourceAsStream)) {
            return readValues.readAll();
        }
    }

    public static <T> List<T> load(byte[] content, Class<T> type) throws IOException {
        try (MappingIterator<T> readValues =
                     csvMapper.readerWithSchemaFor(type).with(CsvSchema.emptySchema().withHeader()).readValues(content)) {
            return readValues.readAll();
        }
    }
}
