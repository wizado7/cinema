package dataaccess.crudoperation.util;

public class SQLQuery {

    public static String SQL_INSERT(String tableName, String columnList, String placeholders) {
        return "INSERT INTO " +
                tableName + " (" +
                columnList + ") VALUES (" +
                placeholders + ")";
    }

    public static String SQL_DELETE(String tableName, String idColumn) {
        return "DELETE FROM " +
                tableName + " WHERE " +
                idColumn + " = ?";
    }

    public static String SQL_FIND_BY_ID(String tableName, String idColumn) {
        return "SELECT * FROM " +
                tableName + " WHERE " +
                idColumn + " = ?";
    }

    public static String SQL_FIND_ALL(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    public static String SQL_UPDATE(String tableName, String setClause, String idColumn) {
        return "UPDATE " +
                tableName + " SET " +
                setClause + " WHERE " +
                idColumn + " = ?";
    }

}
