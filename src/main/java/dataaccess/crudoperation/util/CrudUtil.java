package dataaccess.crudoperation.util;

import dataaccess.crudoperation.annotation.Column;
import dataaccess.crudoperation.annotation.Id;
import dataaccess.crudoperation.annotation.Table;
import dataaccess.crudoperation.exception.IDIllegalArgumentException;
import dataaccess.crudoperation.exception.TableIllegalArgumentException;

import java.lang.reflect.Field;

public class CrudUtil {

    public static String getTableName(Class<?> entityClass) {
        if (entityClass.isAnnotationPresent(Table.class)) {
            return entityClass.getAnnotation(Table.class).name();
        } else {
            throw new TableIllegalArgumentException("Entity class " + entityClass.getName() + " must have @Table annotation");
        }
    }

    public static String getColumnName(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            return field.getAnnotation(Column.class).name();
        } else {
            return field.getName();
        }
    }

    public static String getIdColumn(Class<?> entityClass) {
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return getColumnName(field);
            }
        }
        throw new IDIllegalArgumentException("Entity class " + entityClass.getName() + " must have a field with @Id annotation");
    }
}
