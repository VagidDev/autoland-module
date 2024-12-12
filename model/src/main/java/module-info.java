module com.college.model {
    requires java.sql;
    uses java.sql.Driver;

    exports com.college.model;
    exports com.college.model.database.interfaces;
    exports com.college.model.database.session;
    exports com.college.model.database.implementations;
    exports com.college.model.keys; // Экспортируем API модели базы данных
}