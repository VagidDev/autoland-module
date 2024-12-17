module com.college.model {
    requires java.sql;

    exports com.college.model;
    exports com.college.model.database.session;
    exports com.college.model.database.interfaces;
    exports com.college.model.database.exceptions;
    exports com.college.model.keys;

    uses java.sql.Driver;
}